# 📌 Almacenar archivos en la memoria internad del dispositivo.
1. Crear un DTO




**Funcion que recupera el fichero**
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
        var linea = bf.readLine()

        // Mientras que linea no sea nulo, almacenar en variable de contenido.
        while (linea != null)  {
            contenido = contenido + linea+"\n"
            linea = bf.readLine()
        }
        return contenido
    }
```
