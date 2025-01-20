package com.example.multimediasonido

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// Modelo de datos para cada ítem con un título y un recurso de sonido
data class ListItem(val title: String, val soundResId: Int)

class CustomAdapter(
    private val context: Context,
    private val itemList: List<ListItem>
) : BaseAdapter() {

    // MediaPlayer compartido para todos los ítems
    private var sharedMediaPlayer: MediaPlayer? = null
    private var currentPlayingPosition: Int? = null

    override fun getCount(): Int = itemList.size

    override fun getItem(position: Int): Any = itemList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)

        // Obtén el ítem actual
        val item = itemList[position]

        // Configura los elementos del layout
        val titleTextView: TextView = view.findViewById(R.id.itemTitle)
        val buttonPlay: Button = view.findViewById(R.id.buttonOne)
        val buttonPause: Button = view.findViewById(R.id.buttonTwo)
        val buttonStop: Button = view.findViewById(R.id.buttonThree)

        // Actualiza el título
        titleTextView.text = item.title

        // Estado inicial de los botones
        buttonPause.isEnabled = currentPlayingPosition == position && sharedMediaPlayer?.isPlaying == true
        buttonStop.isEnabled = currentPlayingPosition == position

        // Configura los botones
        buttonPlay.setOnClickListener {
            // Si hay otro sonido reproduciéndose, detenerlo
            if (currentPlayingPosition != position) {
                sharedMediaPlayer?.stop()
                sharedMediaPlayer?.release()
                sharedMediaPlayer = null
                currentPlayingPosition = null
            }

            // Crear un nuevo MediaPlayer para el ítem actual
            sharedMediaPlayer = MediaPlayer.create(context, item.soundResId).apply {
                start()
            }
            currentPlayingPosition = position

            // Actualizar los estados de los botones
            notifyDataSetChanged()
            Toast.makeText(context, "${item.title}: Reproduciendo", Toast.LENGTH_SHORT).show()
        }

        buttonPause.setOnClickListener {
            if (currentPlayingPosition == position && sharedMediaPlayer?.isPlaying == true) {
                sharedMediaPlayer?.pause()
                notifyDataSetChanged() // Actualizar vista
                Toast.makeText(context, "${item.title}: En pausa", Toast.LENGTH_SHORT).show()
            }
        }

        buttonStop.setOnClickListener {
            if (currentPlayingPosition == position) {
                sharedMediaPlayer?.stop()
                sharedMediaPlayer?.release()
                sharedMediaPlayer = null
                currentPlayingPosition = null

                // Actualizar los estados de los botones
                notifyDataSetChanged()
                Toast.makeText(context, "${item.title}: Detenido", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
