# Ubicamos el recurso en raw.
En directorio `res` creamos un directorio llamado `raw` y ahi dentro ubicamos el video.

# Creamos el layout que contiene el reproductor de video.
En este caso es un videoview.

**Imports:**  
- `import android.net.Uri`
- `import android.widget.VideoView`

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <!-- VideoView ocupa la parte superior -->
    <VideoView
        android:id="@+id/video"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@+id/buttonContainer"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginBottom="16dp" />

    <!-- Contenedor de botones usando LinearLayout -->
    <LinearLayout
        android:id="@+id/buttonContainer"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:gravity="center"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:padding="16dp"
        android:layout_marginTop="16dp">

        <!-- Botón Play -->
        <Button
            android:id="@+id/btnPlay"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Play"
            />

        <!-- Botón Pausa -->
        <Button
            android:id="@+id/btnPausa"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Pausa"
            android:layout_marginStart="16dp"
            android:layout_marginEnd="16dp"
            />

        <!-- Botón Reiniciar -->
        <Button
            android:id="@+id/btnReiniciar"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Reiniciar" />

    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>

```

# Clase que contiene las instrucciones del video.
```kotlin
package com.example.videoview

import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Declare video view and buttons here, so they're accessible across methods
    private lateinit var video: VideoView
    private lateinit var botonPlay: Button
    private lateinit var botonPausa: Button
    private lateinit var botonReinicia: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main) // Correctly reference the layout resource
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the VideoView and Buttons
        video = findViewById(R.id.video)
        botonPlay = findViewById(R.id.btnPlay)
        botonPausa = findViewById(R.id.btnPausa)
        botonReinicia = findViewById(R.id.btnReiniciar)

        // Setup the video URI
        val uri: Uri = Uri.parse("android.resource://$packageName/raw/mar")
        video.setVideoURI(uri)
        video.requestFocus()

        // Start the video
        video.resume()

        // funcion definida para poner en play.
        videoPlay()


        // Set onClick listeners for buttons
        botonPlay.setOnClickListener {
            videoPlay()
        }

        botonPausa.setOnClickListener {
            videoPause()
        }

        botonReinicia.setOnClickListener {
            video.seekTo(0) // Move the video to the start
            //video.start()  // Start playing the video again
        }
    }


    // Funcion que realiza las operaciones que conlleva poner el video en play.
    private fun videoPlay() {
        if (!video.isPlaying) {
            video.start()  // Start the video
            botonPlay.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
            botonPausa.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            botonReinicia.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            botonPlay.isActivated = false
            botonPausa.isActivated = true
        }
    }

    private fun videoPause(){
        if (video.isPlaying) {
            video.pause()
            botonPlay.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            botonPausa.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
            botonReinicia.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            botonPlay.isActivated = true
            botonPausa.isActivated = false
        }
    }
}
```
