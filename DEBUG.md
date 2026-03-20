# 📄 Informe de Debug y Resolución de Errores

Este documento detalla los desafíos técnicos encontrados durante el desarrollo y cómo se solucionaron aplicando buenas prácticas de ingeniería.

### 1. ❌ Error: Desbordamiento Horizontal en la Tabla (Frontend)
* **Problema**: Al usar `white-space: nowrap` de forma global, la tabla de productos empujaba el diseño hacia la derecha, rompiendo el centrado y ocultando botones en pantallas medianas.
* **Resolución**: Se implementó un contenedor con `overflow-x-auto` y se limitó el uso de `nowrap` solo a celdas críticas. Se ajustó el `:host` para usar `width: 100%` en lugar de `100vw`, eliminando el scroll lateral del navegador.


### 2. ❌ Error: Persistencia de "Borrado Lógico" en Listado (Arquitectura)
* **Problema**: Al eliminar un producto, este seguía apareciendo en el Frontend a pesar de que el Backend marcaba `activo: false`.
* **Resolución**: Se modificó el `ProductoRepository` en Java para incluir una consulta personalizada o un filtro que solo devuelva productos donde `activo = true`. En el Frontend, se aseguró que el método `obtenerProductos()` refresque la lista inmediatamente tras la respuesta del servidor.

### 3. ❌ Vulnerabilidad Sonar: Inyección o Código Redundante (Calidad)
* **Problema**: Sonar detectó redundancia en las validaciones de la capa de servicio.
* **Resolución**: Se aplicó refactorización eliminando bloques `if/else` innecesarios y centralizando las excepciones en un `GlobalExceptionHandler` para mejorar la mantenibilidad.