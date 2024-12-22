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
