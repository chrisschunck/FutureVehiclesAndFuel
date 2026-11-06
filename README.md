# 🚗 Frota Control — Gestão de Veículos Elétricos e Combustíveis Futuros
Projeto desenvolvido com o objetivo de aplicar conceitos de Arquitetura Hexagonal (Ports & Adapters) utilizando Spring Boot, explorando boas práticas de desenvolvimento, separação de responsabilidades, persistência com Spring Data JPA, DTOs e interface web com Thymeleaf.

# 📌 Objective
Gerenciar a frota de veículos elétricos (e de combustíveis futuros) de uma empresa através de uma API REST e de uma interface web.
A aplicação permite:
Cadastrar veículos com diferentes fontes de energia (BEV, FCEV, Híbrido) Cadastrar motoristas Alocar veículos a motoristas Finalizar alocações Registrar manutenções preventivas Registrar abastecimentos e recargas Consultar o status da frota em tempo real Interagir pela API REST com tratamento com ApiResponses e ou pela interface web

# ⚙️ Technologies
Java 21+
Spring Boot 4.1 
Spring Web (MVC) 
Spring Data JPA Hibernate 
Spring Security 
Bean Validation 
Thymeleaf Bootstrap 5 
PostgreSQL
Docker / Docker Compose
Lombok 
Maven 

# ▶️ Execution
deploy no railway
Aplicação disponível em: frotacontrol.up.railway.app

# 🗄️ Database
O sistema utiliza PostgreSQL para persistência dos dados.
O banco é inicializado automaticamente pelo Spring Boot.

# 🚀 Future Features
Autenticação com Spring Security 
+ JWT Módulo de Locadora (contrato de locação)
+ Módulo de Concessionária (pátio e test-drive)
+ Testes Unitários
+ Testes de Integração Telemetria via IoT/MQTT
+ Notificações por e-mail CI/CD com GitHub Actions
