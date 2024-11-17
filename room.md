
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

## 📍 Crear la DAO - interfaz repositorio.
- Creamos una interfaz con el mismo nombre que la dataclass acabado en DAO -> `PreguntaDAO`
- Anotamos la interfaz con anotacion `@Dao`
- En el cuerpo de esta interfaz definimos los metodos de intearccion con las tablas.
  - Metodos CRUD.

**Ejemplo DAO**
```kotlin
package com.dasus.jasootapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.dasus.jasootapp.models.Pregunta
import kotlinx.coroutines.flow.Flow

@Dao
interface PreguntaDao {

    @Upsert
    suspend fun guardarPregunta(pregunta: Pregunta)

    @Delete
    suspend fun borrarPregunta(pregunta: Pregunta)

    @Query(" SELECT * FROM pregunta ORDER BY id ")
    fun obtenerPreguntas(): Flow<List<Pregunta>>
}
```

**NOTA:** Las operaciones que no recuperan datos en vivo deben precisar la palabra clave `suspend` para aprovechar las corutinas de kotlin. 

## 📍 Crear especificacion de BBDD.
- En el paquete raiz creamos nueva clase abstracta que extienda de RoomDatabase().
- Anotamos la clase con la anotacion `@Database()`.
- Dentro de la anotación añadimos la propiedad `entities` que será una lista de las entidades que la bbdd debe manejar.
- Especificamos la versión que por ahora siempre será `version = 1`
- Dentro de esta clase creamos una constante que haga referencia a los repositorios que la base de datos debe conocer.

**Ejemplo**
```kotlin
package com.dasus.jasootapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dasus.jasootapp.dao.PreguntaDao
import com.dasus.jasootapp.models.Pregunta

@Database(
    entities = [Pregunta::class], // Entidad pregunta.
    version = 1
)
abstract class JesootDatabase: RoomDatabase() {
    // Repositorio DAO de pregunta.
    abstract val dao: PreguntaDao
}
```


