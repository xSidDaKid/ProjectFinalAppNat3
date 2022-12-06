package com.example.quizzer.accesAuxDonnées

import android.content.Context
import com.example.quizzer.domaine.entité.Utilisateur
import java.net.URL

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.google.gson.Gson
import java.io.StringReader


class SourceAPI (var ctx:Context):ISourceDeDonées {
    var urlSource = URL("https://487aacd5-9898-4c1e-b324-85824c2bb312.mock.pstmn.io")
    override fun obtenirReponsesBrutes(): String {
        TODO("Not yet implemented")
    }

    override fun obtenirUtilisateurs(): MutableList<Utilisateur> {
        TODO("Not yet implemented")
    }

    override fun obtenirPermissions(): MutableList<PermissionScore> {
        val queue = Volley.newRequestQueue(ctx)
        val promesse: RequestFuture<String> = RequestFuture.newFuture()
        val requête = StringRequest(Request.Method.GET, urlSource.toString()+"/Utilisateur", promesse, promesse)
        queue.add(requête);


            return reponseJsonToPermission(promesse.get())



    }

    fun reponseJsonToPermission(json:String):MutableList<PermissionScore>{
        var list = mutableListOf<PermissionScore>()
        var gson = Gson()

        list = gson.fromJson(json, mutableListOf<PermissionScore>()::class.java)

        return list
    }

    override fun obtenirQuiz(): MutableList<Quiz> {
        val queue = Volley.newRequestQueue(ctx)
        val promesse: RequestFuture<String> = RequestFuture.newFuture()
        val requête = StringRequest(Request.Method.GET, urlSource.toString()+"/Quiz", promesse, promesse)
        queue.add(requête);


        return reponseJsonToQuiz(promesse.get())



    }

    fun reponseJsonToQuiz(json:String):MutableList<Quiz>{
        var list = mutableListOf<Quiz>()
        var gson = Gson()

        list = gson.fromJson(json, mutableListOf<Quiz>()::class.java)

        return list
    }

}