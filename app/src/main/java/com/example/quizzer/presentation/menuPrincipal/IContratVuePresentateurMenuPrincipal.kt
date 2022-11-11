package com.example.quizzer.presentation.menuPrincipal

interface IContratVuePresentateurMenuPrincipal {
    interface IVueMenuPrincipal {
        fun naviguerVersCreationQuiz()
        fun naviguerVersDemarrerQuiz()
    }

    interface IPresentateurMenuPrincipal {
        fun creerQuiz()
        fun demarrerQuiz()
    }
}