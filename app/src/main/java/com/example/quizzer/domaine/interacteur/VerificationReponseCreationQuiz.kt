package com.example.quizzer.domaine.interacteur

class VerificationReponseCreationQuiz {

    fun verificationReponseCreationQuiz(choixBrut: String, reponses: String): String {
        var message = ""
        var choix = choixBrut.split(",").map { it -> it.trim().lowercase() }
        var premierFiltre = reponses.split(",")
        var réponsesTriés = obtenirReponsesSansChoix(premierFiltre)
        if (choix.size != 4) {
            message = "Veuillez mettre 4 choix"
            return message
        }
        for (reponse in réponsesTriés) {
            if (!(choix.contains(reponse.lowercase()))) {
                message = "Veuillez vérifier les choix des réponses"
                return message
            }
        }
        return message
    }

    fun obtenirReponsesSansChoix(reponses: List<String>): List<String> {
        var réponsesTriés: List<String> = emptyList()
        for (item in reponses) {
            var deuxièmeFiltre = item.split(":")
            réponsesTriés += deuxièmeFiltre[1].lowercase().trim()
        }
        println(réponsesTriés)
        return réponsesTriés
    }

}