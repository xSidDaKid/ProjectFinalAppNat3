package com.example.quizzer.presentation

import com.example.quizzer.accesAuxDonnées.ReponsesParDefaut
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.QuizUtilisateurScore
import com.example.quizzer.domaine.interacteur.ObtenirReponses
import com.example.quizzer.domaine.interacteur.VerificationReponse

object Modèle {

    private var quiz = Quiz()
    private var quizScore = QuizUtilisateurScore()
    var tourDesRéponses: Int = 0

    fun initialiserQuizParDefaut() {
        quiz.reponses = ObtenirReponses().obtenirReponses(ReponsesParDefaut())
        quiz.choix = listOf<String>("Jaune", "Rouge", "Orange", "Vert")
        quiz.titre = "Les fruits et leurs couleurs"
        quiz.question = "Quelle est la couleur du fruit?"
    }

    fun soumettreRéponse(reponse: String, index: Int): Boolean {
        var verification = VerificationReponse().verificationReponse(reponse, index, quiz)
        if (verification) {
            quizScore.score++
        }
        println(verification)
        return verification
    }

    fun getProchaineRéponse(): String {
        var réponse = quiz.reponses.get(tourDesRéponses)
        tourDesRéponses++
        var réponseString = réponse.toList().get(0).first
        return réponseString
    }

    fun getIndexRéponse(): Int {
        return tourDesRéponses
    }

    fun getRéponseParIndex(index: Int): Map<String, String> {
        return quiz.reponses.get(index)
    }

    fun getReponses(): List<Map<String, String>> {
        return quiz.reponses
    }

    fun getChoix(): List<String> {
        return quiz.choix
    }

    fun getTitre(): String {
        return quiz.titre
    }

    fun getQuestion(): String {
        return quiz.question
    }

    fun getScore(): Int {
        println(quizScore.score)
        return quizScore.score
    }

    //Score à implementer


}