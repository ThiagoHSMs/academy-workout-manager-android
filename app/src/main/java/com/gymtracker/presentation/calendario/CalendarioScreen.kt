package com.gymtracker.presentation.calendario

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gymtracker.domain.model.HistoricoTreino
import com.gymtracker.domain.repository.HistoricoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

// ─── ViewModel ────────────────────────────────────────────────────────────────

@HiltViewModel
class CalendarioViewModel @Inject constructor(
    private val historicoRepository: HistoricoRepository
) : ViewModel() {

    private val _anoMes = MutableStateFlow(
        run {
            val c = Calendar.getInstance()
            "%04d-%02d".format(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1)
        }
    )
    val anoMes: StateFlow<String> = _anoMes.asStateFlow()

    val datasComTreino: StateFlow<Set<String>> = _anoMes
        .flatMapLatest { historicoRepository.getDatasDoMes(it) }
        .map { it.toSet() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    fun mesAnterior() {
        val parts = _anoMes.value.split("-")
        val cal = Calendar.getInstance()
        cal.set(parts[0].toInt(), parts[1].toInt() - 1, 1)
        cal.add(Calendar.MONTH, -1)
        _anoMes.value = "%04d-%02d".format(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1)
    }

    fun mesSeguinte() {
        val parts = _anoMes.value.split("-")
        val cal = Calendar.getInstance()
        cal.set(parts[0].toInt(), parts[1].toInt() - 1, 1)
        cal.add(Calendar.MONTH, 1)
        _anoMes.value = "%04d-%02d".format(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1)
    }
}

// ─── Screen ───────────────────────────────────────────────────────────────────

val mesesNomes = listOf("Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez")
val diasSemana = listOf("Dom","Seg","Ter","Qua","Qui","Sex","Sáb")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarioScreen(
    navController: NavController,
    viewModel: CalendarioViewModel = hiltViewModel()
) {
    val anoMes by viewModel.anoMes.collectAsState()
    val datasComTreino by viewModel.datasComTreino.collectAsState()

    val parts = anoMes.split("-")
    val ano = parts[0].toInt()
    val mes = parts[1].toInt()

    val cal = Calendar.getInstance()
    cal.set(ano, mes - 1, 1)
    val primeiroDiaSemana = cal.get(Calendar.DAY_OF_WEEK) - 1 // 0=Dom
    val diasNoMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    val hoje = Calendar.getInstance()
    val hojeStr = "%04d-%02d-%02d".format(
        hoje.get(Calendar.YEAR), hoje.get(Calendar.MONTH) + 1, hoje.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calendário", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Navegação mês
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = viewModel::mesAnterior) {
                        Icon(Icons.Default.ChevronLeft, "Mês anterior", tint = MaterialTheme.colorScheme.primary)
                    }
                    Text(
                        "${mesesNomes[mes - 1]} $ano",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = viewModel::mesSeguinte) {
                        Icon(Icons.Default.ChevronRight, "Próximo mês", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            // Cabeçalho dias da semana
            Row(Modifier.fillMaxWidth()) {
                diasSemana.forEach { dia ->
                    Text(
                        dia, modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Grid calendário
            val totalCells = primeiroDiaSemana + diasNoMes
            val linhas = (totalCells + 6) / 7

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                repeat(linhas) { linha ->
                    Row(Modifier.fillMaxWidth()) {
                        repeat(7) { col ->
                            val celula = linha * 7 + col
                            val dia = celula - primeiroDiaSemana + 1
                            val dataStr = "%04d-%02d-%02d".format(ano, mes, dia)
                            val temTreino = datasComTreino.contains(dataStr)
                            val eHoje = dataStr == hojeStr

                            Box(
                                modifier = Modifier.weight(1f).aspectRatio(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                if (dia in 1..diasNoMes) {
                                    val bgColor = when {
                                        eHoje -> MaterialTheme.colorScheme.primary
                                        temTreino -> MaterialTheme.colorScheme.secondary
                                        else -> Color.Transparent
                                    }
                                    val textColor = when {
                                        eHoje -> MaterialTheme.colorScheme.onPrimary
                                        temTreino -> MaterialTheme.colorScheme.onSecondary
                                        else -> MaterialTheme.colorScheme.onSurface
                                    }

                                    Box(
                                        Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .background(bgColor),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            "$dia",
                                            color = textColor,
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = if (eHoje || temTreino) FontWeight.Bold else FontWeight.Normal
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Legenda
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Legenda", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        LegendaItem(MaterialTheme.colorScheme.primary, "Hoje")
                        LegendaItem(MaterialTheme.colorScheme.secondary, "Treino realizado")
                    }
                    Text(
                        "Treinos este mês: ${datasComTreino.count { it.startsWith(anoMes) }}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun LegendaItem(cor: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(16.dp).clip(CircleShape).background(cor))
        Spacer(Modifier.width(6.dp))
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}
