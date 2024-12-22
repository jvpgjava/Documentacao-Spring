package Spring_Dependencias;
// detalhando as 23 dependências principais do Spring Framework
// funcionalidades e exemplos práticos de uso para cada uma

public class SpringDependencias {

    /**
     * Dependência: spring-core
     * Descrição: Contém a infraestrutura central do Spring, incluindo IoC (Inversion of Control) e DI (Dependency Injection).
     * Exemplo de uso:
     */
    public static void springCoreExample() {
        // Configurando o contexto da aplicação baseado em configuração Java
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Obtendo um bean gerenciado pelo Spring
        MyBean myBean = context.getBean(MyBean.class);
        // Chamando um método do bean
        myBean.doSomething();
    }

    /**
     * Dependência: spring-beans
     * Descrição: Oferece funcionalidades para configuração e gerenciamento de beans.
     * Exemplo de uso:
     */
    public static void springBeansExample() {
        // Criando um BeanFactory para carregar configuração XML
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
        // Recuperando um bean pelo ID definido no XML
        MyBean myBean = (MyBean) factory.getBean("myBean");
        // Executando uma ação no bean
        myBean.doSomething();
    }

    /**
     * Dependência: spring-context
     * Descrição: Fornece extensões ao módulo Core para suporte de acesso a mensagens, eventos e outros serviços contextuais.
     * Exemplo de uso:
     */
    public static void springContextExample() {
        // Criando o contexto da aplicação baseado em configuração Java
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Obtendo um serviço gerenciado pelo Spring
        MyService myService = context.getBean(MyService.class);
        // Executando uma ação no serviço
        myService.performAction();
    }

    /**
     * Dependência: spring-aop
     * Descrição: Suporte para programação orientada a aspectos (Aspect-Oriented Programming).
     * Exemplo de uso:
     */
    public static void springAopExample() {
        // Definindo um aspecto para log antes de métodos específicos
        @Aspect
        class LoggingAspect {
            @Before("execution(* MyService.*(..))")
            public void logBefore() {
                // Log de informação antes da execução do método
                System.out.println("Método chamado");
            }
        }
    }

    /**
     * Dependência: spring-expression (SpEL)
     * Descrição: Fornece uma linguagem de expressão poderosa para consulta e manipulação de objetos.
     * Exemplo de uso:
     */
    public static void springExpressionExample() {
        // Criando o parser de expressões SpEL
        ExpressionParser parser = new SpelExpressionParser();
        // Avaliando uma expressão para concatenar strings
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        // Obtendo o resultado da expressão
        String message = exp.getValue(String.class);
        // Exibindo o resultado
        System.out.println(message);
    }

    /**
     * Dependência: spring-web
     * Descrição: Suporte para desenvolvimento web, incluindo integração com a Servlet API.
     * Exemplo de uso:
     */
    public static void springWebExample() {
        @RestController
        class MyController {
            @GetMapping("/hello")
            public String sayHello() {
                // Retornando uma mensagem HTTP
                return "Hello, Spring Web!";
            }
        }
    }

    /**
     * Dependência: spring-webmvc
     * Descrição: Implementa o padrão MVC para construção de aplicações web.
     * Exemplo de uso:
     */
    public static void springWebMvcExample() {
        @Controller
        class MyController {
            @GetMapping("/home")
            public String home(Model model) {
                // Adicionando dados ao modelo
                model.addAttribute("message", "Welcome to Spring MVC!");
                // Retornando o nome da view
                return "home";
            }
        }
    }

    /**
     * Dependência: spring-data-jpa
     * Descrição: Simplifica o uso do JPA com abstrações de repositório.
     * Exemplo de uso:
     */
    public static void springDataJpaExample() {
        @Entity
        class User {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String name;

            // Getters e Setters
        }

        @Repository
        interface UserRepository extends JpaRepository<User, Long> {
        }
    }

    /**
     * Dependência: spring-tx
     * Descrição: Suporte para gerenciamento de transações declarativas e programáticas.
     * Exemplo de uso:
     */
    public static void springTxExample() {
        @Transactional
        public void performTransaction() {
            // Código que manipula transações
        }
    }

    /**
     * Dependência: spring-jdbc
     * Descrição: Facilita o acesso a bancos de dados usando JDBC.
     * Exemplo de uso:
     */
    public static void springJdbcExample() {
        // Criando uma instância de JdbcTemplate
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // Executando uma consulta SQL
        String sql = "SELECT name FROM users WHERE id = ?";
        String name = jdbcTemplate.queryForObject(sql, new Object[]{1}, String.class);
        // Exibindo o nome obtido
        System.out.println(name);
    }

