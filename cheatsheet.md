# 📌 Tipos de datos.
```kotlin
// Enteros
val byteValue: Byte = 10
val shortValue: Short = 1000
val intValue: Int = 100000
val longValue: Long = 10000000000L

// Decimales
val floatValue: Float = 10.5f
val doubleValue: Double = 20.99

// Cadenas y chars
val letra: Char = 'A'
val saludo: String = "Hola, Kotlin"

// Interpolacion
val edad: Int = 20
val mensaje: String = "Juan tiene $edad años!"

// Buleanos.
val esVerdadero: Boolean = true
val esFalso: Boolean = false
```
# 📌 Colecciones.
```kotlin
// Lista NO mutable
val listaInmutable: List<String> = listOf("Manzana", "Banana", "Cereza")
println(listaInmutable)  // Output: [Manzana, Banana, Cereza]

// Lista mutable
val listaMutable: MutableList<String> = mutableListOf("Manzana", "Banana", "Cereza")
listaMutable.add("Durazno")  // Se puede agregar un elemento
println(listaMutable)  // Output: [Manzana, Banana, Cereza, Durazno]

// Conjnuto NO mutable
val conjuntoInmutable: Set<Int> = setOf(1, 2, 3, 3)  // El 3 duplicado será ignorado
println(conjuntoInmutable)  // Output: [1, 2, 3]

// Conjunto mutable
val conjuntoMutable: MutableSet<Int> = mutableSetOf(1, 2, 3)
conjuntoMutable.add(4)  // Se puede agregar un elemento
println(conjuntoMutable)  // Output: [1, 2, 3, 4]

// Mapa NO mutable.
val mapaInmutable: Map<String, Int> = mapOf("uno" to 1, "dos" to 2, "tres" to 3)
println(mapaInmutable)  // Output: {uno=1, dos=2, tres=3}

// Mapa mutable
val mapaMutable: MutableMap<String, Int> = mutableMapOf("uno" to 1, "dos" to 2)
mapaMutable["tres"] = 3  // Se puede agregar o modificar un par clave-valor
println(mapaMutable)  // Output: {uno=1, dos=2, tres=3}

// Arreglos o Array
val arreglo: Array<Int> = arrayOf(1, 2, 3, 4)
println(arreglo.joinToString())  // Output: 1, 2, 3, 4

arreglo[0] = 10  // Cambiar el valor en el índice 0
println(arreglo.joinToString())  // Output: 10, 2, 3, 4
```

# 📌 Crear bundles.
```kotlin
// empaqueta varias claves-valor.
val miPaquete = Bundle().apply {
    putString("EXTRA_NOMBRE", et_nombre.text.toString())
    putString("EXTRA_APELLIDO", et_apellido.text.toString())
    putString("EXTRA_DIRECCION", et_direccion.text.toString())
    putString("EXTRA_CODIGO-POSTAL", et_codigoPostal.text.toString())
}

// Leer valores empaquetados.
val nombre = miPaquete.getString("EXTRA_NOMBRE")

// Obtener el bundle de un intent, teniendo en cuenta que puede ser nulo y
// extraer un valor teniendo en cuenta que este tambien puede ser nulo.
val miPaquete = intent.extras
val nombre = miPaquete?.getString("EXTRA_NOMBRE") ?: "Nombre no especificado"
```


# 📌 Companion Object.
```kotlin
// Objecto compañero parecido a valores estaticos de otros lenguajes.
    // El concepto es que pertenece a la clase y no al instancia.
    companion object{
        const val TAG = "MainActivity"
    }
```
   
# 📌 Navegación.
Al intento deberemos especificar contexto y contenido. El contexto es la clase desde la cual invocamos la nueva actividad, y el contenido es la actividad misma que estamos invocando.
```kotlin
// Recoge el Intent de la activity en una variable.
val pantallaFelicitacionActivity = Intent(this, FelicitacionActivity::class.java)
// Lanza el intent con startActivity
startActivity(pantallaFelicitacionActivity)
```
**Cerrar vista**
```kotlin
finish()
```

# 📌 Alimentar datos entre pantallas.

