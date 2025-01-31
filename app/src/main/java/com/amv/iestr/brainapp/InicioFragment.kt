package com.amv.iestr.brainapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.amv.iestr.brainapp.databinding.FragmentInicioBinding
import kotlin.random.Random

class InicioFragment : Fragment() {
    var _enlace: FragmentInicioBinding?=null
    val enlace
        get()=_enlace!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _enlace=FragmentInicioBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enlace.btnEmpezar.setOnClickListener{
            comenzarPartida()
        }
    }

    private fun comenzarPartida() {
        // Aleatoriamente navegamos hacia una pregunta de opciones o de suma
        if(Random.nextInt(100)>50){
            findNavController().navigate(InicioFragmentDirections.actionInicioFragmentToOpcionesFragment(0))
        }else{
            findNavController().navigate(InicioFragmentDirections.actionInicioFragmentToSumaFragment(0))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _enlace=null
    }
}