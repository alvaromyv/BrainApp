# 🧠 Brain App

## 📌 Introducción
**Brain App** es una aplicación de preguntas y respuestas para Android, desarrollada en Kotlin con Android Studio. La app presenta preguntas de cálculo y cultura general, con un sistema de puntuación basado en respuestas correctas dentro de un tiempo límite.

## 🚀 Características Principales
✅ **Dos tipos de preguntas**: 
   - Cálculo (respuesta libre con sumas aleatorias)
   - Cultura General (preguntas tipo test de opción múltiple o V/F)
✅ **Tiempo límite para cada respuesta**
✅ **Juego dinámico**: Si fallas o el tiempo se acaba, el juego termina
✅ **Resumen de puntuación al finalizar**
✅ **Menú de navegación entre secciones**

## 📱 Pantallas y Flujo de Navegación

### 1️⃣ **InicioFragment**
📌 Muestra las reglas y un botón para iniciar el juego.  
📌 Se generan preguntas aleatorias según los parámetros configurados.

### 2️⃣ **SumaFragment** (Preguntas de Cálculo)
📌 Presenta una suma aleatoria dentro del rango definido.  
📌 Campo de entrada para la respuesta + botón para enviarla.  
📌 Cuenta regresiva de tiempo visible en pantalla.  

### 3️⃣ **OpcionesFragment** (Preguntas de Cultura General)
📌 Muestra la pregunta y opciones de respuesta (radio buttons).  
📌 Seleccionar una opción confirma automáticamente la respuesta.  

### 4️⃣ **FinFragment** (Pantalla de Fin de Juego)
📌 Muestra la puntuación final.  
📌 Opción para volver a la pantalla de inicio.  

### 5️⃣ **ValoracionActivity**
📌 Permite valorar la experiencia tras la partida.  
📌 Opciones: "Me gusta", "No me gusta".  
📌 La elección se almacena solo durante la ejecución.  

### 6️⃣ **ConfiguracionActivity**
📌 Permite definir los parámetros del juego:  
   - Número de preguntas.
   - Tiempo límite por pregunta.
   - Valor máximo de los números a sumar.
📌 No requiere persistencia de datos entre sesiones.  

## 🛠️ Tecnologías y Componentes Usados
🔹 **Lenguaje**: Kotlin  
🔹 **IDE**: Android Studio  
🔹 **Arquitectura**: Fragments y Navigation Component  
🔹 **Layouts**: ConstraintLayout y LinearLayout  
🔹 **UI Components**: TextView, EditText, RadioButton, Button, Snackbar  
🔹 **Menú de navegación**: Barra superior o botonera inferior con iconos  

## 🎛️ Extras y Configuración
✅ **Back Navigation:**  
   - `InicioFragment` → Salir de la app.  
   - `Otros fragmentos` → Regresar a `InicioFragment`.  
✅ **Interfaz adaptable a distintos dispositivos (sin modo landscape)**  
✅ **Uso de Navigation Component para transición fluida entre fragmentos**  

## 📜 Recursos y Bibliografía
📌 [Documentación oficial de Kotlin](https://kotlinlang.org/docs/home.html)  
📌 [Guía de Android Studio](https://developer.android.com/studio)  
📌 [Uso de Navigation Component](https://developer.android.com/guide/navigation)  