    /**
     * Dependência: spring-security
     * Descrição: Fornece autenticação e autorização para aplicações.
     * Exemplo de uso:
     */
    public static void springSecurityExample() {
        @Configuration
        @EnableWebSecurity
        class SecurityConfig extends WebSecurityConfigurerAdapter {
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                // Configurando segurança para diferentes endpoints
                http.authorizeRequests()
                        .antMatchers("/public").permitAll()
                        .anyRequest().authenticated()
                        .and().formLogin();
            }
        }
    }

    /**
     * Dependência: spring-test
     * Descrição: Ferramentas para testes de unidade e integração.
     * Exemplo de uso:
     */
    public static void springTestExample() {
        @SpringBootTest
        class MyServiceTest {
            @Autowired
            private MyService myService;

            @Test
            void testPerformAction() {
                // Verificando o resultado da ação
                assertEquals("Action Performed", myService.performAction());
            }
        }
    }

    /**
     * Dependência: spring-orm
     * Descrição: Suporte para frameworks ORM, como Hibernate.
     * Exemplo de uso:
     */
    public static void springOrmExample() {
        // Configurando SessionFactory para Hibernate
        SessionFactory sessionFactory = new AnnotationSessionFactoryBuilder()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        // Manipulando transações do Hibernate
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        // Código de manipulação do banco de dados
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Dependência: spring-messaging
     * Descrição: Suporte para sistemas de mensagens, como JMS.
     * Exemplo de uso:
     */
    public static void springMessagingExample() {
        @Component
        class MyMessageListener {
            @JmsListener(destination = "my-queue")
            public void receiveMessage(String message) {
                // Processando a mensagem recebida
                System.out.println("Received: " + message);
            }
        }
    }

    /**
     * Dependência: spring-amqp
     * Descrição: Integração com sistemas de mensagens como RabbitMQ.
     * Exemplo de uso:
     */
    public static void springAmqpExample() {
        @Configuration
        class AmqpConfig {
            @Bean
            public Queue myQueue() {
                // Criando uma fila RabbitMQ
                return new Queue("my-queue");
            }

            @Bean
            public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
                // Configurando RabbitTemplate para mensagens AMQP
                return new RabbitTemplate(connectionFactory);
            }
        }
    }

    /**
     * Dependência: spring-batch
     * Descrição: Suporte para processamento em lote.
     * Exemplo de uso:
     */
    public static void springBatchExample() {
        @Configuration
        @EnableBatchProcessing
        class BatchConfig {
            @Bean
            public Step step(StepBuilderFactory stepBuilderFactory) {
                // Configurando um passo de processamento em lote
                return stepBuilderFactory.get("step1")
                        .<String, String>chunk(1)
                        .reader(() -> "Hello")
                        .processor(item -> item + " Batch")
                        .writer(System.out::println)
                        .build();
            }

            @Bean
            public Job job(JobBuilderFactory jobBuilderFactory, Step step) {
                // Configurando um job de processamento em lote
                return jobBuilderFactory.get("job1")
                        .start(step)
                        .build();
            }
        }
    }

    /**
     * Dependência: spring-boot-starter
     * Descrição: Inclui configurações automáticas para iniciar um projeto Spring Boot.
     * Exemplo de uso:
     */
    public static void springBootStarterExample() {
        @SpringBootApplication
        public class Application {
            public static void main(String[] args) {
                // Inicializando a aplicação Spring Boot
                SpringApplication.run(Application.class, args);
            }
        }
    }

    /**
     * Dependência: spring-cloud
     * Descrição: Fornece suporte para aplicativos distribuídos e baseados em nuvem.
     * Exemplo de uso:
     */
    public static void springCloudExample() {
        @EnableEurekaClient
        @SpringBootApplication
        public class EurekaClientApplication {
            public static void main(String[] args) {
                // Registrando o cliente no servidor Eureka
                SpringApplication.run(EurekaClientApplication.class, args);
            }
        }
    }

    /**
     * Dependência: spring-webflux
     * Descrição: Suporte para aplicações reativas baseadas em programação funcional.
     * Exemplo de uso:
     */
    public static void springWebFluxExample() {
        @RestController
        class ReactiveController {
            @GetMapping("/flux")
            public Flux<String> getFlux() {
                // Criando um Flux reativo que retorna dados dinamicamente
                return Flux.just("Item 1", "Item 2", "Item 3");
            }
        }
    }

    /**
     * Dependência: spring-r2dbc
     * Descrição: Suporte para acesso reativo a bancos de dados relacionais.
     * Exemplo de uso:
     */
    public static void springR2dbcExample() {
        @Repository
        class UserRepository {
            private final DatabaseClient databaseClient;

            public UserRepository(DatabaseClient databaseClient) {
                // Configurando o cliente do banco de dados reativo
                this.databaseClient = databaseClient;
            }

            public Mono<User> findById(Long id) {
                // Consultando um banco de dados reativo usando SQL
                return databaseClient.sql("SELECT * FROM users WHERE id = :id")
                        .bind("id", id)
                        .map(row -> new User(row.get("id", Long.class), row.get("name", String.class)))
                        .one();
            }
        }
    }

    public static void main(String[] args) {
        // Mensagem inicial ao executar o programa
        System.out.println("Este arquivo contém exemplos de uso das 23 principais dependências do Spring Framework.");
    }
}
