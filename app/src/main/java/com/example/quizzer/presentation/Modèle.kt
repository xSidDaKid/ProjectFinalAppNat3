package com.example.quizzer.presentation

import com.example.quizzer.accesAuxDonnées.ReponsesParDefaut
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.QuizUtilisateurScore
import com.example.quizzer.domaine.entité.Utilisateur
import com.example.quizzer.domaine.interacteur.ObtenirReponses
import com.example.quizzer.domaine.interacteur.VerificationReponse

/**
 * Object qui permet d'intéragir avec la base de donnée
 */
object Modèle {

   // var quiz = Quiz()
    private var quizScore = QuizUtilisateurScore()
    private var utilisateur = Utilisateur()

    var tourDesRéponses: Int = 0
    var reponseBrut: String = ""
    //var quiz1 = object : Quiz() {}

    /*    val quiz1 = object {
            val reponses = ObtenirReponses().obtenirReponses(ReponsesParDefaut())
            val choix = listOf<String>("Jaune", "Rouge", "Orange", "Vert")
            val titre = "Les fruits et leurs couleurs"
            val question = "Quelle est la couleur du fruit?"
        }
        val list: MutableList<Int> = arr.toMutableList()*/
    var quizListe = mutableListOf<Quiz>()

    /**
     * Méthode qui permet d'initialiser un quiz
     *
     */
    fun initialiserQuizParDefaut(): Quiz {
        /*quiz.reponses = ObtenirReponses().obtenirReponses(ReponsesParDefaut())
        quiz.choix = listOf<String>("Jaune", "Rouge", "Orange", "Vert")
        quiz.titre = "Les fruits et leurs couleurs"
        quiz.question = "Quelle est la couleur du fruit?"*/
/*        quiz1 = object : Quiz() {
            override var reponses = ObtenirReponses().obtenirReponses(ReponsesParDefaut())
            override var choix = listOf<String>("Jaune", "Rouge", "Orange", "Vert")
            override var titre = "Les fruits et leurs couleurs"
            override var question = "Quelle est la couleur du fruit?"
        }*/
        var newQuiz = Quiz(
            0,
            "Les fruits et leurs couleurs",
            "Quelle est la couleur du fruit?",
            listOf<String>("Jaune", "Rouge", "Orange", "Vert"),
            ObtenirReponses().obtenirReponses(ReponsesParDefaut())
        )
        quizListe.add(newQuiz)
        println("\nQuiz: " + quizListe)
        return quizListe[0]
    }

    /**
     * Méthode qui permet de valider une réponse et de changer le score de l'utilisateur
     *
     * @param reponse La réponse de l'utilisateur
     * @param index
     * @return
     */
    fun soumettreRéponse(reponse: String, index: Int, quiz:Quiz): Boolean {
        var verification = VerificationReponse().verificationReponse(reponse, index, quiz)
        if (verification) {
            quizScore.score++
        }
        return verification
    }

    /**
     * Méthode qui permet d'ajouter un nouveau quiz à la base de donnée
     *
     * @param titre
     * @param question
     * @param choix
     * @param reponse
     * @return
     */
    fun ajouterQuiz(
        titre: String,
        question: String,
        choix: String,
        reponse: String
    ) {
        var reponseTrier = getReponseTrier(reponse)
        var choixTrier = choix.split(",")
        var newQuiz = Quiz(1, titre, question, choixTrier, reponseTrier)

        /*var newQuiz = object : Quiz() {
            override var titre = titre
            override var question = question
            override var choix = choixTrier
            override var reponses = reponseTrier
        }*/
        //A ajouter dans la BD

        quizListe.add(newQuiz)
        println("Quiz: " + quizListe.toString())

    }

    /**
     * Méthode qui permet de mettre réponse dans un tableau (Map)
     *
     * @param reponseBrut Réponse brut du créateur du quiz avec les , et :
     * @return
     */
    fun getReponseTrier(reponseBrut: String): List<Map<String, String>> {
        return ObtenirReponses().trierReponses(reponseBrut)
    }

    fun getProchaineRéponse(quiz:Quiz): String {
        var réponse = quiz.reponses.get(tourDesRéponses)
        tourDesRéponses++
        var réponseString = réponse.toList().get(0).first
        return réponseString
    }

    fun getIndexRéponse(): Int {
        return tourDesRéponses
    }

    fun getRéponseParIndex(index: Int, quiz:Quiz): Map<String, String> {
        return quiz.reponses.get(index)
    }

    fun getReponses(quiz:Quiz): List<Map<String, String>> {
        return quiz.reponses
    }

    fun getChoix(quiz:Quiz): List<String> {
        return quiz.choix
    }

    fun getTitre(quiz:Quiz): String {
        return quiz.titre
    }

    fun getQuestion(quiz:Quiz): String {
        return quiz.question
    }

    fun getScore(quiz: Quiz): Int {
        println(quizScore.score)
        return quizScore.score
    }

    fun getnomUtilisateur(): String {
        return utilisateur.nomUtilisateur
    }

    fun getPassword(): String {
        return utilisateur.motDePasse
    }
}