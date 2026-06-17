package com.gymtracker

import com.gymtracker.domain.model.*
import com.gymtracker.domain.repository.ExercicioRepository
import com.gymtracker.domain.repository.HistoricoRepository
import com.gymtracker.domain.repository.TreinoRepository
import com.gymtracker.domain.usecase.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

// ─── SaveTreinoUseCase Tests ──────────────────────────────────────────────────

class SaveTreinoUseCaseTest {

    private lateinit var repository: TreinoRepository
    private lateinit var useCase: SaveTreinoUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = SaveTreinoUseCase(repository)
    }

    @Test
    fun `deve inserir treino valido com sucesso`() = runTest {
        val treino = Treino(id = 0, nome = "Peito", diaSemana = 2, grupoMuscular = "Peitoral")
        whenever(repository.insertTreino(treino)).thenReturn(1L)

        val result = useCase(treino)

        assertTrue(result.isSuccess)
        assertEquals(1L, result.getOrNull())
        verify(repository).insertTreino(treino)
    }

    @Test
    fun `deve retornar erro para nome em branco`() = runTest {
        val treino = Treino(id = 0, nome = "", diaSemana = 2, grupoMuscular = "Peitoral")

        val result = useCase(treino)

        assertTrue(result.isFailure)
        assertEquals("Nome do treino é obrigatório", result.exceptionOrNull()?.message)
        verify(repository, never()).insertTreino(any())
    }

    @Test
    fun `deve retornar erro para dia da semana invalido`() = runTest {
        val treino = Treino(id = 0, nome = "Peito", diaSemana = 10, grupoMuscular = "Peitoral")

        val result = useCase(treino)

        assertTrue(result.isFailure)
        assertEquals("Dia da semana inválido", result.exceptionOrNull()?.message)
    }

    @Test
    fun `deve atualizar treino existente`() = runTest {
        val treino = Treino(id = 5L, nome = "Costas", diaSemana = 3, grupoMuscular = "Dorsal")
        whenever(repository.updateTreino(treino)).thenReturn(Unit)

        val result = useCase(treino)

        assertTrue(result.isSuccess)
        assertEquals(5L, result.getOrNull())
        verify(repository).updateTreino(treino)
        verify(repository, never()).insertTreino(any())
    }
}

// ─── SaveExercicioUseCase Tests ───────────────────────────────────────────────

class SaveExercicioUseCaseTest {

    private lateinit var repository: ExercicioRepository
    private lateinit var useCase: SaveExercicioUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = SaveExercicioUseCase(repository)
    }

    @Test
    fun `deve inserir exercicio valido`() = runTest {
        val ex = Exercicio(id = 0, treinoId = 1, nome = "Supino", series = 4, repeticoes = 10, carga = 80.0, descanso = 90)
        whenever(repository.insertExercicio(ex)).thenReturn(1L)

        val result = useCase(ex)

        assertTrue(result.isSuccess)
        verify(repository).insertExercicio(ex)
    }

    @Test
    fun `deve rejeitar series zero`() = runTest {
        val ex = Exercicio(id = 0, treinoId = 1, nome = "Supino", series = 0, repeticoes = 10, carga = 80.0, descanso = 90)

        val result = useCase(ex)

        assertTrue(result.isFailure)
        assertEquals("Séries deve ser maior que zero", result.exceptionOrNull()?.message)
    }

    @Test
    fun `deve rejeitar carga negativa`() = runTest {
        val ex = Exercicio(id = 0, treinoId = 1, nome = "Supino", series = 4, repeticoes = 10, carga = -5.0, descanso = 90)

        val result = useCase(ex)

        assertTrue(result.isFailure)
        assertEquals("Carga não pode ser negativa", result.exceptionOrNull()?.message)
    }
}

// ─── GetTreinoHojeUseCase Tests ───────────────────────────────────────────────

class GetTreinoHojeUseCaseTest {

    private lateinit var repository: TreinoRepository
    private lateinit var useCase: GetTreinoHojeUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetTreinoHojeUseCase(repository)
    }

    @Test
    fun `deve retornar null quando nao ha treino para o dia`() = runTest {
        whenever(repository.getTreinoPorDia(any())).thenReturn(null)

        val result = useCase()

        assertNull(result)
    }

    @Test
    fun `deve retornar treino quando existe para o dia`() = runTest {
        val treino = Treino(id = 1, nome = "Peito", diaSemana = 2, grupoMuscular = "Peitoral")
        whenever(repository.getTreinoPorDia(any())).thenReturn(treino)

        val result = useCase()

        assertNotNull(result)
        assertEquals("Peito", result?.nome)
    }
}

// ─── DiaSemana Tests ──────────────────────────────────────────────────────────

class DiaSemanaTest {

    @Test
    fun `deve converter valor int corretamente`() {
        assertEquals(DiaSemana.SEGUNDA, DiaSemana.fromInt(2))
        assertEquals(DiaSemana.DOMINGO, DiaSemana.fromInt(1))
        assertEquals(DiaSemana.SABADO, DiaSemana.fromInt(7))
    }

    @Test
    fun `deve retornar SEGUNDA para valor invalido`() {
        assertEquals(DiaSemana.SEGUNDA, DiaSemana.fromInt(99))
    }

    @Test
    fun `deve ter labels corretos`() {
        assertEquals("Segunda-feira", DiaSemana.SEGUNDA.label)
        assertEquals("Domingo", DiaSemana.DOMINGO.label)
    }
}

// ─── SalvarTreinoRealizado Tests ──────────────────────────────────────────────

class SalvarTreinoRealizadoUseCaseTest {

    private lateinit var repository: HistoricoRepository
    private lateinit var useCase: SalvarTreinoRealizadoUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = SalvarTreinoRealizadoUseCase(repository)
    }

    @Test
    fun `deve salvar historico e retornar id`() = runTest {
        val historico = HistoricoTreino(
            treinoId = 1, nomeTreino = "Peito",
            dataExecucao = "2026-06-06", horaExecucao = "08:00"
        )
        whenever(repository.salvarTreinoRealizado(historico)).thenReturn(42L)

        val id = useCase(historico)

        assertEquals(42L, id)
        verify(repository).salvarTreinoRealizado(historico)
    }
}
