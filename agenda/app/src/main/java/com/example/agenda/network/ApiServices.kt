package com.example.agenda.network

import com.example.agenda.models.Agendamento
import com.example.agenda.models.Serviço
import com.example.agenda.models.Usuário
import retrofit2.Call
import retrofit2.http.*


interface ApiServices {



    @GET("todo/")
    fun listarAgendamentos(): Call<List<Agendamento>>


    @POST("todo/")
    fun agendarServiço(@Body novoAgendamento: Agendamento): Call<List<Agendamento>?>

    @POST("todo/")
    fun novoUsuario(@Body novoUsuário: Usuário): Call<List<Usuário>?>

//
//// Outro exemplo
//// http://services.koruthos.com.br/tecplus/curso_extensao/all?pagina={pagina}&tamanho={tamanho}
//@GET("curso_extensao/all/{pagina}")
//fun listarCursos(@Path("pagina") pagina: Int): Call<PacoteJSON<List<Curso?>?>?>?
//
//@POST("curso_extensao/new")
//fun inserirCurso(@Body curso: Curso?): Call<PacoteJSON<Curso?>?>?
//
//@PUT("curso_extensao")
//fun atualizarCurso(@Body curso: Curso?): Call<PacoteJSON<Curso?>?>?
//
//@DELETE("curso_extensao/{id}")
//fun removerCurso(@Path("id") id: Int): Call<PacoteJSON<Curso?>?>?
}