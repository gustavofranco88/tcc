package com.example.agenda.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda.databinding.FragmentHomeBinding
import com.example.agenda.models.Agendamento
import com.example.agenda.network.NetworkManager
import com.example.agenda.widgets.adapters.AgendamentosAdaptersRecycler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    var agendamentos: MutableList<Agendamento> = ArrayList<Agendamento>()
    private lateinit var mBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
            agendamentos.clear()
            val call = NetworkManager.service.listarAgendamentos()
            call.enqueue(object : Callback<List<Agendamento>> {
                override fun onResponse(
                    call: Call<List<Agendamento>>,
                    response: Response<List<Agendamento>>
                ) {
                    tratarResposta(response.body())
            }

                override fun onFailure(call: Call<List<Agendamento>>, t: Throwable) {
                Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show()

            }

        })

        mBinding.homeRecycler.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        mBinding.homeRecycler.adapter = AgendamentosAdaptersRecycler(
            agendamentos, object : AgendamentosAdaptersRecycler.Evento {
                override fun onCompartilharClick(agendamento: Agendamento) {
                    Toast.makeText(context, agendamento.nome, Toast.LENGTH_SHORT).show()
                }

            }
        )

        return mBinding.root
    }


    private fun tratarResposta(body: List<Agendamento>?) {
        agendamentos.addAll(body!!)
        mBinding.homeRecycler.adapter?.notifyDataSetChanged()
    }


    /**
     * Método estático para auxiliar na criação do Fragmento
     * Pertence a classe e não aos objetos
     */
    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment = HomeFragment()
    }


}