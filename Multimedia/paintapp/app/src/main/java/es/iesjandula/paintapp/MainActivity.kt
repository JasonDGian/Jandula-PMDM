package es.iesjandula.paintapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // --------  BLOQUE DE CONFIGURACIÓN DE LA TOOLBAR.

        // Configura la Actionbar personalizada..
        val toolbar: Toolbar = findViewById(R.id.include_toolbar)
        val flechaRetroceso = toolbar.findViewById<ImageView>(R.id.flechaRetroceso)
        setSupportActionBar(toolbar)

        // Especifica que hacer con la flecha del iccono de retroceso.s
        flechaRetroceso.setOnClickListener{
            finish()
        }

        // Configura un icono de menu personalizado.
        val iconoMenu = ContextCompat.getDrawable(this, R.drawable.baseline_menu_24)
        // Asigna el icono al menu.
        toolbar.setOverflowIcon(iconoMenu);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --------------------------------------------------

        // --------  BLOQUE DE CONFIGURACIÓN DE FUNCIONES DE PINTADO

        var lienzo = findViewById<MyCanvas>(R.id.lienzo)
        var botonBorrar = findViewById<Button>(R.id.btnBorrarTodo)
        var botonAumentar = findViewById<Button>(R.id.btnAumentar)
        var botonDisminuir = findViewById<Button>(R.id.btnDisminuir)
        var botonReinicaPincel = findViewById<Button>(R.id.btnReiniciaPincel)

        botonBorrar.setOnClickListener {
            lienzo.clearCanvas()
        }
        botonDisminuir.setOnClickListener {
            lienzo.disminuyePincel()
        }
        botonAumentar.setOnClickListener {
            lienzo.aumentaPincel()
        }
        botonReinicaPincel.setOnClickListener {
            lienzo.reiniciaPincel()
        }
    }

    //  ----------------  FUNCIONES DE NAVEGACION DE TOOLBAR ------------------

    // Infla el menú de la Toolbar con el menú indicado .
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    // Maneja los eventos de selección de los ítems del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // al hacer click en la primera opcion.
            R.id.item1 -> {
                // Lógica de la opción de ajustes
                Toast.makeText(this, "1 clicked", Toast.LENGTH_SHORT).show()
                // Recoge el Intent de la activity en una variable.
                //val primeraActivity = Intent( this, PrimeraActivity::class.java )
                // Lanza el intent con startActivity
                //startActivity(primeraActivity)
                true
            }
            // al hacer click en la segunda opcion.
            R.id.item2 -> {
                // Lógica de la opción de búsqueda
                Toast.makeText(this, "2 clicked", Toast.LENGTH_SHORT).show()

                // Recoge el Intent de la activity en una variable.
                //val segundaActivity = Intent(this, SegundaActivity::class.java)
                // Lanza el intent con startActivity
                //startActivity(segundaActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

