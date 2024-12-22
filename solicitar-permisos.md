# 🗒️ Solicitud de permisos en Android Studio con Kotlin.
1. Declarar los permisos en el android manifest.
2. Comprobar el nivel de la API.
3. Comprobar el estado del permiso (otorgado, no otorgado).
4. Manejar la respuesta del usuario.
5. Mostrar una explicación.

# 📌 1. Declarar los permisos en el android manifest.
En el fichero `AndroidManifest.xml` añadimos la linea del permiso que deseamos solicitar, encima de la etiqueta de `<Application>`.
```xml
<uses-permission android:name="android.permission.CAMERA"/>
```

# 📌 2. Comprobar el nivel de la API.
Desde Android 6.0 (API 23), los permisos sensibles se solicitan en tiempo de ejecución. Si tu aplicación tiene un minSdkVersion menor a 23, asegúrate de manejar los permisos de manera adecuada.
    
# 📌 3. Comprobar el estado del permiso (otorgado, no otorgado).
Antes de solicitar un permiso, comprueba si ya ha sido concedido:   

**Companion object.**   
```kotlin
// Codigo para el permiso de la camara.
private val REQUEST_CAMERA_PERMISSION = 100 // ID unico por app.
```
**En tiempo de ejecución.**
```kotlin
if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
    == PackageManager.PERMISSION_GRANTED) {
    // El permiso ya está concedido
    openCamera()
} else {
    // El permiso no está concedido, solicitarlo
    ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.CAMERA),
        REQUEST_CAMERA_PERMISSION
    )
}
```
# 📌 4. Manejar la respuesta del usuario.
# 📌 5. Mostrar una explicación.
