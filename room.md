Documentacion room.
https://developer.android.com/training/data-storage/room?hl=es-419

1. Añadir las dependencias.
  - Añadir las librerias
  - Añadir los plugins. 
2. Crear modelos - @Entity
3. Crear repositorios - @Dao


1. Crear un esquema que contiene las tablas.
2. Crear objeto que contiene la informacion, donde cada atributo del objeto representa un campo de la tabla.
3. Crear un DAO - que viene a ser un interfaz para operaciones CRUD.


# 📌 Dependencias.
**Entradas en fichero libs.versions.toml**
```toml
[versions]
kotlin = "2.0.0"
ksp = "2.0.21-1.0.27"
roomVersion = "2.6.1"

[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
androidx-room-compiler = { module = "androidx.room:room-compiler", version.ref = "roomVersion" }
androidx-room-ktx = { module = "androidx.room:room-ktx", version.ref = "roomVersion" }

[plugins]
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
```
**Entradas en build.gradle.kts** module app
```
plugins {
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
}
```


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

**Ejemplo de especificacion con patron SINGLETON**
```kotlin
package com.dasus.jasootapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dasus.jasootapp.dao.PreguntaDao
import com.dasus.jasootapp.models.Pregunta

@Database(entities = [Pregunta::class], version = 1)
abstract class JesootDatabase: RoomDatabase() {
    // Repositorio DAO de pregunta.
    abstract fun preguntaDao(): PreguntaDao

    // Companion object para patron SINGLETON
    companion object {
        @Volatile
        private var INSTANCE: JesootDatabase? = null

        fun getDatabase(context: Context): JesootDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JesootDatabase::class.java,
                    "jesoot_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
```

# 📌 Instanciar la base de datos.
1. Crear una instancia Singleton usando un companion object.
2. Inicializar la BBDD en la clase Application.
3. Acceder al DAO desde las actividades usando el contexto de application.
   
--- 
     
1. Crear clase de aplicacion personalizada.
```kotlin
package com.dasus.jasootapp

import android.app.Application
import com.dasus.jasootapp.database.JesootDatabase

// Clase intermedia que instancia la base de datos.
class myApp: Application() {
    
    // Devuelve la base de datos.
    val database: JesootDatabase by lazy {
        JesootDatabase.getDatabase(this)
    }
}
```
2. Manifestamos la clase `manifest.xml`.
Este paso lo que hace es indicar en el manifes, el nombre que clase deseamos inicializar para las configuraciones globales.
```xml
<application
    android:name=".MyApplication"
    ... >
</application>
```
![imagen](https://github.com/user-attachments/assets/e34d412d-db2f-4c28-8bc4-94ebc3d1fc5e)


3. Realizamos las llamadas.
```kotlin
class MainActivity : AppCompatActivity() {
    private val preguntaDao by lazy {
        (application as MyApplication).database.preguntaDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Use the DAO
        lifecycleScope.launch {
            val preguntas = preguntaDao.getPreguntas().collect { list ->
                println("Preguntas: $list")
            }
        }
    }
}
```

4.  Ejemplo de recuperacion de datos en vivo (LIVE DATA).   

**Declaración de funccion en DAO**
```kotlin
// Funcion que mediante una consulta SQL devuelve, demanera
// observada, una lista de preguntas.
@Query("SELECT * FROM pregunta")
fun loadAllPreguntasLive(): LiveData<List<Pregunta>>
```


**Llamada y observacion de datos.**
```kotlin
// Este bloque de codigo "OBSERVA" una variable cuyo valor
// depende de una llamada a la base de gatos. Al actualizarse
// la base de datos se actualiza a su vez el codigo que depende de ella.
database.preguntaDao().loadAllPreguntasLive().observe(this) { listado ->
    // Save the listado value in a variable if needed.
    this.listado = listado
}

botonJugar.setOnClickListener {
    val currentListado = listado ?: emptyList() // Ensure listado is not null
    if (currentListado.size > 7) {
        irAJugar()
    } else {
        muestraError()
        muestraMensaje("¡Necesitas preguntas!")
    }
}

```

# 📌 Claves primarias compuestas.
Para definir  una clave primaria compuesta deberemos de incluir su configuración como parametro en la anotación `@Entity`.

**Ejemplo**:   
```kotlin
import androidx.room.Entity

@Entity(primaryKeys = ["usuarioId", "eventoId"])
data class Registro(
    val usuarioId: Int, // Parte de la clave primaria
    val eventoId: Int,  // Parte de la clave primaria
    val fecha: String   // Otros campos
)
```

# 📌 Relaciones entre entidades.

## 📍 Relaciones 1:N
Para implementar una relación `1:N` (uno-a-muchos) en `Room`, necesitas realizar los siguientes pasos:
 1. Definir las entidades principales y secundarias.
 2. Establecer la clave foránea (foreign key) en la entidad secundaria.
 3. Crear una clase de relación con @Relation.
 4. Definir las consultas en el DAO.



Tabla usuario.
```kotlin
@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val email: String
)
```

Tabla Tarea
```kotlin
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id"],
            childColumns = ["usuarioId"],
            onDelete = ForeignKey.CASCADE // Opcional: Borra tareas si el usuario es eliminado
        )
    ]
)
data class Tarea(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val descripcion: String,
    val completada: Boolean,
    val usuarioId: Int // Relaciona esta columna con la tabla Usuario
)
```

