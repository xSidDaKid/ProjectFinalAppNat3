package com.example.quizzer.presentation.creationQuiz

import android.view.View

/**
 * Interface qui permet à la vue de communiquer avec le présentateur et au présentateur de communiquer avec le modèle
 * Vue <--> Présentateur ET Présentateur <--> Modèle
 */
interface IContratVuePresentateurCreationQuiz {
    interface IVueCreation {
        fun naviguerVersQuiz()
        fun afficherMessage(message: String)
        fun attacherÉcouteurQuiz(vue: View)
    }

    interface IPresentateurCreation {
        fun traiterCreationQuiz(
            titre: String,
            question: String,
            choix: List<String>,
            reponse: List<String>
        )
    }
}