package com.example.quizzer.domaine.interacteur

class VerificationReponseCreationQuiz {

    fun verificationReponseCreationQuiz(choixBrut: String, reponses: String) : Boolean {
        var choix = choixBrut.split(",")
        var premierFiltre = reponses.split(",")
        var nbrChoix = 0
        var réponsesTriés = obtenirReponsesSansChoix(premierFiltre)
        for (choi in choix){
            nbrChoix++
            if(!(réponsesTriés.contains(choi.lowercase()))){
                return false
            }
        }
        if(nbrChoix != 4){
            return false
        }
        return true
    }

    fun obtenirReponsesSansChoix(reponses : List<String>): List<String>{
        var réponsesTriés: List<String> = emptyList()
        for (item in reponses) {
            var deuxièmeFiltre = item.split(":")
            réponsesTriés += deuxièmeFiltre[1].lowercase()
        }
        return réponsesTriés
    }

}