**Alimenta datos a pantalla con intent.**
```kotlin
// Recogemos el intent de la nueva pantalla en una variable.
val intentNuevaPantalla = Intent(this, FelicitacionActivity::class.java)

// Enviamos los datos a la nueva pantalla.
intentNuevaPantalla.putExtra( "EXTRA_DATO", miVariableDato )
```
**Consume datos recibidos en el intent.**
```kotlin
// Asignación de valor de intent a variable.
val nombre: String = intent.extras?.getString("EXTRA_DATO").orEmpty()
```

**Pasar bundle datos**
```kotlin
// declara inicializa la variable intent con la actividad..
val intent = Intent(this, RegistroActivity::class.java)

// empaqueta varias claves-valor.
val bundle = Bundle().apply {
    putString("EXTRA_NOMBRE", et_nombre.text.toString())
    putString("EXTRA_APELLIDO", et_apellido.text.toString())
    putString("EXTRA_DIRECCION", et_direccion.text.toString())
    putString("EXTRA_CODIGO-POSTAL", et_codigoPostal.text.toString())
}
// envia el paquete al intent.
intent.putExtras(bundle)

// Llama la activity.
startActivity(intent)
```

# 📌 Funciones con retorno.
```kotlin
// Tras los parentesis de los parametros y sus tipos irá el tipo de dato retornado por la funcion.
fun nombreFuncion(parametro1: Tipo, parametro2: Tipo): TipoDeRetorno {
    // Cuerpo de la función
    return valorDeRetorno
}
```

# 📌 Dataclass - modelos.
```kotlin
package com.dasus.jasootapp.models

data class Pregunta(
    val pregunta: String,
    val respuesta1: String,
    val respuesta2: String,
    val respuesta3: String,
    val respuesta4: String,
    val correcta: Int
) {
    fun validaRespuesta(respuesta: Int): Boolean {
        return (respuesta == this.correcta)
    }
}
```

# 📌 Drawables.
**Crear nuevo Drawable:** `Clic derecho en drawable` > `New` > `Drawable resource file`    
**Ejemplo Drawable**    
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="@color/white"/>
    <stroke
        android:width="1dp"
        android:color="#000000"/>
    <corners android:radius="12dp"/>
</shape>
```

# 📌 Imagenes.


# 📌 Fragmentos.
1. **Crear fragmento nuevo:** `New` > `Fragment` > `Fragment(Blank)` > Nombrar el fragmento `NombreFragment`
    - Esto generará el fichero `activity` y el `layout` para el fragmento.
2. **Borrar comentarios genéricos.**
3. **Definir el layout y elementos del fragmento** (diseño).
4. **Posicionar el fragmento en la actividad principal** (FragmentContainerView + ID fragmento).
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/fragmentContainerButton"
        android:name="ies.jandula.ejercicioframgentosi.ButtonFragment"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</LinearLayout>
```



# 📌 Almacenamiento interno.
**Funcion de control de existencia**
```kotlin
// Función que busca comprobar si un fichero de cierto nombre ya existe en la memoria.
fun existeArchivo(archivo: String): Boolean {
    // Analiza el array de ficheros de la aplicación y busca coincidencia.
    return fileList().any { it == archivo }
}
```

**Funcion de guardado**
```kotlin
    // Función que guarda el contenido de un campo de texto en un archivo.
    fun guardarEnFichero(nombreFichero: String, contenido: String) {
        val archivo = OutputStreamWriter(openFileOutput(nombreFichero, Activity.MODE_PRIVATE))
        archivo.write(contenido)
        archivo.flush()
        archivo.close()
    }
```

**Funcion de lectura**
```kotlin
  fun leerDeFichero(nombreFichero: String): String {
        var contenidoFichero = "";
        val archivo = InputStreamReader(openFileInput(nombreFichero))
        val bufferReader = BufferedReader(archivo)
        var linea = bufferReader.readLine()

        while (linea != null) {
            contenidoFichero = contenido + linea + "\n"
            linea = bufferReader.readLine()
        }
        return contenido
    }
```
# 📌 Radio Button \ Radio Group
En los layout.xml es necesario envolver los `radio-button` en un `radio-group`.

