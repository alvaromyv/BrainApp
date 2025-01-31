package com.amv.iestr.brainapp

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.amv.iestr.brainapp.databinding.FragmentInicioBinding
import com.amv.iestr.brainapp.databinding.FragmentResultadoBinding
import com.amv.iestr.brainapp.databinding.FragmentSumaBinding
import kotlin.random.Random

class ResultadoFragment : Fragment() {
    var _enlace: FragmentResultadoBinding?=null
    val enlace
        get()=_enlace!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _enlace=FragmentResultadoBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Recogemos el bundle con el resultado que obtenemos en ValoracionFragment
        setFragmentResultListener(RESULTADO) { requestKey, bundle ->
            // Convertimos en bundle a string, actualizamos el resultado para el usuario y ocultamos el btnValorar
            val resultado = bundle.getString(RESULTADO)
            enlace.txtResultado.text = resultado
            enlace.btnValorar.isVisible = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enlace.btnValorar.setOnClickListener{
            findNavController().navigate(ResultadoFragmentDirections.actionResultadoFragmentToValoracionFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _enlace=null
    }
}