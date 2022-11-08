package com.example.quizzer.domaine.interacteur

import com.example.quizzer.domaine.entité.Quiz

class VerificationReponse {
    // Verification à determiner si on recupre la question ou non
    fun verificationReponse(reponseSaisie : String,indexQuestion: Int , quiz:Quiz) : Boolean {

        var reponse = quiz.getReponse(indexQuestion)
        if(reponseSaisie == reponse.toList().get(0).second){

            return true
        }
        return false
    }
}