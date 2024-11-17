
1. Crear un esquema que contiene las tablas.
2. Crear objeto que contiene la informacion, donde cada atributo del objeto representa un campo de la tabla.
3. Crear un DAO - que viene a ser un interfaz para operaciones CRUD.


## 📍 Crear la entidad - tabla.
- crear una `dataclass` con los campos que tendrá la tabla.
- anotar la clase con @Entity
- identificar el ID de la tabla con anotacion @PrimaryKey(autogenerate = true/false)
  - Este atributo tiene que tener un valor default.

### 🔸 Clave primaria.
**ejemplo**
```kotlin
@PrimaryKey(autogenerate = true)
val id: Int = 0
```

**Ejemplo de entidad con clave primaria**
```kotlin
package com.dasus.jasootapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pregunta(
    
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val pregunta:String,
    val respuesta1:String,
    val respuesta2:String,
    val respuesta3:String,
    val respuesta4:String,
    val correcta: Int){


    fun validaRespuesta( respuesta:Int ):Boolean {
        return (respuesta == this.correcta);
    }
}
```
