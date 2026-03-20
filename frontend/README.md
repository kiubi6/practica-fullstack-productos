# SistemaProductos

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 21.2.3.

# 🖥️ Sistema de Gestión de Productos - Frontend (Bun Stack)

Interfaz de usuario de alto rendimiento desarrollada con **Angular 17+**, diseñada para la administración centralizada de inventarios. Este proyecto destaca por el uso de **Bun** como runtime, logrando tiempos de instalación y ejecución superiores a los estándares tradicionales.

---

## 🚀 Tecnologías y Herramientas
* **Angular**: Framework principal para la arquitectura de componentes y servicios.
* **Bun**: Runtime y gestor de paquetes de última generación (reemplaza a Node.js/npm).
* **Tailwind CSS**: Framework de utilidades para un diseño responsivo y atómico.
* **RippleUI**: Suite de componentes para una interfaz moderna, limpia y consistente.

---

## 🛡️ Seguridad y Experiencia de Usuario (UX)
* **AuthGuard**: Implementación de seguridad en rutas privadas. Protege el acceso al panel de productos, redirigiendo al usuario al login si no existe una sesión activa.
* **Sistema de Login**: Validación de credenciales administrativas con persistencia de estado mediante `localStorage`.
* **Diseño "Green Energy"**: Interfaz personalizada con fondo verde vibrante (`#22c55e`), tarjetas blancas con bordes redondeados (`2rem`) y sombras profundas para una jerarquía visual clara.
* **Gestión Dinámica**: Tabla responsiva con soporte para scroll horizontal y acciones de CRUD inmediatas.

---

## ⚙️ Requisitos Previos
Es indispensable contar con **Bun** instalado en su entorno local:
* **Windows (PowerShell):** `powershell -c "irm bun.sh/install.ps1 | iex"`
* **Linux/macOS:** `curl -fsSL https://bun.sh/install | bash`

---

## 🛠️ Instalación y Ejecución

Siga estos pasos para desplegar el entorno de desarrollo:

1. **Clonar el repositorio:**
   ```bash
   git clone <url-de-tu-repositorio-frontend>
   cd nombre-del-proyecto-frontend.

##  Instalación dependencias
    ```bash
     bun install.

##  Ejecutar el servidor de desarrollo
```bash
bun run ng serve -o
