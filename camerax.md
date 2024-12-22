# 📌 Pasos a seguir.
1. Añadir librerias al fichero toml.
2. Configurar fichero build.gradle.kts de la aplicacion.
    - Añadir configuración viewBinding.
    - Comprobar configuración del compilador java.
    - Añadir dependencias.
3. Crear el diseño en el layout.xml de la actividad.
    - Introducir un PreviewView de cameraX.
    - Introducir botones de control.
4. Crear la Activity del layout anterior.
5. Declarar permisos en Android Manifest.
6. Solicitar permisos en activity.







## 🔸 Fichero `libs.versions.toml`.
```toml
[versions]
camerax = "1.1.0-beta01"

[libraries]
androidx-camera-core = { module = "androidx.camera:camera-core", version.ref = "camerax" }
androidx-camera-camera2 = { module = "androidx.camera:camera-camera2", version.ref = "camerax" }
androidx-camera-lifecycle = { module = "androidx.camera:camera-lifecycle", version.ref = "camerax" }
androidx-camera-video = { module = "androidx.camera:camera-video", version.ref = "camerax" }
androidx-camera-view = { module = "androidx.camera:camera-view", version.ref = "camerax" }
androidx-camera-extensions = { module = "androidx.camera:camera-extensions", version.ref = "camerax" }
```

## 🔸 Fichero `build.graded.kts` (aplicación)
**En el modulo `Android{}` - justo despues del modulo `defaultConfig{}`**   
```kts
    buildFeatures {
        viewBinding = true
    }
```
    
**En el modulo `Android{}` - justo despues del modulo `buildTypes{}`**
```kts
compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
```
    
**En el modulo `Dependencies{}`**
```kts
implementation (libs.androidx.camera.core)
implementation (libs.androidx.camera.camera2)
implementation (libs.androidx.camera.lifecycle)
implementation (libs.androidx.camera.video)
implementation (libs.androidx.camera.view)
implementation (libs.androidx.camera.extensions)
```

## 🔸 Pantalla de previsualización de la camara.

**Diseño XML.**
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
   xmlns:android="http://schemas.android.com/apk/res/android"
   xmlns:app="http://schemas.android.com/apk/res-auto"
   xmlns:tools="http://schemas.android.com/tools"
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   tools:context=".MainActivity">

   <androidx.camera.view.PreviewView
       android:id="@+id/viewFinder"
       android:layout_width="match_parent"
       android:layout_height="match_parent" />

   <Button
       android:id="@+id/image_capture_button"
       android:layout_width="110dp"
       android:layout_height="110dp"
       android:layout_marginBottom="50dp"
       android:layout_marginEnd="50dp"
       android:elevation="2dp"
       android:text="@string/take_photo"
       app:layout_constraintBottom_toBottomOf="parent"
       app:layout_constraintLeft_toLeftOf="parent"
       app:layout_constraintEnd_toStartOf="@id/vertical_centerline" />

   <Button
       android:id="@+id/video_capture_button"
       android:layout_width="110dp"
       android:layout_height="110dp"
       android:layout_marginBottom="50dp"
       android:layout_marginStart="50dp"
       android:elevation="2dp"
       android:text="@string/start_capture"
       app:layout_constraintBottom_toBottomOf="parent"
       app:layout_constraintStart_toEndOf="@id/vertical_centerline" />

   <androidx.constraintlayout.widget.Guideline
       android:id="@+id/vertical_centerline"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       android:orientation="vertical"
       app:layout_constraintGuide_percent=".50" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

**Activity relacionada.**

## 🔸 Declaración de permisos en `AndroidManifest.xml`.
```xml
    <uses-feature android:name="android.hardware.camera.any" />

    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission
        android:name="android.permission.WRITE_EXTERNAL_STORAGE"
        android:maxSdkVersion="28" />

    <uses-feature
        android:name="android.hardware.camera"
        android:required="true" />
```

**En el companion object**   
```kotlin
companion object {
    // Constante que representa el código único utilizado para identificar la solicitud de permisos
    private const val CODIGO_PERMISOS = 10

    // Lista mutable que contiene los permisos requeridos para la funcionalidad de la aplicación
    private val PERMISOS_REQUERIDOS = mutableListOf(
        // Permiso para acceder a la cámara
        Manifest.permission.CAMERA,
        // Permiso para grabar audio
        Manifest.permission.RECORD_AUDIO
    ).apply {
        // Verifica si la versión del SDK de Android es igual o menor a Android 9 (Pie)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            // Agrega el permiso para escribir en el almacenamiento externo en versiones antiguas
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }.toTypedArray() // Convierte la lista mutable a un arreglo para facilitar su uso
}
```
   
**Funcion que solicita los permisos si no han sido otorgados**   
```kotlin
// Verifica si todos los permisos necesarios están concedidos
if (todosLosPermisosConcedidos()) {
    // Si todos los permisos están otorgados, inicializa la cámara
    iniciarCamara()
} else {
    // Si faltan permisos, solicita los permisos al usuario
    ActivityCompat.requestPermissions(
        this,                // Contexto de la actividad actual
        PERMISOS_REQUERIDOS, // Lista de permisos requeridos
        CODIGO_PERMISOS      // Código único para identificar esta solicitud
    )
}
```
   
**Funcion de comprobación de permisos.**   
```kotlin
// Define una función privada que verifica si todos los permisos necesarios han sido otorgados.
// Devuelve un valor booleano (true o false).
private fun todosLosPermisosConcedidos() = PERMISOS_REQUERIDOS.all {
    ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
}
```
   
**Funcion que maneja los resultados de los permisos.**   
```kotlin
// Maneja los resultados de las solicitudes de permisos en tiempo de ejecución
override fun onRequestPermissionsResult(codigoDeSolicitud: Int, permisosSolicitados: Array<out String>, resultadosEntregados: IntArray) {
    // Llama a la implementación de la superclase para manejar otros casos predeterminados
    super.onRequestPermissionsResult(codigoDeSolicitud, permisosSolicitados, resultadosEntregados)

    // Verifica si el código de solicitud coincide con el definido para los permisos
    if (codigoDeSolicitud == CODIGO_PERMISOS) {
        // Comprueba si hay resultados y si el primer permiso fue concedido
        if (resultadosEntregados.isNotEmpty() && resultadosEntregados[0] == PackageManager.PERMISSION_GRANTED) {
            // Muestra un mensaje indicando que el permiso fue concedido
            Toast.makeText(this, "Permiso concedido.", Toast.LENGTH_SHORT).show()
        } else {
            // Muestra un mensaje indicando que el permiso fue denegado
            Toast.makeText(this, "Permiso denegado.", Toast.LENGTH_SHORT).show()
        }
    }
}
```

