# 📌 Almacenar archivos en la memoria internad del dispositivo.

**OutputStreamWriter:**     
Es una clase en Kotlin (y en Java) que se utiliza para escribir caracteres en un flujo de salida (output stream) y es especialmente útil para escribir datos en archivos. Funciona como un "adaptador" entre el flujo de salida de bytes (binario) y el flujo de caracteres (texto), convirtiendo datos de caracteres en bytes utilizando una codificación especificada (por defecto UTF-8).

**openFileOutput:**    
Es un método en Android que permite abrir o crear un archivo en el almacenamiento interno de la aplicación y obtener un flujo de salida (FileOutputStream) para escribir en él. Este método es específico de Android y se utiliza para manejar archivos en el almacenamiento privado de la aplicación, al cual solo esta tiene acceso. Esto garantiza que los datos estén seguros y que otras aplicaciones no puedan leerlos ni modificarlos.


**Funcion que guarda el fichero en el almacenamiento interno:**
```kotlin
// Función que guarda el contenido de un campo de texto en un archivo.
fun guardar(nombreFichero: String) {

    // Crea un flujo de datos ded salida, abriendo un fichero existente o creando uno nuevo.
    // A este fichero solo podrá acceder esta aplicacion.
    val archivo = OutputStreamWriter(openFileOutput(nombreFichero, Activity.MODE_PRIVATE))

    // Escribe el contenido del campo de texto `txtTexto` en el archivo.
    // Convierte el texto a String antes de escribirlo.
    archivo.write(txtTexto.text.toString())

    // Asegura que los datos se escriban en el archivo inmediatamente.
    archivo.flush()

    // Cierra el archivo para liberar recursos.
    archivo.close()
}
```

**Funcion que recupera el fichero:**
```kotlin
    //Leo el fichero y lo devuelvo en una variable
    fun contenidoFichero (nombreFichero : String) : String{

        // Variable que almacenará el contenido del fichero.
        var contenido = "";

        // Variable 'archivo' donde se guarda el fichero como objeto.  
        val archivo = InputStreamReader( openFileInput( nombreFichero ) )

        // Variable Buffer Reader que lee el fichero.
        val bufferReader = BufferedReader (archivo)

        // Linea leida en el fichero
        var linea = bufferReader.readLine()

        // Mientras que linea no sea nulo, almacenar en variable de contenido.
        while (linea != null)  {
            contenido = contenido + linea+"\n"
            linea = bufferReader.readLine()
        }
        return contenido
    }
```
