package com.gymtracker.presentation.treinos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gymtracker.domain.model.DiaSemana

val gruposMusculares = listOf(
    "Peitoral", "Costas", "Ombros", "Bíceps", "Tríceps",
    "Pernas", "Glúteos", "Abdomen", "Panturrilha", "Full Body"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreinoFormScreen(
    treinoId: Long?,
    navController: NavController,
    viewModel: TreinoFormViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var diaMenuExpanded by remember { mutableStateOf(false) }
    var grupoMenuExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(treinoId) { treinoId?.let { viewModel.carregarTreino(it) } }
    LaunchedEffect(state.isSaved) { if (state.isSaved) navController.popBackStack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (treinoId == null) "Novo Treino" else "Editar Treino",
                        fontWeight = FontWeight.Bold
                    )
                },
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
            Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Nome
            OutlinedTextField(
                value = state.nome,
                onValueChange = viewModel::onNomeChange,
                label = { Text("Nome do Treino *") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.nomeError != null,
                supportingText = state.nomeError?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
                leadingIcon = { Icon(Icons.Default.FitnessCenter, null) },
                singleLine = true
            )

            // Dia da Semana
            ExposedDropdownMenuBox(
                expanded = diaMenuExpanded,
                onExpandedChange = { diaMenuExpanded = it }
            ) {
                OutlinedTextField(
                    value = DiaSemana.fromInt(state.diaSemana).label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Dia da Semana") },
                    modifier = Modifier.fillMaxWidth().menuAnchor(),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(diaMenuExpanded) },
                    leadingIcon = { Icon(Icons.Default.CalendarToday, null) }
                )
                ExposedDropdownMenu(
                    expanded = diaMenuExpanded,
                    onDismissRequest = { diaMenuExpanded = false }
                ) {
                    DiaSemana.entries.forEach { dia ->
                        DropdownMenuItem(
                            text = { Text(dia.label) },
                            onClick = {
                                viewModel.onDiaChange(dia.value)
                                diaMenuExpanded = false
                            },
                            leadingIcon = {
                                if (state.diaSemana == dia.value)
                                    Icon(Icons.Default.Check, null, tint = MaterialTheme.colorScheme.primary)
                            }
                        )
                    }
                }
            }

            // Grupo Muscular
            ExposedDropdownMenuBox(
                expanded = grupoMenuExpanded,
                onExpandedChange = { grupoMenuExpanded = it }
            ) {
                OutlinedTextField(
                    value = state.grupoMuscular,
                    onValueChange = viewModel::onGrupoChange,
                    label = { Text("Grupo Muscular *") },
                    modifier = Modifier.fillMaxWidth().menuAnchor(),
                    isError = state.grupoError != null,
                    supportingText = state.grupoError?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(grupoMenuExpanded) },
                    leadingIcon = { Icon(Icons.Default.Accessibility, null) }
                )
                ExposedDropdownMenu(
                    expanded = grupoMenuExpanded,
                    onDismissRequest = { grupoMenuExpanded = false }
                ) {
                    gruposMusculares.forEach { grupo ->
                        DropdownMenuItem(
                            text = { Text(grupo) },
                            onClick = {
                                viewModel.onGrupoChange(grupo)
                                grupoMenuExpanded = false
                            }
                        )
                    }
                }
            }

            // Observações
            OutlinedTextField(
                value = state.observacoes,
                onValueChange = viewModel::onObservacoesChange,
                label = { Text("Observações") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5,
                leadingIcon = { Icon(Icons.Default.Notes, null) }
            )

            state.error?.let {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
                    Text(it, modifier = Modifier.padding(12.dp), color = MaterialTheme.colorScheme.onErrorContainer)
                }
            }

            Button(
                onClick = viewModel::salvar,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Icon(Icons.Default.Save, null)
                    Spacer(Modifier.width(8.dp))
                    Text("Salvar Treino", fontWeight = FontWeight.Bold)
                }
            }

            Spacer(Modifier.height(80.dp))
        }
    }
}
