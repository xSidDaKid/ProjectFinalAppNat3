package com.example.quizzer.presentation.creationQuiz

import android.view.View

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