package com.example.quizzer.accesAuxDonnées

import android.content.Context
import android.util.JsonReader
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.Utilisateur
import com.example.quizzer.domaine.interacteur.ObtenirReponses
import java.io.StringReader
import java.net.URL


class SourceAPI(var ctx: Context) : ISourceDeDonées {

    var urlSource = URL("http://10.0.2.2:64473/Service1.svc")

    //var urlSource = URL("https://d669f856-a4c9-423f-8375-1a565c31c4e8.mock.pstmn.io")
    var compteurID = 0
    var mapUser = emptyMap<Int, Utilisateur>()
    var mapQuiz = emptyMap<Int, Quiz>()
    var mapPermissionScore = emptyMap<Int, PermissionScore>()

    override fun obtenirReponsesBrutes(): String {
        TODO("Not yet implemented")
    }

    /**
     * Méthode qui permet d'obtenir la liste des utilisateurs
     *
     * @return Liste des utilisateurs
     */
    override fun obtenirUtilisateurs(): Map<Int, Utilisateur> {
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

    private fun reponseJsonToUser(json: String): Map<Int, Utilisateur> {
        var jsonRead = JsonReader(StringReader(json))

        jsonRead.beginArray()

        while (jsonRead.hasNext()) {
            var pair = readUserJson(jsonRead)
            mapUser += (pair.first to pair.second)
        }
        jsonRead.endArray()

        return mapUser
    }

    private fun readUserJson(jsonRead: JsonReader): Pair<Int, Utilisateur> {
        var courriel: String = ""
        var nomUtilisateur: String = ""
        var motDePasse: String = ""
        var idUtilisateur: Int = 0

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
                "idUtilisateur" -> {
                    idUtilisateur = jsonRead.nextInt()
                }
                else -> {
                    jsonRead.skipValue()
                }
            }

        }
        jsonRead.endObject()
        return Pair(
            idUtilisateur, Utilisateur(
                courriel,
                nomUtilisateur,
                motDePasse
            )
        )
    }

    /**
     * Méthode qui permet d'obtenir la liste des permissions
     *
     * @return
     */
    override fun obtenirPermissions(): Map<Int, PermissionScore> {
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

    fun reponseJsonToPermission(json: String): Map<Int, PermissionScore> {
        var jsonRead = JsonReader(StringReader(json))

        jsonRead.beginArray()

        while (jsonRead.hasNext()) {
            var pair = readPermissionJson(jsonRead)
            mapPermissionScore += (pair.first to pair.second)
        }
        jsonRead.endArray()

        return mapPermissionScore
    }

    private fun readPermissionJson(jsonRead: JsonReader): Pair<Int, PermissionScore> {
        var idQuiz: Int = 0
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
            compteurID++
        }
        jsonRead.endObject()

        obtenirQuiz()

        return Pair(
            compteurID, PermissionScore(
                mapUser.get(idUtilisateur),
                mapQuiz.get(idQuiz),
                score

            )
        )
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
        var jsonRead = JsonReader(StringReader(json))
        jsonRead.beginArray()
        while (jsonRead.hasNext()) {
            var pair = readQuizJson(jsonRead)
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
        var idQuiz: Int = 0

        jsonRead.beginObject()
        while (jsonRead.hasNext()) {
            var cle = jsonRead.nextName()
            when (cle) {
                "choix" -> {
                    choix = jsonRead.nextString()
                }
                "idQuiz" -> {
                    idQuiz = jsonRead.nextInt()
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
            idQuiz, Quiz(
                titre,
                question,
                ObtenirReponses().trierReponses2(choix),
                ObtenirReponses().trierReponses(reponseString),
                mapUser.getValue(idCreateurQuiz)
            )
        )
    }

    /**
     * Méthode qui permet l'ajout d'un quiz à la BD
     *
     * @param quiz Le quiz a ajouté
     * @param id ID de l'utilisateur
     */
    override fun postQuiz(quiz: Quiz, id: Int) {
        var choix = quiz.getChoixPourJson()
        var id = id
        var question = quiz.question
        var reponse = quiz.getReponsePourJson()
        var titre = quiz.titre

        val queue = Volley.newRequestQueue(ctx)
        val requête = object : StringRequest(Request.Method.POST,
            urlSource.toString() + "/AddQuizParam/" + titre + "/" + choix + "/" + id + "/" + reponse + "/" + question,
            { response ->
                var strResp = response.toString()
                Log.d("API", strResp)
            },
            { error ->
                Log.d("API", "error => ${error.networkResponse.statusCode}")
            }
        ) {}
        queue.add(requête)
        Log.d("post", requête.toString())
    }

    /**
     * Méthode qui permet l'ajout d'un utilisateur à la BD
     *
     * @param quiz Le quiz a ajouté
     * @param id ID de l'utilisateur
     */
    override fun postUtilisateur(utilisateur: Utilisateur) {
        var courriel = utilisateur.courriel
        var nomUtilisateur = utilisateur.nomUtilisateur
        var motDePasse = utilisateur.motDePasse

        val queue = Volley.newRequestQueue(ctx)
        val requête = object : StringRequest(Request.Method.POST,
            urlSource.toString() + "/AddUtilisateur/" + courriel + "/" + nomUtilisateur + "/" + motDePasse,
            { response ->
                var strResp = response.toString()
                Log.d("API", strResp)
            },
            { error ->
                Log.d("API", "error => ${error.networkResponse.statusCode}")
            }
        ) {}
        queue.add(requête)
        Log.d("postUtilisateur", requête.toString())
    }

    /**
     * Méthode qui permet l'ajout d'un utilisateur à la BD
     *
     * @param quiz Le quiz a ajouté
     * @param id ID de l'utilisateur
     */
    override fun postPermissionScore(permissionScore: PermissionScore) {
        var quiz = permissionScore.quiz
        var idQuiz = 0
        var utilisateur = permissionScore.utilisateur
        var idUtilisateur = 0
        var score = permissionScore.score

        if (mapQuiz.size == 0) {
            obtenirQuiz()
        }

        for ((key, value) in mapQuiz) {
            if (quiz!!.titre == value.titre) {
                idQuiz = key
                break
            } else {
                idQuiz = key + 1
            }
        }

        for ((key, value) in mapUser) {
            if (utilisateur!!.courriel == value.courriel) {
                idUtilisateur = key
            }
        }

        val queue = Volley.newRequestQueue(ctx)
        val requête = object : StringRequest(Request.Method.POST,
            urlSource.toString() + "/AddPermission/" + idQuiz + "/" + idUtilisateur + "/" + score,
            { response ->
                var strResp = response.toString()
                Log.d("API", strResp)
            },
            { error ->
                Log.d("API", "error => ${error.networkResponse.statusCode}")
            }
        ) {}
        queue.add(requête)
        //Thread.sleep(100)
    }

    /**
     * Méthode qui permet de changer le score de l'utilisateur
     *
     * @param permissionScore Le score du quiz a changé
     */
    override fun updatePermissionScore(permissionScore: PermissionScore) {
        var quiz = permissionScore.quiz
        var idQuiz = 0
        var utilisateur = permissionScore.utilisateur
        var idUtilisateur = 0
        var score = permissionScore.score

        for ((key, value) in mapQuiz) {
            if (quiz == value) {
                idQuiz = key
            }
        }

        for ((key, value) in mapUser) {
            if (utilisateur == value) {
                idUtilisateur = key
            }
        }

        val queue = Volley.newRequestQueue(ctx)
        val requête = object : StringRequest(Request.Method.PUT,
            urlSource.toString() + "/UpdatePermission/" + score + "/" + idQuiz + "/" + idUtilisateur,
            { response ->
                var strResp = response.toString()
                Log.d("API", strResp)
            },
            { error ->
                Log.d("API", "error => ${error.networkResponse.statusCode}")
            }
        ) {}
        queue.add(requête)
    }


}