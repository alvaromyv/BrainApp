package com.amv.iestr.brainapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.amv.iestr.brainapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val enlace: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(enlace.root)
        ViewCompat.setOnApplyWindowInsetsListener(enlace.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        enlace.panelInferior.menu.findItem(R.id.mainActivity)?.isVisible = false
    }

    override fun onStart() {
        super.onStart()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.contenedor_navegacion) as NavHostFragment
        val navController = navHostFragment.navController
        val panel=findViewById<BottomNavigationView>(R.id.panel_inferior)
        NavigationUI.setupWithNavController(panel,navController)
    }
}