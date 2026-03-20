# 🛒 Sistema de Productos - Backend API

API REST profesional construida con **Spring Boot** para la gestión de inventarios. Implementa una arquitectura limpia basada en capas y cumplimiento de estándares de calidad.

## 🛠️ Tecnologías y Arquitectura
* **Java 17 / Spring Boot 3**
* **Spring Data JPA**: Gestión de persistencia.
* **MySQL**: Base de Datos relacional.
* **Arquitectura de Capas**:
    * `Controller` -> `Service` -> `Repository` -> `Entity`
* **Calidad**: 100% de cobertura en lógica de negocio evaluada con **SonarCloud**.

## 🚀 Funcionalidades Clave
* **Borrado Lógico (Soft Delete)**: Los productos no se eliminan físicamente, se desactivan (`activo: false`).
* **Validaciones**: Integridad de datos en creación y actualización.
* **CORS Configurado**: Permitido para el origen del Frontend.

## ⚙️ Instalación
1. Configurar `application.properties` con tus credenciales de MySQL.
2.Abrir el proyecto en su IDE de preferencia (IntelliJ IDEA, Eclipse o VS Code) y ejecutar la clase principal `Application.java` mediante el botón **Run**.
3. Acceso base: `http://localhost:8080/api/productos`.