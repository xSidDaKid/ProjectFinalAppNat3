package com.example.quizzer.presentation.menuPrincipal

interface IContratVuePresentateurMenuPrincipal {
    interface IVueMenuPrincipal {
        fun naviguerVersCreationQuiz()
        fun naviguerVersDemarrerQuiz()
        fun naviguerVersListeQuiz()
    }

    interface IPresentateurMenuPrincipal {
        fun creerQuiz()
        fun demarrerQuiz()
    }
}