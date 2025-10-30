## 🚀 MotoSecurityX — Challenge_java (2TDS 2025)

Spring Boot 3 + Thymeleaf + Spring Security

Atividade prática da disciplina Java Advanced, com o objetivo de corrigir erros, garantir o funcionamento completo da aplicação MVC e proteger rotas sensíveis com autenticação via Spring Security.

## 👥 Integrantes do Grupo

Caio Henrique – RM: 554600
Carlos Eduardo – RM: 555223
Antônio Lino – RM: 554518

## 🎯 Objetivo 

O desafio “Let’s Play a Game” propôs uma aplicação com múltiplos erros lógicos e sintáticos espalhados entre:

  - Um controlador Java com falhas críticas.

  - Dois templates Thymeleaf (login.html e dashboard.html) com erros de sintaxe e marcação.

  **Missão:** 

  1. Corrigir todos os erros e inconsistências.

  2. Fazer o sistema inicializar sem exceções.

  3. Garantir que controlador e views cooperem em harmonia.

  4. (Desafio Extra 💡) Adicionar proteção de rotas nas ações que alteram o estado do servidor, utilizando Spring Security + CSRF ativo.
  

## 🧩 Arquitetura e Estrutura

```

LetsPlayGame/
├── docs/
│   └── atividade_texto_01.pdf         # Enunciado original do desafio
│
├── src/
│   ├── main/java/com/example/letsplaygame/
│   │   ├── config/
│   │   │   └── SecurityConfig.java    # Configuração de autenticação e rotas
│   │   ├── web/
│   │   │   ├── GameController.java    # Controlador principal corrigido
│   │   │   └── LetsPlayGameApplication.java
│   │
│   ├── resources/
│   │   ├── templates/
│   │   │   ├── login.html             # Corrigido com CSRF e validação
│   │   │   └── dashboard.html         # Corrigido e seguro (POST protegido)
│   │   └── application.properties     # Configurações padrão
│
├── build.gradle                       # Gradle com dependências do Spring Boot
├── gradlew / gradlew.bat              # Wrappers do Gradle
├── HELP.md                            # Ajuda padrão do Spring Boot
├── settings.gradle
└── repositorio_git.txt                # Link de backup do repositório GitHub

```

## 🔐 Segurança Implementada
- A classe SecurityConfig.java define toda a proteção das rotas:

**Estrutura principal:**
```js
{
  @Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/do-something").authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )
            .csrf(Customizer.withDefaults());
        return http.build();
    }
}

}
```

    ✅ Rotas seguras: Apenas /login e estáticos são públicos.
    
    ✅ POST /do-something protegido por CSRF e sessão válida.
    
    ✅ Logout POST com invalidação de sessão.
    
    ✅ Bloqueio de acesso direto via URL sem autenticação.



## 💻 Views Thymeleaf (corrigidas)

**login.html:**

  - Corrigido th:action e th:text.
  
  - Inclusão de token CSRF.
   
  - Exibe “Voltar ao painel” apenas para usuários autenticados.

**dashboard.html:**

  - Corrigido th:each e sintaxe geral.
   
  - Ação POST protegida com CSRF.
   
  - Botão de Logout (POST).
   
  - Exibição dinâmica de mensagens e lista de itens.

## ▶️ Como executar o projeto

**Pré-requisitos:**

  - Java 17+

  - IntelliJ IDEA (ou VS Code com extensão Spring)

  - Gradle (wrapper incluído)

**Passos:**

1. Clone o repositório:

    git clone https://github.com/CarlosCampos84/letsplaygame.git
    cd letsplaygame


2. Compile e rode:

    ./gradlew bootRun


3. Acesse no navegador:
    👉 http://localhost:9090


## 🔎 Teste rápido
  
1. Abra /login e entre com o usuário padrão.

2. Após login, acesse / (Dashboard).

3. Clique em Executar ação (POST) — mensagem “Feito com sucesso!” aparece.
  
4. Clique em Logout — retorna para /login?logout.

5. Acesse / novamente — é redirecionado ao login (autenticação exigida).


## 📦 Entrega

- Arquivo ZIP: pasta LetsPlayGame (sem build/, .idea/, .gradle/).

- Pasta docs/: inclui o PDF original da atividade.

- repositorio_git.txt: contém o link deste repositório GitHub como backup.


## 🧾 Observações finais

✔️ Projeto compila e roda sem erros

✔️ Todas as views renderizam corretamente

✔️ Métodos de alteração usam @PostMapping

✔️ Rotas protegidas com Spring Security + CSRF

✔️ Entrega contém documentação e código-fonte completo


## 📄 Licença

Uso educacional / acadêmico.
© 2025 — Faculdade de Informática e Administração Paulista (FIAP).

