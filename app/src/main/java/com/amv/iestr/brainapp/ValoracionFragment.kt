package com.amv.iestr.brainapp

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.amv.iestr.brainapp.databinding.FragmentInicioBinding
import com.amv.iestr.brainapp.databinding.FragmentResultadoBinding
import com.amv.iestr.brainapp.databinding.FragmentSumaBinding
import com.amv.iestr.brainapp.databinding.FragmentValoracionBinding
import kotlin.random.Random

class ValoracionFragment : Fragment() {
    var _enlace: FragmentValoracionBinding?=null
    val enlace
        get()=_enlace!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _enlace=FragmentValoracionBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Enviamos a ResultadoFragment un String en función si nos gusta la app o no, si pulsamos back regresamos sin datos y por ende nos aparecerá el texto Sin Valorar
        enlace.btnMeGusta.setOnClickListener{
            setFragmentResult(RESULTADO, bundleOf(RESULTADO to "Me Gusta"))
            findNavController().popBackStack()
        }
        enlace.btnNoMeGusta.setOnClickListener{
            setFragmentResult(RESULTADO, bundleOf(RESULTADO to "No me Gusta"))
            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _enlace=null
    }
}