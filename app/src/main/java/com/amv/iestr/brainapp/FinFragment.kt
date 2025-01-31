package com.amv.iestr.brainapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.amv.iestr.brainapp.databinding.FragmentFinBinding
import com.amv.iestr.brainapp.databinding.FragmentInicioBinding
import com.amv.iestr.brainapp.databinding.FragmentOpcionesBinding
import com.amv.iestr.brainapp.databinding.FragmentSumaBinding
import java.io.Console

class FinFragment : Fragment() {
    var _enlace: FragmentFinBinding?=null
    val enlace
        get()=_enlace!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _enlace=FragmentFinBinding.inflate(inflater,container,false)
        return enlace.root
    }

    private var puntosConseguidos: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            val datos=FinFragmentArgs.fromBundle(it)
            puntosConseguidos = datos.puntos
        }

        if(puntosConseguidos==nPreguntas){ // VICTORIA
            enlace.lblFin.text = "¡Victoria!"
        }else{ // DERROTA
            enlace.lblFin.text = "Derrota :("
        }

        enlace.lblPuntos.text = puntosConseguidos.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _enlace=null
    }
}