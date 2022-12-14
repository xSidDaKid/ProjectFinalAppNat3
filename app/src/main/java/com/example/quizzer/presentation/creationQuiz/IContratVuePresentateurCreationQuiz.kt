package com.example.quizzer.presentation.creationQuiz

import android.view.View
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.Utilisateur

/**
 * Interface qui permet à la vue de communiquer avec le présentateur et au présentateur de communiquer avec le modèle
 * Vue <--> Présentateur ET Présentateur <--> Modèle
 */
interface IContratVuePresentateurCreationQuiz {
    interface IVueCreation {
        fun naviguerVersQuiz()
        fun naviguerVersMenu()
        fun afficherMessage(message: String)
        fun attacherÉcouteurQuiz(vue: View)
        fun afficherMessageErreur(string: String)
    }

    interface IPresentateurCreation {
        fun traiterCreationQuiz(
            titre: String,
            question: String,
            choix: List<String>,
            reponse: List<String>
        )

        fun ajoutPermission()
    }
}