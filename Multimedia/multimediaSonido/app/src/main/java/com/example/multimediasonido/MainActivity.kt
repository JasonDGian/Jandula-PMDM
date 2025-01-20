package com.example.multimediasonido

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lista de ítems con recursos de sonido
        val items = listOf(
            ListItem("Sonido 1", R.raw.sound1),
            ListItem("Sonido 2", R.raw.sound2),
            ListItem("Sonido 3", R.raw.sound3),
            ListItem("Sonido 4", R.raw.sound4),
            ListItem("Sonido 5", R.raw.sound5)
        )

        // Encuentra el ListView en el layout
        val listView: ListView = findViewById(R.id.listView)

        // Establece el adaptador personalizado al ListView
        listView.adapter = CustomAdapter(this, items)
    }
}








//        // Inicializa los botones con las vistas correspondientes
//        initView()
//        // Crea el objeto MediaPlayer y carga el archivo de audio
//        mediaPlayer = MediaPlayer.create(this, R.raw.crickets)
//        // Establece los listeners para los botones
//        setOnClickListeners(this)
//    }
//
//    // Inicializa los botones referenciándolos desde el layout
//    private fun initView() {
//        buttonPlay = findViewById(R.id.buttonPlay)
//        buttonPause = findViewById(R.id.buttonPause)
//        buttonStop = findViewById(R.id.buttonStop)
//    }
//
//    // Establece los OnClickListeners para los botones
//    private fun setOnClickListeners(context: Context) {
//        // Cuando se presiona el botón de play, empieza la reproducción del audio
//        buttonPlay.setOnClickListener {
//            mediaPlayer.start()
//            // Muestra un mensaje indicando que se está reproduciendo
//            Toast.makeText(context, "Reproduciendo...", Toast.LENGTH_SHORT).show()
//        }
//
//        // Cuando se presiona el botón de pausa, pausa la reproducción del audio
//        buttonPause.setOnClickListener {
//            mediaPlayer.pause()
//            // Muestra un mensaje indicando que se ha pausado
//            Toast.makeText(context, "En pausa...", Toast.LENGTH_SHORT).show()
//        }
//
//        // Cuando se presiona el botón de stop, detiene la reproducción y reinicia el MediaPlayer
//        buttonStop.setOnClickListener {
//            mediaPlayer.stop()
//            // Reinicia el objeto MediaPlayer para poder reproducir el audio de nuevo
//            mediaPlayer = MediaPlayer.create(context, R.raw.crickets)
//            // Muestra un mensaje indicando que la reproducción ha sido detenida
//            Toast.makeText(context, "Parando...", Toast.LENGTH_SHORT).show()
//        }
//    }
// }
