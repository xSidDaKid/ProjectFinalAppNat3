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


class SourceAPI(var ctx: Context) : ISourceDeDonées {

    var urlSource = URL("http://10.0.2.2:64473/Service1.svc")
    //var urlSource = URL("https://d669f856-a4c9-423f-8375-1a565c31c4e8.mock.pstmn.io")


    override fun obtenirReponsesBrutes(): String {
        TODO("Not yet implemented")
    }

    /**
     * Méthode qui permet d'obtenir la liste des utilisateurs
     *
     * @return Liste des utilisateurs
     */
    override fun obtenirUtilisateurs(): MutableList<Utilisateur> {
        val queue = Volley.newRequestQueue(ctx)
        val promesse: RequestFuture<String> = RequestFuture.newFuture()
        val requête = StringRequest(
            Request.Method.GET,
            urlSource.toString() + "/Utilisateur",
            promesse,
            promesse
        )
        queue.add(requête)
        return reponseJsonToUser(promesse.get())
    }

    private fun reponseJsonToUser(json: String): MutableList<Utilisateur> {
        var list = mutableListOf<Utilisateur>()
        var jsonRead = JsonReader(StringReader(json))

        jsonRead.beginArray()

        while (jsonRead.hasNext()) {
            list.add(readUserJson(jsonRead))
        }
        jsonRead.endArray()

        return list
    }

    private fun readUserJson(jsonRead: JsonReader): Utilisateur {
        var courriel: String = ""
        var nomUtilisateur: String = ""
        var motDePasse: String = ""

        jsonRead.beginObject()
        while (jsonRead.hasNext()) {
            var cle = jsonRead.nextName()
            when (cle) {
                "courriel" -> {
                    courriel = jsonRead.nextString()
                }
                "motDePasse" -> {
                    motDePasse = jsonRead.nextString()
                }
                "nomUtilisateur" -> {
                    nomUtilisateur = jsonRead.nextString()
                }
                else -> {
                    jsonRead.skipValue()
                }
            }

        }
        jsonRead.endObject()
        return Utilisateur(
            courriel,
            nomUtilisateur,
            motDePasse
        )
    }

    override fun obtenirPermissions(): MutableList<PermissionScore> {
        val queue = Volley.newRequestQueue(ctx)
        val promesse: RequestFuture<String> = RequestFuture.newFuture()
        val requête = StringRequest(
            Request.Method.GET,
            urlSource.toString() + "/Permission",
            promesse,
            promesse
        )
        queue.add(requête);
        var listPerm = reponseJsonToPermission(promesse.get())
        return listPerm
    }

    fun reponseJsonToPermission(json: String): MutableList<PermissionScore> {
        var list = mutableListOf<PermissionScore>()
        Log.d("testapi", "debut15")
        var gson = Gson()
        Log.d("testapi", "debut2")
        list = gson.fromJson(json, mutableListOf<PermissionScore>()::class.java)
        Log.d("testapi", "debut3")
        return list
    }

    /**
     * Méthode qui permet d'obtenir la liste des quiz
     *
     * @return Liste des quiz
     */
    override fun obtenirQuiz(): MutableList<Quiz> {
        val queue = Volley.newRequestQueue(ctx)
        val promesse: RequestFuture<String> = RequestFuture.newFuture()
        val requête =
            StringRequest(Request.Method.GET, urlSource.toString() + "/Quiz", promesse, promesse)
        queue.add(requête)
        return reponseJsonToQuiz(promesse.get())
    }

    fun reponseJsonToQuiz(json: String): MutableList<Quiz> {
        var list = mutableListOf<Quiz>()
        var jsonRead = JsonReader(StringReader(json))

        jsonRead.beginArray()

        while (jsonRead.hasNext()) {
            list.add(readQuizJson(jsonRead))
        }
        jsonRead.endArray()

        return list
    }

    fun readQuizJson(jsonRead: JsonReader): Quiz {
        var choix: String = ""
        var question: String = ""
        var reponseString: String = ""
        var titre: String = ""

        jsonRead.beginObject()
        while (jsonRead.hasNext()) {
            var cle = jsonRead.nextName()
            when (cle) {
                "choix" -> {
                    choix = jsonRead.nextString()
                }
                "question" -> {
                    question = jsonRead.nextString()
                }
                "reponses" -> {
                    reponseString = jsonRead.nextString()
                }
                "titre" -> {
                    titre = jsonRead.nextString()
                }
                else -> {
                    jsonRead.skipValue()
                }
            }

        }
        jsonRead.endObject()
        return Quiz(
            titre,
            question,
            ObtenirReponses().trierReponses2(choix),
            ObtenirReponses().trierReponses(reponseString)
        )
    }

}