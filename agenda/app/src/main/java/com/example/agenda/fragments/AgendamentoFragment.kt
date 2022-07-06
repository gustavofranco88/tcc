package com.example.agenda.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.agenda.databinding.FragmentAgendamentoBinding
import com.example.agenda.models.Agendamento
import com.example.agenda.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgendamentoFragment : Fragment() {

    lateinit var novoAgendamento: Agendamento
    private lateinit var mBinding: FragmentAgendamentoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAgendamentoBinding.inflate(inflater, container, false)

        mBinding.btnData.setOnClickListener {
            DatePickerFragment { result ->mBinding.textViewData.text = result }
                .show(childFragmentManager,"datePicker")
        }
        mBinding.btnSalvar.setOnClickListener {

            novoAgendamento = Agendamento(
                nome = "", telefone = "", servico = "", data = "", hora = ""
            )
            novoAgendamento.nome = mBinding.editTextTextPersonNameNome.text.toString()
            novoAgendamento.telefone = mBinding.editTextPhoneAgendar.text.toString()
            novoAgendamento.servico = mBinding.editTextTextPersonName2.text.toString()
            novoAgendamento.data = mBinding.editTextTime.text.toString()
            novoAgendamento.hora = mBinding.editTextTime.text.toString()



            val call = NetworkManager.service.agendarServiço(novoAgendamento)

            call.enqueue(object : Callback<List<Agendamento>?> {

                override fun onResponse(
                    call: Call<List<Agendamento>?>,
                    response: Response<List<Agendamento>?>
                ) {
                    //if (response.isSuccessful) {
                    tratarResposta(response.body())
                    //} else if (response.code() == 500) {
                    //  Log.d("TAG", "ERRO: ${response.message()}")
                }


                override fun onFailure(call: Call<List<Agendamento>?>, t: Throwable) {
                    Toast.makeText(context, "Erro ao enviar dados", Toast.LENGTH_SHORT).show()

                }

            })
        }



        return mBinding.root
    }

//    private fun showDatePickerDialog() {
//        val datePicker = DatePickerFragment { dia, mes, ano -> onDateSelected(dia, mes, ano) }
//        datePicker.dataPicker.toString()
//        showDatePickerDialog()
//    }
//
//    private fun onDateSelected(dia: Int, mes: Int, ano: Int) {
//
//    }

    private fun tratarResposta(body: List<Agendamento>?) {
        novoAgendamento.addAll(body!!)
        Toast.makeText(context, "Adicionado com sucesso", Toast.LENGTH_SHORT).show()
    }


    /**
     * Método estático para auxiliar na criação do Fragmento
     * Pertence a classe e não aos objetos
     */
    companion object {
        @JvmStatic
        fun newInstance(): AgendamentoFragment = AgendamentoFragment()
    }


}