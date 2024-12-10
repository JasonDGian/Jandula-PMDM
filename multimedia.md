# 📌 Android multimedia.

- Canvas: es una clase lienzo de dibujo, en este lienzo se puede pintar  y hace uso de otra clase llamada 'Paint'.  
- Paint: -> Viene a ser una clase con metodos de pintura, como un pincel.
  - Tiene distintas propiedades como , color, isAntiAlias, isDither, style, strokeWidth, strokeCap, strokeJoin.
- Bitmap: -> Para generar un mapa de bits.
- Path: Sirve para definir segmentos de lineas y curvas
- SurfaceView -> clase.

## Funciones sobre-escritas de view.
- Funcion onDraw -> Se encarga de pintar.
- onSizeChanged -> Funcion que se ejecuta al cambiar de tamaño de lienzo y la primera.


-> Crear vista generica en pantalla -> Crear clase Canvas, 

Para dibujar en android hace falta lo siguiente:
1. Una clase Bitmap o View.
2. Un canvas
3. Comandos de dibujo
4. Definicion de paint para describir como se pinta.

# Paso 1 - 
https://medium.com/over-engineering/getting-started-with-drawing-on-the-android-canvas-621cf512f4c7
```kotlin
class CustomView @JvmOverloads constructor(context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {


    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // DRAW STUFF HERE
    }
}
```



# Reproductores video y audio.

Para reproducir recursos de internet debemos explicitar la necesidad del permiso. 
```xml
<uses-permission android:name="android.permission.INTERNET" />
```


para reprodudicr ficheros almacenados en `res/raw`

```kotlin
var mediaPlayer = MediaPlayer.create(context, R.raw.sound_file_1)
mediaPlayer.start() // no need to call prepare(); create() does that for you
```

Así es como puedes reproducir desde un URI disponible localmente en el sistema (la cual obtuviste mediante un agente de resolución de contenido, por ejemplo):
```kotlin
val myUri: Uri = .... // initialize Uri here
val mediaPlayer = MediaPlayer().apply {
    setAudioAttributes(
        AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()
    )
    setDataSource(applicationContext, myUri)
    prepare()
    start()
}
```

La reproducción desde una URL remota a través de la transmisión HTTP tiene el siguiente aspecto:
```kotlin
val url = "http://........" // your URL here
val mediaPlayer = MediaPlayer().apply {
    setAudioAttributes(
        AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()
    )
    setDataSource(url)
    prepare() // might take long! (for buffering, etc)
    start()
}
``` 


