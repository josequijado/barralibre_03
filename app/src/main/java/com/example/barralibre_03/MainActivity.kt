package com.example.barralibre_03

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Toast
import android.view.Gravity
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Enlazar los componentes con sus IDs
        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val btnAceptar = findViewById<Button>(R.id.btnAceptar)

        // Añadir el TextWatcher al EditText
        txtNombre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Si el texto tiene 3 caracteres o más, habilitar el botón
                btnAceptar.isEnabled = (s?.length ?: 0) >= 3
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Configuración del botón para mostrar el Toast al hacer clic
        btnAceptar.setOnClickListener {
            val nombre = txtNombre.text.toString().trim()

            // Crear un Toast personalizado
            val inflater = layoutInflater
            val customToastLayout: View = inflater.inflate(R.layout.custom_toast_ma, null)
            val toastText = customToastLayout.findViewById<TextView>(R.id.toastText)
            toastText.text = "Hola, $nombre"

            // Crear y configurar el Toast
            val toast = Toast(this)
            toast.duration = Toast.LENGTH_LONG
            toast.view = customToastLayout

            // Calcular la posición para que aparezca debajo del botón
            val location = IntArray(2)
            btnAceptar.getLocationOnScreen(location)
            val xOffset = 0
            val yOffset = location[1] + btnAceptar.height + 20  // Desplazamiento de 20 píxeles debajo del botón

            // Establecer la posición y mostrar el Toast
            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, xOffset, yOffset)
            toast.show()

            // Deshabilitar el botón y el campo de texto después de enviar
            btnAceptar.isEnabled = false
            txtNombre.isEnabled = false
        }


    }
}