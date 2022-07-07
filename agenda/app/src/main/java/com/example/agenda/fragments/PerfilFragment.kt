package com.example.agenda.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.agenda.databinding.FragmentPerfilBinding
import com.example.agenda.models.Agendamento
import com.example.agenda.models.Usuário
import com.example.agenda.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilFragment : Fragment() {


    private lateinit var mBinding: FragmentPerfilBinding
    private lateinit var novoUsuário: Usuário

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentPerfilBinding.inflate(inflater, container, false)

        mBinding.btnCadastrar.setOnClickListener {

            novoUsuário = Usuário(nome = "",  senha = "")

            novoUsuário.nome = mBinding.textinputlayoutNome.toString()
            novoUsuário.senha = mBinding.textinputlayoutSenha.toString()




            val call = NetworkManager.service.novoUsuario(novoUsuário)

            call.enqueue(object : Callback<List<Usuário>?> {

                override fun onResponse(
                    call: Call<List<Usuário>?>,
                    response: Response<List<Usuário>?>
                ) {
                    //if (response.isSuccessful) {
                    tratarResposta(response.body())
                    //} else if (response.code() == 500) {
                    //  Log.d("TAG", "ERRO: ${response.message()}")
                }


                override fun onFailure(call: Call<List<Usuário>?>, t: Throwable) {
                    Toast.makeText(context, "Adicionado com sucesso", Toast.LENGTH_SHORT).show()

                }

            })
        }

        return mBinding.root
    }

    private fun tratarResposta(body: List<Usuário>?) {
        novoUsuário.addAll(body!!)
        Toast.makeText(context, "Adicionado com sucesso", Toast.LENGTH_SHORT).show()
    }

    /**
     * Método estático para auxiliar na criação do Fragmento
     * Pertence a classe e não aos objetos
     */
    companion object {
        @JvmStatic
        fun newInstance(): PerfilFragment = PerfilFragment()
    }


}