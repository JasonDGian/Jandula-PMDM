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

# 📌 Draweables

# 📌 Imagenes

# 📌 Fragmentos
