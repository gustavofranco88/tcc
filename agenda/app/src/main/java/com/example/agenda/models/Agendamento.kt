package com.example.agenda.models

data class Agendamento (
    var nome: String,
    var telefone: String,
    var servico: String,
    var data: String,
    var hora: String,
) {
    fun addAll(body: List<Agendamento>) {

    }
}
