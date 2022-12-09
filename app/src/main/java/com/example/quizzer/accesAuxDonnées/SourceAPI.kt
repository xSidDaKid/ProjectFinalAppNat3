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
import java.security.Permission


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

    /**
     * Méthode qui permet d'obtenir la liste des permissions
     *
     * @return
     */
    override fun obtenirPermissions(): MutableList<PermissionScore> {
        val queue = Volley.newRequestQueue(ctx)
        val promesse: RequestFuture<String> = RequestFuture.newFuture()
        val requête = StringRequest(
            Request.Method.GET,
            urlSource.toString() + "/Permission",
            promesse,
            promesse
        )
        queue.add(requête)
        return reponseJsonToPermission(promesse.get())
    }

    fun reponseJsonToPermission(json: String): MutableList<PermissionScore> {
        var list = mutableListOf<PermissionScore>()
        var jsonRead = JsonReader(StringReader(json))

        jsonRead.beginArray()

        while (jsonRead.hasNext()) {
            //list.add(readPermissionJson(jsonRead))
        }
        jsonRead.endArray()

        return list
    }

    private fun readPermissionJson(jsonRead: JsonReader): String {
      /*  var idQuiz: Int = 0
        var idUtilisateur: Int = 0
        var score: Int = 0

        jsonRead.beginObject()
        while (jsonRead.hasNext()) {
            var cle = jsonRead.nextName()
            when (cle) {
                "idQuiz" -> {
                    idQuiz = jsonRead.nextInt()
                }
                "idUtilisateur" -> {
                    idUtilisateur = jsonRead.nextInt()
                }
                "score" -> {
                    score = jsonRead.nextInt()
                }
                else -> {
                    jsonRead.skipValue()
                }
            }

        }
        jsonRead.endObject()
        return PermissionScore(
            idQuiz,
            idUtilisateur,
            score

        )*/
        return ""
    }

    /**
     * Méthode qui permet d'obtenir la liste des quiz
     *
     * @return Liste des quiz
     */
    override fun obtenirQuiz(): Map<Int, Quiz> {
        val queue = Volley.newRequestQueue(ctx)
        val promesse: RequestFuture<String> = RequestFuture.newFuture()
        val requête =
            StringRequest(Request.Method.GET, urlSource.toString() + "/Quiz", promesse, promesse)
        queue.add(requête)
        return reponseJsonToQuiz(promesse.get())
    }

    fun reponseJsonToQuiz(json: String): Map<Int, Quiz> {
        var list = mutableListOf<Quiz>()
        var jsonRead = JsonReader(StringReader(json))
        var mapQuiz = emptyMap<Int, Quiz>()

        jsonRead.beginArray()

        while (jsonRead.hasNext()) {
            var pair = readQuizJson(jsonRead)
            //list.add(readQuizJson(jsonRead))
            mapQuiz += (pair.first to pair.second)
        }
        jsonRead.endArray()

        return mapQuiz
    }

    fun readQuizJson(jsonRead: JsonReader): Pair<Int, Quiz> {
        var choix: String = ""
        var question: String = ""
        var reponseString: String = ""
        var titre: String = ""
        var idCreateurQuiz: Int = 0

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
                "idCreateurQuiz" -> {
                    idCreateurQuiz = jsonRead.nextInt()
                }
                else -> {
                    jsonRead.skipValue()
                }
            }

        }
        jsonRead.endObject()
        return Pair(
            idCreateurQuiz, Quiz(
                titre,
                question,
                ObtenirReponses().trierReponses2(choix),
                ObtenirReponses().trierReponses(reponseString)
            )
        )
    }

}