# 📌 Añadir dependencias Room.
El modo en el que añadimos dependencias a Android Studio ha cambiado en 2024.
Para seguir no complicarnos la vida seguimos los siquientes pasos.

## Habilitar el KSP.
En el fichero build.gradle de modulo nivel superior pegamos:
```kotlin
plugins {
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
}
```
En el fichero build.gradle de nivel de modulo pegamos:
```kotlin
plugins {
    id("com.google.devtools.ksp")
}
```

**Ahora importante sincronizar el proyecto para que acepte estos plugins antes de continuar**.


## Insertar dependencias.
Copiamos las dependencias que necesitamos desde la documentación y las pegamos a nivel de modulo..
```kotlin
dependencies {
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
}
```
**Sincronizamos el proyecto antes de continuar**.

Tras unos segundos aparecerá un mensaje que nos informará que podemos cambiar la importación para que refleje las tecnicas y estructura modernas recientes.
![imagen](https://github.com/user-attachments/assets/5bb72ab5-46f9-414e-97c6-a44a20de7467)

Realizamos el cambio para cada linea insertada.
![imagen](https://github.com/user-attachments/assets/1561fee2-7639-4e1e-bdf6-e02b779a87aa)

A continuación borraremos la variable de versión creada ya que esta no verá más uso.

En el fichero `libs.versions.toml` podremos observar nuestras importaciones adaptadas a estructura moderna.
![imagen](https://github.com/user-attachments/assets/019c57f1-fa7e-4836-b599-ee89f81c61db)
