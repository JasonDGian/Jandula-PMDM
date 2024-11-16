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
# 📌 Colecciones
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

# 📌 Funciones con retorno
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

# 📌 Draweables

# 📌 Imagenes

# 📌 Fragmentos
