package com.amv.iestr.brainapp

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.amv.iestr.brainapp.databinding.FragmentInicioBinding
import com.amv.iestr.brainapp.databinding.FragmentOpcionesBinding
import com.amv.iestr.brainapp.databinding.FragmentSumaBinding
import java.io.Console
import kotlin.random.Random

class OpcionesFragment : Fragment() {
    var _enlace: FragmentOpcionesBinding?=null
    val enlace
        get()=_enlace!!

    private var temporizador: CountDownTimer? = null
    private var puntosConseguidos: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _enlace=FragmentOpcionesBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            val datos=OpcionesFragmentArgs.fromBundle(it)
            puntosConseguidos = datos.puntos
        }
        enlace.lblAcertadas.text = "$puntosConseguidos / $nPreguntas"
        // Inicializamos el contador y lo iniciamos
        iniciarTemporizador()
        // Obtenemos una pregunta aleatoria de la batería de preguntas para mostrarsela al usuario
        val pregunta = preguntas.random()
        enlace.lblPregunta.text = pregunta.texto
        enlace.opciones.setOnCheckedChangeListener { _ , id ->
            // Si el usuario responde con verdadero la respuesta es TRUE, sino FALSE
            val respuesta:Boolean = enlace.btnVerdadero.id==id
            if(pregunta.respuesta==respuesta){ // Si coincide la respuesta del usuario con la pregunta, continuamos el juego
                siguientePregunta()
            }else{ // Si no es correcta, acabamos la partida (FIN)
                Toast.makeText(requireContext(), "Fallaste.", Toast.LENGTH_SHORT).show()
                terminarPartida()
            }
        }
    }

    private fun iniciarTemporizador() {
        // Cancelamos por si hay algún contador anterior activado aún
        temporizador?.cancel()
        temporizador = object : CountDownTimer((tiempoPregunta.toLong()*1000), 1000) {
            override fun onTick(milisegundos: Long) {
                // Por cada segundo transcurrido (1000 milisegundos) actualizamos el tiempo restante para el usuario
                enlace.lblTiempoRestante.text = String.format("%02d", milisegundos/1000)
            }
            override fun onFinish() {
                // Si acaba el tiempo y no se ha respondido la pregunta, acabamos la partida (FIN)
                terminarPartida()
            }
        }.start()
    }

    private fun siguientePregunta() {
        // Aleatoriamente navegamos hacia una pregunta de opciones o de suma
        temporizador?.cancel()
        puntosConseguidos++
        Toast.makeText(requireContext(), "¡Acertada!", Toast.LENGTH_SHORT).show()
        if(puntosConseguidos==nPreguntas){ // Comprobamos si hemos llegado al Nº Preguntas, si es así acabamos la partida
            terminarPartida()
        }else { // Sino, continuamos la partida
            if(Random.nextInt(100)>50){
                findNavController().navigate(OpcionesFragmentDirections.actionOpcionesFragmentSelf(puntosConseguidos))
            }else{
                findNavController().navigate(
                    OpcionesFragmentDirections.actionOpcionesFragmentToSumaFragment(puntosConseguidos))
            }
        }
    }

    private fun terminarPartida(){
        temporizador?.cancel()
        findNavController().navigate(
            OpcionesFragmentDirections.actionOpcionesFragmentToFinFragment(puntosConseguidos))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        temporizador?.cancel()
        _enlace=null
    }
}