# Minhas Tarefas

**Minhas Tarefas** é um aplicativo Android nativo desenvolvido em Kotlin para gerenciamento de produtividade pessoal. O app permite criar, organizar e acompanhar tarefas diárias com um sistema visual intuitivo de prioridades baseado em prazos.

## 📱 Funcionalidades

### Autenticação
* **Login com E-mail e Senha**: Criação e autenticação de contas persistentes.
* **Login Anônimo (Convidado)**: Acesso rápido para testar as funcionalidades sem necessidade de cadastro imediato.
* **Logout**: Encerramento de sessão seguro via Firebase Auth.

### Gerenciamento de Tarefas (CRUD)
* **Criar Tarefas**: Interface intuitiva com seletores nativos de Data (`DatePicker`) e Hora (`TimePicker`).
* **Listar Tarefas**: Visualização em lista com atualização "puxar para recarregar" (*Swipe Refresh*).
* **Editar Tarefas**: Atualização de título, descrição, data e hora.
* **Excluir Tarefas**: Gesto de deslizar para o lado (*Swipe*) com diálogo de confirmação de segurança.
* **Concluir Tarefas**: Marcação rápida de tarefas como "feitas" via menu de contexto.

### Interface e Experiência (UX/UI)
* **Sistema de Cores Semafórico**: Os cards mudam de cor automaticamente para indicar urgência:
    * 🟢 **Verde**: Tarefa concluída.
    * 🔴 **Vermelho**: Tarefa vencida (ontem ou antes).
    * 🟡 **Amarelo**: Tarefa vence hoje.
    * 🔵 **Azul**: Tarefa no prazo (futuro) ou sem data definida.
* **Identidade Visual**: Tema personalizado em tons de Marrom (*Coffee*).
* **Personalização**: Opção nas configurações para alternar o formato da data nos cards (Numérico `01/01/2025` ou Extenso `01 de janeiro de 2025`).

### Notificações
* **Push Notifications**: Integração com Firebase Cloud Messaging (FCM) para lembretes diários (Tópico: `daily_notification`), configurável via menu de preferências.

## 🛠 Tecnologias e Arquitetura

O projeto foi construído seguindo as melhores práticas de desenvolvimento Android moderno:

* **Linguagem**: [Kotlin](https://kotlinlang.org/)
* **Arquitetura**: MVVM (Model-View-ViewModel)
* **Comunicação com API**: [Retrofit2](https://square.github.io/retrofit/) & OkHttp3
* **Serialização de Dados**: Gson
* **Backend Integration**: Firebase (Auth, Cloud Messaging, Analytics, BOM)
* **UI**: XML Layouts, ViewBinding, Material Design Components
* **Gerenciamento de Estado**: LiveData & ViewModel
* **Persistência Local**: SharedPreferences (AndroidX Preference)
* **Listas**: RecyclerView com ListAdapter e DiffUtil (implícito no Adapter)

## 🔌 Back-end

⚠️ **Atenção:** Este aplicativo funciona como um cliente (Front-end) e depende de uma API REST para persistir os dados das tarefas.

O código-fonte do servidor Back-end **não está incluído neste repositório**. Ele deve ser baixado e executado separadamente.

🔗 **Repositório do Back-end:** [https://github.com/paulomatheus/to-do-app-server.git](https://github.com/paulomatheus/to-do-app-server.git)

O app está configurado por padrão para conectar-se ao endereço `http://10.0.2.2:8080`, que corresponde ao `localhost` da máquina host quando executado no Emulador Android.

## 🚀 Como Rodar o Projeto

### Pré-requisitos
1.  **Android Studio** (versão recomendada: Ladybug ou mais recente).
2.  **JDK 11** ou superior configurado.
3.  **Back-end rodando**: Certifique-se de iniciar o servidor Java mencionado acima na porta `8080`.

### Configuração do Firebase
Para que a autenticação e as notificações funcionem, você precisa configurar seu próprio projeto no Firebase:
1.  Crie um projeto no [Console do Firebase](https://console.firebase.google.com/).
2.  Adicione um app Android com o pacote `com.paulomatheus.mytasks`.
3.  Habilite o **Authentication** (Provedores: E-mail/Senha e Anônimo).
4.  Baixe o arquivo `google-services.json` e coloque-o na pasta `app/` do projeto.

### Instalação
1.  Clone este repositório:
    ```bash
    git clone [https://github.com/seu-usuario/minhas-tarefas.git](https://github.com/seu-usuario/minhas-tarefas.git)
    ```
2.  Abra o projeto no Android Studio.
3.  Aguarde o Gradle sincronizar as dependências.
4.  Execute o app em um emulador (recomendado para conexão fácil com o localhost) ou dispositivo físico.

---
Desenvolvido com Paulo Matheus
