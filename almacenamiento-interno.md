# 📌 Almacenar archivos en la memoria interna del dispositivo.
Procedimiento para guardar y cargar datos de un fichero interno.
1. Crear el layout con los elementos (botones, formularios...) que consumiran e interactuarán con el fichero.
2. Asociar (findViewById) esos elementos a unas variables en la clase Kotlin.
3. Definir las funciones de carga y guardado de datos en el fichero deseado.
4. Validar la existencia o no existencia del fichero en el almacenamiento.
5. Llamar a las funciones de carga y guardado de datos donde sea pertinente.

**OutputStreamWriter:**     
Es una clase en Kotlin (y en Java) que se utiliza para escribir caracteres en un flujo de salida (output stream) y es especialmente útil para escribir datos en archivos. Funciona como un "adaptador" entre el flujo de salida de bytes (binario) y el flujo de caracteres (texto), convirtiendo datos de caracteres en bytes utilizando una codificación especificada (por defecto UTF-8).

**openFileOutput:**    
Es un método en Android que permite abrir o crear un archivo en el almacenamiento interno de la aplicación y obtener un flujo de salida (FileOutputStream) para escribir en él. Este método es específico de Android y se utiliza para manejar archivos en el almacenamiento privado de la aplicación, al cual solo esta tiene acceso. Esto garantiza que los datos estén seguros y que otras aplicaciones no puedan leerlos ni modificarlos.




**Funcion que guarda el fichero en el almacenamiento interno:**
```kotlin
    // Función que guarda el contenido de un campo de texto en un archivo.
    fun guardarEnFichero(nombreFichero: String, contenido: String) {

        // Crea un flujo de datos ded salida, abriendo un fichero existente o creando uno nuevo.
        // A este fichero solo podrá acceder esta aplicacion.
        val archivo = OutputStreamWriter(openFileOutput(nombreFichero, Activity.MODE_PRIVATE))

        // Escribe el contenido del campo de texto `txtTexto` en el archivo.
        // Convierte el texto a String antes de escribirlo.
        archivo.write(contenido)

        // Asegura que los datos se escriban en el archivo inmediatamente.
        archivo.flush()

        // Cierra el archivo para liberar recursos.
        archivo.close()
    }
```

**Funcion que recupera el fichero:**
```kotlin
  fun leerDeFichero(nombreFichero: String): String {

        // Variable que almacenará el contenido del fichero.
        var contenido = "";

        // Variable 'archivo' donde se guarda el fichero como objeto.
        val archivo = InputStreamReader(openFileInput(nombreFichero))

        // Variable Buffer Reader que lee el fichero.
        val bufferReader = BufferedReader(archivo)

        // Linea leida en el fichero
        var linea = bufferReader.readLine()

        // Mientras que linea no sea nulo, almacenar en variable de contenido.
        while (linea != null) {
            contenido = contenido + linea + "\n"
            linea = bufferReader.readLine()
        }
        return contenido
    }
```

**Ejemplo de guardar en fichero, cargar y borrar**
```kotlin
package ies.jandula.almacenamiento1

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    // Declaramos variables que interactuaran con el fichero.
    lateinit var controladorTexto : EditText
    lateinit var recuperadorTexto : TextView // Almacena lo recuperado.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        // Inicializamos las variables de los controladores de texto.
        controladorTexto = findViewById<EditText>(R.id.editTextText)
        recuperadorTexto = findViewById<TextView>(R.id.textView3)
        val botonGuardar = findViewById<Button>(R.id.btGuardar)
        val botonCargar = findViewById<Button>(R.id.btCargar)
        val botonBorrar = findViewById<Button>(R.id.buttonBorrar)

        // si el fichero existe...
        if (existeArchivo(fileList(), "fichero.txt")) {
            // ...leer el fichero y cargarlo en el text  view.
            recuperadorTexto.text = leerDeFichero("fichero.txt")
        }

        botonBorrar.setOnClickListener {
            guardarEnFichero("fichero.txt", "")
            recuperadorTexto.text = leerDeFichero("fichero.txt")
        }

        botonGuardar.setOnClickListener {
            var contenido = leerDeFichero("fichero.txt")
            var nuevoContenido = controladorTexto.text.toString()+"\n"+contenido
            guardarEnFichero( "fichero.txt",nuevoContenido  )
            recuperadorTexto.text=leerDeFichero("fichero.txt")
            controladorTexto.text.clear()
        }

        botonCargar.setOnClickListener {
            recuperadorTexto.text = leerDeFichero("fichero.txt")
        }


    }
    // Funcion que busca comprobar si un fichero de cierto nombre ya existe en la memoria.
    fun existeArchivo(archivos: Array<String>, archivo: String): Boolean {
        archivos.forEach { arch ->
            if (arch == archivo) {
                return true
            }
        }
        return false
    }

    // Función que guarda el contenido de un campo de texto en un archivo.
    fun guardarEnFichero(nombreFichero: String, contenido: String) {

        // Crea un flujo de datos ded salida, abriendo un fichero existente o creando uno nuevo.
        // A este fichero solo podrá acceder esta aplicacion.
        val archivo = OutputStreamWriter(openFileOutput(nombreFichero, Activity.MODE_PRIVATE))

        // Escribe el contenido del campo de texto `txtTexto` en el archivo.
        // Convierte el texto a String antes de escribirlo.
        archivo.write(contenido)

        // Asegura que los datos se escriban en el archivo inmediatamente.
        archivo.flush()

        // Cierra el archivo para liberar recursos.
        archivo.close()
    }

    //Leo el fichero y lo devuelvo en una variable
    fun leerDeFichero(nombreFichero: String): String {

        // Variable que almacenará el contenido del fichero.
        var contenido = "";

        // Variable 'archivo' donde se guarda el fichero como objeto.
        val archivo = InputStreamReader(openFileInput(nombreFichero))

        // Variable Buffer Reader que lee el fichero.
        val bufferReader = BufferedReader(archivo)

        // Linea leida en el fichero
        var linea = bufferReader.readLine()

        // Mientras que linea no sea nulo, almacenar en variable de contenido.
        while (linea != null) {
            contenido = contenido + linea + "\n"
            linea = bufferReader.readLine()
        }
        return contenido
    }
}
```
