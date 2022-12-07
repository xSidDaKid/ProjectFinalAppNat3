package com.example.quizzer.accesAuxDonnées

import android.content.Context
import android.util.JsonReader
import android.util.Log
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
import com.example.quizzer.domaine.interacteur.ObtenirReponses
import com.google.gson.Gson
import java.io.StringReader


class SourceAPI (var ctx:Context):ISourceDeDonées {
    var urlSource = URL("https://d669f856-a4c9-423f-8375-1a565c31c4e8.mock.pstmn.io")
    override fun obtenirReponsesBrutes(): String {
        TODO("Not yet implemented")
    }

    override fun obtenirUtilisateurs(): MutableList<Utilisateur> {
        TODO("Not yet implemented")
    }

    override fun obtenirPermissions(): MutableList<PermissionScore> {
        val queue = Volley.newRequestQueue(ctx)
        val promesse: RequestFuture<String> = RequestFuture.newFuture()
        val requête = StringRequest(Request.Method.GET, urlSource.toString()+"/Permission", promesse, promesse)
        queue.add(requête);
        var listPerm = reponseJsonToPermission(promesse.get())
        return listPerm
    }

    fun reponseJsonToPermission(json:String):MutableList<PermissionScore>{
        var list = mutableListOf<PermissionScore>()
        Log.d("testapi","debut15")
        var gson = Gson()
        Log.d("testapi","debut2")
        list = gson.fromJson(json, mutableListOf<PermissionScore>()::class.java)
        Log.d("testapi","debut3")
        return list
    }

    override fun obtenirQuiz(): MutableList<Quiz> {
        Log.d("testapi","debutquiz")

        val queue = Volley.newRequestQueue(ctx)
        Log.d("testapi","debutquiz2")

        val promesse: RequestFuture<String> = RequestFuture.newFuture()
        Log.d("testapi","debutquiz3")
        val requête = StringRequest(Request.Method.GET, urlSource.toString()+"/Quiz", promesse, promesse)
        queue.add(requête);

        Log.d("testapi","debutquiz4")

        return reponseJsonToQuiz(promesse.get())



    }

    fun reponseJsonToQuiz(json:String):MutableList<Quiz>{
//        Log.d("testapi","debutquiz5")
//
        var list = mutableListOf<Quiz>()
//        var gson = Gson()
//        Log.d("testapi","debutquiz6")
//        list = gson.fromJson(json, arrayOf<Quiz>()::class.java)
//        Log.d("testapi","debutquiz7")




        var jsonRead = JsonReader(StringReader(json))
        jsonRead.beginArray()
        while (jsonRead.hasNext()) {
            list.add(readQuizJson(jsonRead))
        }
        jsonRead.endArray()
        return list
    }

    fun readQuizJson(jsonRead: JsonReader):Quiz{
        var choix:String=""
        var question:String=""
        var reponseString:String=""
        var titre:String=""

        jsonRead.beginObject()
        while(jsonRead.hasNext()){
            var cle = jsonRead.nextName()
            when (cle){
                "choix"->{
                    choix = jsonRead.nextString()
                }
                "question"->{
                    question = jsonRead.nextString()
                }
                "reponses"->{
                    reponseString = jsonRead.nextString()
                }
                "titre"->{
                    titre = jsonRead.nextString()
                }else->{
                    jsonRead.skipValue()
                }
            }

        }
        jsonRead.endObject()
        return Quiz(titre,question,ObtenirReponses().trierReponses2(choix),ObtenirReponses().trierReponses(reponseString))
    }
}