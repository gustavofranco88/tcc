package com.example.agenda.widgets.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.databinding.ItemAgendamentoBinding
import com.example.agenda.models.Agendamento

class AgendamentosAdaptersRecycler (

    var agendamentos: List<Agendamento>,
    var evento: AgendamentosAdaptersRecycler.Evento
) : RecyclerView.Adapter<AgendamentosAdaptersRecycler.ViewHolder>() {

     /**
     * Carrega o layout do adapter
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAgendamentoBinding.inflate(layoutInflater, parent, false)  //ItemContatoBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    /**
     * Preenche o ViewHolder com os elementos da lista
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val agendamento = agendamentos[position]
        holder.binding.agendamento = agendamento
        holder.binding.textViewNome.text = agendamento.nome
        holder.binding.textViewServico.text = agendamento.servico


        holder.binding.cardListaAgendamento.setOnClickListener{
            evento.onCompartilharClick(agendamento)
        }
    }

    /**
     * Retorna o número de itens da lista - calcula a altura do recycler vuew
     */
    override fun getItemCount(): Int {
        return agendamentos.size
    }

    // Eventos disparados pelo item
    interface Evento {
        fun onCompartilharClick(agendamento: Agendamento)
    }

    // Classe do ViewHolder que armazena os itens de layout do recycler
    // view - representa cada um dos itens de linha
    data class ViewHolder(var binding: ItemAgendamentoBinding) : RecyclerView.ViewHolder(binding.root)

}
