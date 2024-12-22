# 🗒️ Solicitud de permisos en Android Studio con Kotlin.
1. Declarar los permisos en el android manifest.
2. Definir el codigo de solicitud y permisos en el companion object de la actividad.
3. Comprobar el estado del permiso (otorgado, no otorgado).
4. Manejar la respuesta del usuario.
5. Mostrar una explicación.

>[!CAUTION]
> Desde Android 6.0 (API 23), los permisos sensibles se solicitan en tiempo de ejecución. Si tu aplicación tiene un minSdkVersion menor a 23, asegúrate de manejar los permisos de manera adecuada. Esto hace que la declaración en el manifest o la solicitud de permisos pueda variar y deba de adaptarse al SDK en uso incluyendo mecanismos de retro-compatibilidad.

## 📌 1. Declarar los permisos en el android manifest.
En el fichero `AndroidManifest.xml` añadimos la linea del permiso que deseamos solicitar, encima de la etiqueta de `<Application>`.
```xml
<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.RECORD_AUDIO" />
```

## 📌 2. Definir el codigo y permisos en el companion object.
**Companion object.**   
```kotlin
companion object {
    // Codigo para el permiso de la camara.
    private val CODIGO_PERMISOS = 100 // ID unico por solicitud en app.
    // Array de permisos a solicitar.
    val PERMISOS_REQUERIDOS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
}
```

## 📌 3. Comprobar el estado del permiso (otorgado, no otorgado).
Antes de solicitar un permiso, comprueba si ya ha sido concedido:   

**Funcion para comprobar más de un permiso.**   
```kotlin
// Verifica iterando el array de permisos para ver si han sido concedidos o no.
private fun todosLosPermisosConcedidos() = PERMISOS_REQUERIDOS.all {
    ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
}
```

**Logica que comprueba si todos los permisos están concendidos, de no estarlo, los solicitará.**
```kotlin
// Solicitar permisos necesarios para la cámara y audio
if (todosLosPermisosConcedidos()) {
    iniciarCamara() // Si los permisos están concedidos, inicializar la cámara
} else {
    // Solicitar permisos al usuario
    ActivityCompat.requestPermissions(
        this,                 // Contexto atividad.
        PERMISOS_REQUERIDOS,  // Array de permisos requeridos.
        CODIGO_PERMISOS       // Codigo de solicitud.
    )
}
```

## 📌 4. Manejar la respuesta del usuario.
```kotlin
// Manejo de resultados de permisos
// Método de callback que se llama automáticamente por el sistema Android cuando el usuario responde a una solicitud de permiso (es decir, cuando concede o deniega el permiso).
override fun onRequestPermissionsResult(codigoDeSolicitud: Int, permisosSolicitados: Array<out String>, resultadosEntregados: IntArray) {
    super.onRequestPermissionsResult(codigoDeSolicitud, permisosSolicitados, resultadosEntregados)

    if (codigoDeSolicitud == CODIGO_PERMISOS) {
        if (resultadosEntregados.isNotEmpty() && resultadosEntregados[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }
}
```
