package com.gymtracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gymtracker.presentation.calendario.CalendarioScreen
import com.gymtracker.presentation.configuracoes.ConfiguracoesScreen
import com.gymtracker.presentation.evolucao.EvolucaoScreen
import com.gymtracker.presentation.exercicios.ExercicioFormScreen
import com.gymtracker.presentation.exercicios.ExerciciosScreen
import com.gymtracker.presentation.historico.HistoricoDetalheScreen
import com.gymtracker.presentation.historico.HistoricoScreen
import com.gymtracker.presentation.treinos.TreinoFormScreen
import com.gymtracker.presentation.treinos.TreinosScreen
import com.gymtracker.presentation.treinohoje.TreinoHojeScreen

sealed class Screen(val route: String) {
    object TreinoHoje : Screen("treino_hoje")
    object Treinos : Screen("treinos")
    object TreinoForm : Screen("treino_form?treinoId={treinoId}") {
        fun createRoute(treinoId: Long? = null) =
            if (treinoId != null) "treino_form?treinoId=$treinoId" else "treino_form"
    }
    object Exercicios : Screen("exercicios/{treinoId}") {
        fun createRoute(treinoId: Long) = "exercicios/$treinoId"
    }
    object ExercicioForm : Screen("exercicio_form/{treinoId}?exercicioId={exercicioId}") {
        fun createRoute(treinoId: Long, exercicioId: Long? = null) =
            if (exercicioId != null) "exercicio_form/$treinoId?exercicioId=$exercicioId"
            else "exercicio_form/$treinoId"
    }
    object Historico : Screen("historico")
    object HistoricoDetalhe : Screen("historico/{historicoId}") {
        fun createRoute(historicoId: Long) = "historico/$historicoId"
    }
    object Evolucao : Screen("evolucao")
    object Calendario : Screen("calendario")
    object Configuracoes : Screen("configuracoes")
}

@Composable
fun GymNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.TreinoHoje.route
    ) {
        composable(Screen.TreinoHoje.route) {
            TreinoHojeScreen(navController = navController)
        }

        composable(Screen.Treinos.route) {
            TreinosScreen(navController = navController)
        }

        composable(
            route = Screen.TreinoForm.route,
            arguments = listOf(
                navArgument("treinoId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->
            val treinoId = backStackEntry.arguments?.getLong("treinoId")?.takeIf { it != -1L }
            TreinoFormScreen(treinoId = treinoId, navController = navController)
        }

        composable(
            route = Screen.Exercicios.route,
            arguments = listOf(navArgument("treinoId") { type = NavType.LongType })
        ) { backStackEntry ->
            val treinoId = backStackEntry.arguments!!.getLong("treinoId")
            ExerciciosScreen(treinoId = treinoId, navController = navController)
        }

        composable(
            route = Screen.ExercicioForm.route,
            arguments = listOf(
                navArgument("treinoId") { type = NavType.LongType },
                navArgument("exercicioId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->
            val treinoId = backStackEntry.arguments!!.getLong("treinoId")
            val exercicioId = backStackEntry.arguments?.getLong("exercicioId")?.takeIf { it != -1L }
            ExercicioFormScreen(
                treinoId = treinoId,
                exercicioId = exercicioId,
                navController = navController
            )
        }

        composable(Screen.Historico.route) {
            HistoricoScreen(navController = navController)
        }

        composable(
            route = Screen.HistoricoDetalhe.route,
            arguments = listOf(navArgument("historicoId") { type = NavType.LongType })
        ) { backStackEntry ->
            val historicoId = backStackEntry.arguments!!.getLong("historicoId")
            HistoricoDetalheScreen(historicoId = historicoId, navController = navController)
        }

        composable(Screen.Evolucao.route) {
            EvolucaoScreen(navController = navController)
        }

        composable(Screen.Calendario.route) {
            CalendarioScreen(navController = navController)
        }

        composable(Screen.Configuracoes.route) {
            ConfiguracoesScreen(navController = navController)
        }
    }
}
