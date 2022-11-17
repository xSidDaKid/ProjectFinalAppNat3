package com.example.quizzer.domaine.interacteur

class VerificationReponseCreationQuiz {

    fun verificationReponseCreationQuiz(choixBrut: String, reponses: String) : Boolean {
        var choix = choixBrut.split(",")
        var premierFiltre = reponses.split(",")
        var réponsesTriés: List<String> = emptyList()

        for (item in premierFiltre) {
            var deuxièmeFiltre = item.split(":")
            réponsesTriés += deuxièmeFiltre[1]
        }
        for (choi in choix){
            for ( reponse in réponsesTriés){
                if( reponse.lowercase() != choi.lowercase()){
                    return false
                }
            }
        }
        return true
    }
}