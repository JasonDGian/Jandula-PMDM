package com.example.grabadoraaudio

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException

class MainActivity : AppCompatActivity() {

    // Variable para gestionar la grabadora de audio
    var grabadora: MediaRecorder? = null

    // Ruta donde se guardará la grabación
    var ruta: String? = null

    // Botones para grabar y reproducir
    private lateinit var play: Button
        private lateinit var grabar: Button

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                // Solicita los permisos necesarios para grabar audio y acceder al almacenamiento
                if (
                    (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                    (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                    (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO
                        ),
                        1000
                    )
                }

                // Inicializa los componentes de la interfaz
                initView()

                // Configura las acciones de los botones
                setOnClickListeners()
            }

            // Configura los listeners de los botones
            private fun setOnClickListeners() {
                grabar.setOnClickListener {
                    grabar(it)
                }
                play.setOnClickListener {
                    reproducir(it)
                }
            }

            // Asocia los elementos de la interfaz con las variables del código
            fun initView() {
                grabar = findViewById(R.id.grabar)
                play = findViewById(R.id.play)
            }

            // Método para iniciar o detener la grabación
            fun grabar(view: View) {
                // Si la grabadora es nula, significa que vamos a iniciar una grabación nueva
                if (grabadora == null) {
                    // Establece la ruta donde se guardará el archivo de audio
                    ruta = "/data/data/com.example.grabadoraaudio/prueba.mp3"
                    Log.d("Ruta", "Guardando en: $ruta")

                    // Configura la grabadora
                    grabadora = MediaRecorder()
                    grabadora?.apply {
                        setAudioSource(MediaRecorder.AudioSource.MIC) // Fuente: micrófono del dispositivo
                        setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP) // Formato de salida
                        setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB) // Codificación de audio
                        setOutputFile(ruta) // Ruta del archivo de salida
                    }

                    try {
                        // Prepara e inicia la grabación
                        grabadora?.prepare()
                        grabadora?.start()
                        grabar.setBackgroundColor(Color.RED) // Cambia el color del botón para indicar que está grabando
                        Toast.makeText(applicationContext, "Grabando...", Toast.LENGTH_LONG).show()
                    } catch (e: IOException) {
                        println(e)
                    }
                } else {
                    // Si la grabadora ya está activa, detenemos la grabación
                    try {
                        grabadora?.stop() // Detiene la grabación
                        grabadora?.release() // Libera los recursos de la grabadora
                        grabadora = null // Resetea la grabadora
                        grabar.setBackgroundColor(Color.BLACK) // Cambia el color del botón para indicar que no está grabando
                        Toast.makeText(applicationContext, "Fin de la grabación.", Toast.LENGTH_LONG).show()
                    } catch (e: IOException) {
                        println(e)
                    }
                }
            }

            // Método para reproducir el audio grabado
            fun reproducir(view: View) {
                val mediaPlayer = MediaPlayer()
                try {
                    // Configura el reproductor con la ruta del archivo de audio
                    mediaPlayer.setDataSource(ruta)
                    mediaPlayer.prepare()
                } catch (e: IOException) {
                    println(e)
                }
                // Inicia la reproducción
                mediaPlayer.start()
                Toast.makeText(applicationContext, "Reproduciendo audio...", Toast.LENGTH_LONG).show()
            }
}
