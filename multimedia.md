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
