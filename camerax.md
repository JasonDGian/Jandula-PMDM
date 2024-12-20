# 📌 Añadir dependencias en el script del modulo.

**En el fichero toml de librerias.**
```toml
camerax = "1.1.0-beta01"

androidx.camera.core = { module = "androidx.camera:camera-core", version.ref = "camerax" }
androidx.camera.camera2 = { module = "androidx.camera:camera-camera2", version.ref = "camerax" }
androidx.camera.lifecycle = { module = "androidx.camera:camera-lifecycle", version.ref = "camerax" }
androidx.camera.video = { module = "androidx.camera:camera-video", version.ref = "camerax" }
androidx.camera.view = { module = "androidx.camera:camera-view", version.ref = "camerax" }
androidx.camera.extensions = { module = "androidx.camera:camera-extensions", version.ref = "camerax" }
```

** En el fichero build.gradle.kts del modulo.
```kts
    implementation (libs.androidx.camera.core)
    implementation (libs.androidx.camera.camera2)
    implementation (libs.androidx.camera.lifecycle)
    implementation (libs.androidx.camera.video)
    implementation (libs.androidx.camera.view)
    implementation (libs.androidx.camera.extensions)
```

**En el fichero build.gradel.kts del modulo, justo despues del buildTypes{}**
```kts
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
```

**Por ultimo, habilitar el viewBinding, colocando esta instruccion despues del "defaultconfig"**
```kts
    buildFeatures {
        viewBinding = true
    }
```
