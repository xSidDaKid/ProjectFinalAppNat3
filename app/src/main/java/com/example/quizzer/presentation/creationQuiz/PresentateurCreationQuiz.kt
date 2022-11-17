package com.example.quizzer.presentation.creationQuiz

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz.IVueCreation
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz.IPresentateurCreation

class PresentateurCreationQuiz(var modele: Modèle, var vue: IVueCreation = VueCreationQuiz()) :
    IPresentateurCreation {

    override fun traiterCreationQuiz(
        titre: String,
        question: String,
        choix: String,
        reponse: String
    ) {
        //var réponsesTriés = trierReponses(reponse)

        // Demander l'utilisateur de vérifier ses réponses dans la vue
        if(!(modele.veriferQuiz(choix, reponse))){

        }
        modele.ajouterQuiz(titre, question, choix, reponse)
        vue.naviguerVersQuiz()
    }

}