**Referencia y llamadas a los botones desde kotlin**
```kotlin
    radioGroup.setOnCheckedChangeListener {
      _, checkedId ->
        if ( respCorrecta1.isChecked ){
            Toast.makeText(this, "Respuesta correcta 1", Toast.LENGTH_SHORT).show()
        }
        if ( respCorrecta2.isChecked ){
            Toast.makeText(this, "Respuesta correcta 2", Toast.LENGTH_SHORT).show()
        }
        if ( respCorrecta3.isChecked ){
            Toast.makeText(this, "Respuesta correcta 3", Toast.LENGTH_SHORT).show()
        }
        if ( respCorrecta4.isChecked ){
        Toast.makeText(this, "Respuesta correcta 4", Toast.LENGTH_SHORT).show()
        }
    }
```
**Funcion de reinicio de seleccion**
```kotlin
// Set the reset button to clear the RadioGroup selection
resetButton.setOnClickListener {
    radioGroup.clearCheck()
}
```

# 📌 Menu contextual inflado.
**Crea un fichero en /res/menu que contenga las entradas.**
```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
        <item
            android:id="@+id/botonFormulario"
            android:title="@string/boton_contextual_configuracion" />
        <item
            android:id="@+id/botonListado"
            android:title="@string/boton_contextual_listad" />
        <item
            android:id="@+id/botonInfo"
            android:title="@string/contextual_acerca_de"/>
</menu>
```
**Identifica el boton**
```kotlin
        // Identifica el boton para el menu contextual.
        val botonContextual = findViewById<Button>(R.id.boton_menu)
```

**Infla el menu que aparece**
```kotlin
        // configura el comportamiento del boton del menu contextual.
        botonContextual.setOnClickListener {
                view -> // vista que recibe.
            // Identifica o genera el menu emergente.
            val menuEmergente = PopupMenu(this, view)
            // Busca la vista del menu contextual y lo mete en el menu emergente creado.
            menuEmergente.menuInflater.inflate(R.menu.menu_cascada, menuEmergente.menu)
            // Aplica un comportamiento a las entradas del menu inflado.
            menuEmergente.setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.botonFormulario -> {
                        // Abre actividad Configuracion.
                        ifAConfiguracion()
                        true
                    }
                    R.id.botonListado -> {
                        // Abre actividad Listado Preguntas.
                        irAListado()
                        true
                    }
                    R.id.botonInfo ->{
                        // muestra la informacion de la aplicacion.
                        ifAInfo()
                        true
                    }
                    // Caso por defecto del when.
                    else -> false
                }
            }
            // Muestra el menu en pantalla una vez configurado.
            menuEmergente.show()
        }
```

# 📌 Funcion con retraso.
Esta funcion espera 2 segundos antes de finalizar la ejecución.
```kotlin
private fun muestraMensaje(mensaje: String) {
    findViewById<TextView>(R.id.tv_titulo2).isVisible=true
    
    Handler(Looper.getMainLooper()).postDelayed({
        findViewById<TextView>(R.id.tv_titulo2).isVisible=false
    }, 2000)
}
```

# 📌 Objetos animación.
```kotlin
// Create the ObjectAnimator to rotate the ImageView 360 degrees
val rotateAnimator = ObjectAnimator.ofFloat(findViewById<ImageView>(R.id.logo), "rotation",  10f, 0f, -10f)

rotateAnimator.duration = 2000  // Duration of one full rotation (in milliseconds)
rotateAnimator.repeatCount = ObjectAnimator.INFINITE  // Repeat infinitely
rotateAnimator.repeatMode = ObjectAnimator.REVERSE  // Reverse the direction after each cycle

rotateAnimator.start()  // Start the rotation animation
```

# 📌 Permisos de internet.
Abre el archivo AndroidManifest.xml y dentro del elemento <manifest>, agrega el siguiente permiso:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```
El permiso debe estar definido antes del elemento <application> en el archivo, como se muestra aquí:
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myapp">

    <!-- Permiso para acceder a internet -->
    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:label="@string/app_name"
        android:theme="@style/Theme.MyApp">
        <!-- Actividades y otros componentes -->
    </application>
</manifest>
```

