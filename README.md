# 🏋️ GymTracker - Organizador de Treinos de Academia

Aplicativo Android profissional para gerenciamento de treinos, desenvolvido com:
- **Kotlin** + **Jetpack Compose**
- **Room Database** (SQLite offline)
- **Hilt** (Injeção de dependência)
- **MVVM + Clean Architecture**
- **Material Design 3** (tema escuro premium)
- **WorkManager** (notificações)

---

## 📁 Estrutura do Projeto

```
app/src/main/java/com/gymtracker/
├── GymTrackerApp.kt               ← Application + Hilt + Canais de notificação
├── MainActivity.kt                ← Entry point + Bottom Navigation Bar
├── data/
│   ├── local/
│   │   ├── dao/Daos.kt            ← TreinoDao, ExercicioDao, HistoricoDao
│   │   ├── entity/Entities.kt     ← Entidades Room (4 tabelas)
│   │   └── database/GymDatabase.kt
│   ├── mapper/Mappers.kt          ← Conversão entity ↔ domain
│   └── repository/RepositoryImpl.kt
├── domain/
│   ├── model/Models.kt            ← Modelos de domínio
│   ├── repository/Repositories.kt ← Interfaces
│   └── usecase/UseCases.kt        ← Toda a lógica de negócio
├── di/AppModule.kt                ← Módulos Hilt
├── navigation/Navigation.kt       ← NavHost + rotas
├── service/NotificationService.kt ← Worker + agendamento
└── presentation/
    ├── theme/Theme.kt             ← Cores Material 3 dark
    ├── dashboard/                 ← Tela inicial com resumo
    ├── treinohoje/                ← Treino do dia com checklist
    ├── treinos/                   ← CRUD de treinos
    ├── exercicios/                ← CRUD de exercícios
    ├── historico/                 ← Histórico completo
    ├── evolucao/                  ← Gráfico de evolução de cargas
    ├── calendario/                ← Calendário mensal visual
    ├── configuracoes/             ← Backup/restauração
    └── components/CronometroCard.kt ← Cronômetro de descanso
```

---

## 🚀 Como Abrir no Android Studio

### Pré-requisitos
- Android Studio Ladybug (2024.2.1) ou superior
- JDK 17
- Android SDK 35
- Dispositivo/emulador Android 8.0+ (API 26+)

### Passos

1. **Abrir o projeto**
   ```
   File → Open → selecione a pasta "gymtracker"
   ```

2. **Sincronizar o Gradle**
   - Android Studio vai detectar automaticamente e perguntar para sincronizar
   - Clique em **"Sync Now"** na barra amarela que aparecer
   - Aguarde o download das dependências (~2-5 min na primeira vez)

3. **Verificar SDK**
   ```
   File → Project Structure → SDK Location
   Confirme que o Android SDK está instalado
   ```

4. **Rodar o app**
   - Conecte um dispositivo Android via USB com "Depuração USB" ativada
   - OU crie um emulador: `Tools → Device Manager → Create Device`
   - Clique no botão ▶️ (Run 'app') ou pressione `Shift+F10`

---

## 📦 Como Gerar o APK

### APK de Debug (para testes)
```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```
O APK será gerado em:
```
app/build/outputs/apk/debug/app-debug.apk
```

### APK de Release (para distribuição)
1. Crie uma keystore (primeira vez):
   ```
   Build → Generate Signed Bundle / APK → APK → Create new...
   ```
   Preencha os dados e salve o arquivo `.jks`

2. Gere o APK assinado:
   ```
   Build → Generate Signed Bundle / APK → APK
   → Selecione sua keystore
   → Build Variant: release
   → Finish
   ```
   O APK estará em:
   ```
   app/build/outputs/apk/release/app-release.apk
   ```

---

## 🏪 Como Gerar AAB para Google Play

1. ```
   Build → Generate Signed Bundle / APK → Android App Bundle
   ```
2. Selecione sua keystore
3. Build variant: **release**
4. O arquivo `.aab` será gerado em:
   ```
   app/build/outputs/bundle/release/app-release.aab
   ```
5. Faça upload no **Google Play Console** → Produção → Criar release

---

## ✅ Funcionalidades Implementadas

| Funcionalidade | Status |
|---|---|
| Dashboard com resumo do dia | ✅ |
| Treino de Hoje com checklist | ✅ |
| CRUD de Treinos | ✅ |
| CRUD de Exercícios | ✅ |
| Histórico de treinos | ✅ |
| Detalhe do histórico | ✅ |
| Evolução de cargas | ✅ |
| Calendário mensal | ✅ |
| Cronômetro de descanso | ✅ |
| Notificações diárias | ✅ |
| Backup/exportação do banco | ✅ |
| Tema dark premium | ✅ |
| Bottom Navigation | ✅ |
| Busca em tempo real | ✅ |
| Validação de formulários | ✅ |
| Testes unitários (JUnit + Mockito) | ✅ |
| Arquitetura MVVM + Clean | ✅ |
| Room + Hilt + Coroutines | ✅ |

---

## 🗄️ Banco de Dados

4 tabelas Room:
- **treinos** - nome, dia da semana, grupo muscular, observações
- **exercicios** - nome, séries, reps, carga, descanso, ordem (FK → treinos)
- **historico_treinos** - data, hora, nome do treino (FK → treinos)
- **historico_exercicios** - carga utilizada, séries/reps reais (FK → historico_treinos)

---

## 🔔 Notificações

O WorkManager agenda uma notificação diária às **7h da manhã** informando o treino do dia.
Ao reiniciar o dispositivo, o `BootReceiver` reagenda automaticamente.

---

## 🧪 Testes

Rodar os testes unitários:
```
./gradlew test
```
Ou pelo Android Studio:
```
Clique direito em app/src/test → Run Tests
```

---

## 📞 Suporte

Em caso de erros de build:
1. `File → Invalidate Caches → Invalidate and Restart`
2. `Build → Clean Project` → `Build → Rebuild Project`
3. Verifique se o JDK 17 está configurado em `File → Project Structure → SDK`
