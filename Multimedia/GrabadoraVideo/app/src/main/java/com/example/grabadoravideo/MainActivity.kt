package com.example.grabadoravideo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Verifica si el permiso de escritura en el almacenamiento está concedido
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Si no está concedido, solicita el permiso
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                              123
            )
            }
    }

    // Método para iniciar la grabación de video
    fun grabarVideo(view: View) {
        val REQUEST_VIDEO_CAPTURE = 1 // Código para identificar la solicitud de captura de video

        // Crea un Intent para iniciar la acción de captura de video
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { videoIntent ->
            // Verifica que haya una actividad disponible para manejar el Intent
            videoIntent.resolveActivity(packageManager)?.also {
                // Inicia la actividad de captura de video y espera el resultado
                startActivityForResult(videoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }
}
