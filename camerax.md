# 📌 Pasos a seguir.
1. Añadir dependencias.
2. Configurar compatibilidad de compilador con Java 1.8







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
Añadiremos la configuración del compilador para Java 1.8 y las dependencias del modulo dependencies.   
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



**Por ultimo, habilitar el viewBinding, colocando esta instruccion despues del "defaultconfig"**
```kts
    buildFeatures {
        viewBinding = true
    }
```
