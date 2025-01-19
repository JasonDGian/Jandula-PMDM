# 📌 1 - Creamos los valores de colores.
En directorio `res/values/` , en el fichero `values.xml` introducimos lo siguiente:
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
    <color name="red">#FFFF0000</color>
    <color name="blue">#FF0000FF</color>
    <color name="green">#FF008000</color>
    <color name="yellow">#FFFFFF00</color>
    <color name="orange">#FFFFA500</color>
    <color name="purple">#FF800080</color>
    <color name="brown">#FFA52A2A</color>
    <color name="pink">#FFFFC0CB</color>
    <color name="gray">#FF808080</color>
    <color name="cyan">#FF00FFFF</color>
</resources>
```

# 📌 2 - Creamos una vista personalizada que representa el lienzo.
   
```kotlin
package es.iesjandula.paintapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat
import kotlin.math.abs

class MyCanvas(context: Context) : View(context) {

    /**
     * Configuración del lienzo.
     */

    // Color de fondo del lienzo
    private val colorFondo = ResourcesCompat.getColor(resources, R.color.yellow, null)

    // Canvas (lienzo) donde se dibujarán las líneas
    private lateinit var lienzo: Canvas

    // Bitmap que almacena los trazos dibujados
    private lateinit var imagenBitmap: Bitmap

    // Marco alrededor del lienzo
    private lateinit var marcoLienzo: Rect

    /** --------------------- fin configuracion lienzo ------------------------------*/

    /**
     * Configuración inicial del pincel.
     */

    // Grosor del trazo del pincel
    private var anchoPincel = 15f

    // Color del pincel
    private val colorPincel = ResourcesCompat.getColor(resources, R.color.black, null)

    // Configuración del pincel
    private val pincel = Paint().apply {
        color = colorPincel
        isAntiAlias = true // Suaviza los bordes de las líneas y formas
        isDither = true // Mejora la calidad de los degradados de color
        style = Paint.Style.STROKE // Estilo del pincel: solo trazo
        strokeWidth = anchoPincel // Grosor del trazo
        strokeCap = Paint.Cap.ROUND // Extremos redondeados en los trazos
        strokeJoin = Paint.Join.ROUND // Unión redondeada entre líneas
    }

    /** --------------------- fin configuracion pincel ------------------------------*/


    /**
     * Configuración de eventos de toque.
     */

    // "Tolerancia" al movimiento del dedo antes de registrar un trazo
    private val toleranciaMovimiento = ViewConfiguration.get(context).scaledEdgeSlop

    // Ruta que define la forma del trazo actual
    private var path = Path()

    // Coordenadas actuales del toque
    private var motionX = 0f
    private var motionY = 0f

    // Coordenadas previas del trazo
    private var currentX = 0f
    private var currentY = 0f

    /** --------------------- fin configuracion eventos toque ------------------------------*/


    // Se ejecuta cuando el tamaño del lienzo cambia o se inicializa
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Libera el bitmap anterior si ya estaba inicializado
        if (::imagenBitmap.isInitialized) imagenBitmap.recycle()

        // Crea un nuevo bitmap con las dimensiones del lienzo
        imagenBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        lienzo = Canvas(imagenBitmap)

        // Establece el color de fondo del lienzo
        lienzo.drawColor(colorFondo)

        // Define un marco alrededor del lienzo con un margen de 15 píxeles
        val inset = 15
        marcoLienzo = Rect(inset, inset, w - inset, h - inset)
    }

    // Dibuja el contenido del lienzo
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Dibuja el bitmap en el lienzo
        canvas.drawBitmap(imagenBitmap, 0f, 0f, null)
        // Dibuja el marco alrededor del lienzo
        canvas.drawRect(marcoLienzo, pincel)
    }

    // Maneja los eventos táctiles (toques del usuario)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // controla si el evento es nulo antes de operar.
        if (event != null) {
            motionX = event.x
            motionY = event.y
            when (event.action) {
                MotionEvent.ACTION_DOWN -> touchStart() // Inicio de un trazo
                MotionEvent.ACTION_MOVE -> touchMove() // Movimiento del trazo
                MotionEvent.ACTION_UP -> touchUp() // Finalización del trazo
            }
            return true
        }
        // Retorna false si el evento es null
        return false
    }

    // Resetea el camino actual cuando el trazo termina
    // Cuando el usuario levanta el dedo esta función hará que el path de dibujo se reinicie.
    private fun touchUp() {
        path.reset()
    }


    // Actualiza el trazo mientras el usuario mueve el dedo
    // Esta funcion es para cuando el usuario está moviendo el dedo sin despegarlo de la pantalla.
    private fun touchMove() {
        val dx = abs(motionX - currentX) // Recoge el valor absoluto.
        val dy = abs(motionY - currentY) // Recoge el valor absoluto.
        if (dx >= toleranciaMovimiento || dy >= toleranciaMovimiento) {
            // Suaviza el movimiento del trazo utilizando un punto intermedio
            path.quadTo(currentX, currentY, (motionX + currentX) / 2, (motionY + currentY) / 2)
            currentX = motionX
            currentY = motionY
            // Dibuja el trazo en el canvas
            lienzo.drawPath(path, pincel)
        }
        // Redibuja la vista para reflejar los cambios
        invalidate()
    }

    // Inicia un nuevo trazo al tocar la pantalla
    private fun touchStart() {
        path.reset()
        path.moveTo(motionX, motionY) // Define el punto inicial del trazo
        currentX = motionX
        currentY = motionY
    }
}
```


# 📌 3 - Introducimos la vista en el XML que define el layout del main activity.

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <es.iesjandula.paintapp.MyCanvas
        android:id="@+id/lienzo"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />


</androidx.constraintlayout.widget.ConstraintLayout>
```

# 📌 4 - Configuramos el main activity para que utilice el layout en modo correcto.
```kotlin
package es.iesjandula.paintapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //Nos creamos una variable de tipo canvas
        val myCanvas = MyCanvas(this)

        setContentView(myCanvas)

    }
}
``


# 📌 5 - 



estructura basica de pintura
funcion de aumentar y disminuir tamaño de pincel
cambiar color de pincel
