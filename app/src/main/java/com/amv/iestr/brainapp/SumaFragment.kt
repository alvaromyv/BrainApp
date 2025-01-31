package com.amv.iestr.brainapp

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.amv.iestr.brainapp.databinding.FragmentInicioBinding
import com.amv.iestr.brainapp.databinding.FragmentSumaBinding
import kotlin.random.Random

class SumaFragment : Fragment() {
    var _enlace: FragmentSumaBinding?=null
    val enlace
        get()=_enlace!!

    private var temporizador: CountDownTimer? = null
    private var puntosConseguidos: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _enlace=FragmentSumaBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            val datos=SumaFragmentArgs.fromBundle(it)
            puntosConseguidos = datos.puntos
        }
        enlace.lblAcertadas.text = "$puntosConseguidos / $nPreguntas"

        // Generamos los números y mostramos al suma al usuario
        val n1 = generaNumero()
        val n2 = generaNumero()
        enlace.lblPregunta.text = generaSuma(n1,n2)
        // Inicializamos el contador y lo iniciamos
        iniciarTemporizador()

        enlace.btnResponder.setOnClickListener{
            //var respuesta: Int = enlace.txtRespuesta.text.toString().toInt()
            if(enlace.txtRespuesta.text.isEmpty()){ // Si el campo de texto está vacío
                Toast.makeText(context, "Introduce una respuesta.", Toast.LENGTH_SHORT).show()
            }else if(enlace.txtRespuesta.text.toString().toInt()==(n1+n2)){ // Si el número dado por el usuario coincide con el resultado de la suma
                siguientePregunta()
            }else{
                Toast.makeText(requireContext(), "Fallaste.", Toast.LENGTH_SHORT).show()
                // Si fallamos en la respuesta, acabamos la partida (FIN)
                terminarPartida()
            }
        }
    }

    private fun siguientePregunta() {
        // Aleatoriamente navegamos hacia una pregunta de opciones o de suma
        temporizador?.cancel()
        puntosConseguidos++
        Toast.makeText(requireContext(), "¡Acertada!", Toast.LENGTH_SHORT).show()
        if(puntosConseguidos==nPreguntas){ // Comprobamos si hemos llegado al Nº Preguntas, si es así acabamos la partida
            terminarPartida()
        }else{ // Sino, continuamos la partida
            if(Random.nextInt(100)>50){
                findNavController().navigate(SumaFragmentDirections.actionSumaFragmentToOpcionesFragment(puntosConseguidos))
            }else{
                findNavController().navigate(SumaFragmentDirections.actionSumaFragmentSelf(puntosConseguidos))
            }
        }
    }

    private fun terminarPartida(){
        temporizador?.cancel()
        findNavController().navigate(SumaFragmentDirections.actionSumaFragmentToFinFragment(puntosConseguidos))
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
    private fun generaSuma(n1: Int, n2: Int): StringBuilder {
        return StringBuilder().append(n1).append(" + ").append(n2).append(" = ")
    }

    private fun generaNumero(): Int {
        return Random.nextInt(numeroMax)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        temporizador?.cancel()
        _enlace=null
    }
}