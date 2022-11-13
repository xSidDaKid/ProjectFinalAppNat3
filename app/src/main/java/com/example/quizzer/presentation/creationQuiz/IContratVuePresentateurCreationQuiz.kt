package com.example.quizzer.presentation.creationQuiz

interface IContratVuePresentateurCreationQuiz {
    interface IVueCreation {
        fun naviguerVersQuiz()
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