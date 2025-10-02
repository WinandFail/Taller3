# Sistema de Gestión de Productos - JavaFX

Sistema de gestión de productos desarrollado con JavaFX que permite crear, listar y eliminar productos.

## 🚀 Características

- **Dashboard principal** con tabla de productos
- **Formulario de creación** de productos
- **Navegación dinámica** entre vistas
- **Gestión centralizada** de datos con patrón Singleton
- **Interfaz moderna** y responsive

## 📋 Requisitos

- Java 17 o superior
- Maven 3.6 o superior

## 🛠️ Instalación y Ejecución

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar la aplicación

```bash
mvn javafx:run
```

## 📁 Estructura del Proyecto

```
fx10/
├── src/
│   └── main/
│       ├── java/
│       │   ├── module-info.java
│       │   └── co/edu/uniquindio/fx10/
│       │       ├── App.java                          # Clase principal
│       │       ├── modelo/
│       │       │   └── Producto.java                 # Modelo de datos
│       │       ├── repositorio/
│       │       │   └── ProductoRepository.java       # Gestión del ArrayList
│       │       └── controlador/
│       │           ├── DashboardController.java      # Controlador del dashboard
│       │           └── FormularioProductoController.java # Controlador del formulario
│       └── resources/
│           └── co/edu/uniquindio/fx10/vista/
│               ├── Dashboard.fxml                    # Vista del dashboard
│               └── FormularioProducto.fxml           # Vista del formulario
└── pom.xml                                           # Configuración Maven
```

## 💡 Uso

### Dashboard Principal

- Visualiza todos los productos en una tabla
- Haz clic en "Crear Producto" para agregar un nuevo product
- Selecciona un product y haz clic en "Eliminar Producto" para eliminarlo

### Crear Producto

1. Haz clic en el botón "Crear Producto"
2. Completa todos los campos del formulario:
   - Código (único)
   - Nombre
   - Descripción
   - Precio
   - Stock
3. Haz clic en "Guardar Producto" o "Cancelar"

## 🏗️ Arquitectura

### Patrón Singleton
El `ProductoRepository` utiliza el patrón Singleton para garantizar que existe una única instancia del ArrayList de productos en toda la aplicación.

### Separación de Capas
- **Modelo**: Clase `Producto` con los atributos del negocio
- **Repositorio**: Gestión centralizada de los datos
- **Controlador**: Lógica de negocio y coordinación entre vista y modelo
- **Vista**: Archivos FXML con la interfaz de usuario

## 🎨 Características de la Interfaz

- Diseño moderno con colores profesionales
- Efectos de sombra y bordes redondeados
- Tabla responsive con formato de moneda en precios
- Validación de campos del formulario
- Mensajes de confirmación y error

## 📦 Datos de Ejemplo

La aplicación incluye 3 productos de ejemplo:
- Laptop Dell
- Mouse Logitech
- Teclado Mecánico

## 🔧 Tecnologías Utilizadas

- **JavaFX 21**: Framework de interfaz gráfica
- **Java 17**: Lenguaje de programación
- **Maven**: Gestión de dependencias y compilación
- **FXML**: Diseño de interfaces

## 👨‍💻 Autor

Proyecto desarrollado para el curso de Programación 1 - Universidad del Quindío

