package com.amv.iestr.brainapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amv.iestr.brainapp.databinding.ActivityConfiguracionBinding

class ConfiguracionActivity : AppCompatActivity() {
    private val enlace: ActivityConfiguracionBinding by lazy {
        ActivityConfiguracionBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(enlace.root)
        enlace.txtNPreguntas.hint = nPreguntas.toString()
        enlace.txtTiempoSeg.hint = tiempoPregunta.toString()
        enlace.txtNMax.hint = numeroMax.toString()
        ViewCompat.setOnApplyWindowInsetsListener(enlace.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        enlace.panelInferior.menu.findItem(R.id.configuracionActivity)?.isVisible = false
        enlace.panelInferior.selectedItemId = R.id.configuracionActivity

        enlace.panelInferior.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.valoracionActivity -> {
                    val intent = Intent(this, ValoracionActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.mainActivity -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    override fun onStart() {
        super.onStart()
        enlace.btnGuardar.setOnClickListener{
            nPreguntas =
                if(enlace.txtNPreguntas.text.isEmpty()) enlace.txtNPreguntas.hint.toString().toIntOrNull()!!
                else enlace.txtNPreguntas.text.toString().toIntOrNull()!!
            tiempoPregunta =
                if(enlace.txtTiempoSeg.text.isEmpty()) enlace.txtTiempoSeg.hint.toString().toIntOrNull()!!
                else enlace.txtTiempoSeg.text.toString().toIntOrNull()!!
            numeroMax =
                if(enlace.txtNMax.text.isEmpty()) enlace.txtNMax.hint.toString().toIntOrNull()!!
                else enlace.txtNMax.text.toString().toIntOrNull()!!
            Toast.makeText(this, "¡Guardado correctamente!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

/*val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.contenedor_navegacion) as NavHostFragment
        val navController = navHostFragment.navController
        val panel=findViewById<BottomNavigationView>(R.id.panel_inferior)
        NavigationUI.setupWithNavController(panel,navController)*/