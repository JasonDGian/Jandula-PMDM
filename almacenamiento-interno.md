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
// Función que lee el contenido de un archivo y lo devuelve como una cadena de texto.
fun contenidoFichero(nombreFichero: String): String {

    // Inicializa una variable vacía para almacenar el contenido completo del archivo.
    var contenido = ""

    // Abre el archivo en modo de lectura usando InputStreamReader.
    // `openFileInput` devuelve un flujo de entrada para el archivo especificado.
    val archivo = InputStreamReader(openFileInput(nombreFichero))

    // Crea un `BufferedReader` que mejora la eficiencia de lectura del archivo al leerlo por bloques.
    val bufferReader = BufferedReader(archivo)

    // Lee la primera línea del archivo y la almacena en la variable `linea`.
    var linea = bufferReader.readLine()

    // Bucle que continúa leyendo hasta que no haya más líneas (es decir, hasta que `linea` sea nula).
    while (linea != null) {
        // Añade la línea leída al contenido, junto con un salto de línea al final.
        contenido += linea + "\n"
        
        // Lee la siguiente línea y actualiza `linea`.
        linea = bufferReader.readLine()
    }

    // Retorna el contenido completo del archivo como una cadena.
    return contenido
}

```
