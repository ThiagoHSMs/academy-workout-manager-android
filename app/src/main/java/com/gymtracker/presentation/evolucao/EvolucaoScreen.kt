package com.gymtracker.presentation.evolucao

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gymtracker.domain.model.HistoricoExercicio
import com.gymtracker.domain.repository.ExercicioRepository
import com.gymtracker.domain.repository.HistoricoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// ─── ViewModel ────────────────────────────────────────────────────────────────

data class EvolucaoState(
    val exercicios: List<String> = emptyList(),
    val exercicioSelecionado: String = "",
    val pontos: List<HistoricoExercicio> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class EvolucaoViewModel @Inject constructor(
    private val historicoRepository: HistoricoRepository,
    private val exercicioRepository: ExercicioRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EvolucaoState())
    val state: StateFlow<EvolucaoState> = _state.asStateFlow()

    init { carregarNomesExercicios() }

    private fun carregarNomesExercicios() {
        viewModelScope.launch {
            historicoRepository.getAllHistorico().collect { lista ->
                val nomes = lista.flatMap { it.exercicios }.map { it.nomeExercicio }.distinct().sorted()
                _state.value = _state.value.copy(exercicios = nomes)
                if (nomes.isNotEmpty() && _state.value.exercicioSelecionado.isEmpty()) {
                    selecionarExercicio(nomes.first())
                }
            }
        }
    }

    fun selecionarExercicio(nome: String) {
        _state.value = _state.value.copy(exercicioSelecionado = nome, isLoading = true)
        viewModelScope.launch {
            historicoRepository.getEvolucaoPorNomeExercicio(nome).collect { pontos ->
                _state.value = _state.value.copy(pontos = pontos, isLoading = false)
            }
        }
    }
}

// ─── Screen ───────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvolucaoScreen(
    navController: NavController,
    viewModel: EvolucaoViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var dropdownExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Evolução de Cargas", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Seletor de exercício
            if (state.exercicios.isNotEmpty()) {
                ExposedDropdownMenuBox(
                    expanded = dropdownExpanded,
                    onExpandedChange = { dropdownExpanded = it }
                ) {
                    OutlinedTextField(
                        value = state.exercicioSelecionado,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Exercício") },
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(dropdownExpanded) },
                        leadingIcon = { Icon(Icons.Default.FitnessCenter, null) }
                    )
                    ExposedDropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false }
                    ) {
                        state.exercicios.forEach { nome ->
                            DropdownMenuItem(
                                text = { Text(nome) },
                                onClick = {
                                    viewModel.selecionarExercicio(nome)
                                    dropdownExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            if (state.isLoading) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            } else if (state.pontos.isEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        Modifier.padding(32.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.TrendingUp, null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(Modifier.height(16.dp))
                        Text(
                            if (state.exercicios.isEmpty()) "Nenhum histórico ainda.\nConclua treinos para ver a evolução."
                            else "Nenhum dado para este exercício.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            } else {
                // Gráfico simplificado em cards (Vico requer configuração de tema específica)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Histórico de Carga", fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.height(8.dp))

                        // Mini bar chart manual
                        val maxCarga = state.pontos.maxOf { it.cargaUtilizada }
                        state.pontos.takeLast(8).forEach { ponto ->
                            val frac = if (maxCarga > 0) (ponto.cargaUtilizada / maxCarga).toFloat() else 0f
                            Row(
                                Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "${ponto.cargaUtilizada} kg",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.width(56.dp),
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                                LinearProgressIndicator(
                                    progress = { frac },
                                    modifier = Modifier.weight(1f).height(8.dp),
                                    color = MaterialTheme.colorScheme.secondary,
                                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            }
                        }
                    }
                }

                // Lista de registros
                Text("Registros", fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary)

                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(state.pontos.reversed()) { ponto ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Row(
                                Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.TrendingUp, null,
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(20.dp))
                                Spacer(Modifier.width(8.dp))
                                Column(Modifier.weight(1f)) {
                                    Text("${ponto.cargaUtilizada} kg",
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.secondary)
                                    Text("${ponto.seriesRealizadas}x${ponto.repeticoesRealizadas}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                        }
                    }
                    item { Spacer(Modifier.height(80.dp)) }
                }
            }
        }
    }
}
