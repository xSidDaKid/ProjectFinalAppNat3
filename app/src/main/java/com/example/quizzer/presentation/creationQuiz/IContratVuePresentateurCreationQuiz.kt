package com.example.quizzer.presentation.creationQuiz

interface IContratVuePresentateurCreationQuiz {
    interface IVueCreation {
        fun naviguerVersQuiz()
        fun afficherMessage(message: String)
    }

    interface IPresentateurCreation {
        fun traiterCreationQuiz(
            titre: String,
            question: String,
            choix: String,
            reponse: String
        )
    }
}