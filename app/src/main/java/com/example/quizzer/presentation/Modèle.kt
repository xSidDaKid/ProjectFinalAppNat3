package com.example.quizzer.presentation

import com.example.quizzer.accesAuxDonnées.ReponsesParDefaut
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.interacteur.ObtenirReponses
import com.example.quizzer.domaine.interacteur.VerificationReponse

object Modèle {

    private var quiz = Quiz()


    fun initialiserQuizParDefaut() {
        quiz.reponses = ObtenirReponses().obtenirReponses(ReponsesParDefaut())
        quiz.choix = listOf<String>("Jaune", "Rouge", "Orange", "Vert")
        quiz.titre = "Les fruits et leurs couleurs"
        quiz.question = "Quel est la couleur du fruit"
    }

    fun soumettreRéponse(reponse: String, index: Int) : Boolean {
        var verification = VerificationReponse().verificationReponse(reponse, index, quiz)
        return verification
    }

    fun getReponses() :  List<Map<String, String>> {
        return quiz.reponses
    }

    fun getChoix() : List<String>{

        return quiz.choix
    }

    fun getTitre() : String{

        return quiz.titre
    }

    fun getQuestion() : String{

        return quiz.question
    }

    //Score à implementer


}