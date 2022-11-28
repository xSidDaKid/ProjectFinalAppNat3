package com.example.quizzer.presentation.menuPrincipal

/**
 * Interface qui permet à la vue de communiquer avec le présentateur et au présentateur de communiquer avec le modèle
 * Vue <--> Présentateur ET Présentateur <--> Modèle
 */
interface IContratVuePresentateurMenuPrincipal {
    interface IVueMenuPrincipal {
        fun naviguerVersCreationQuiz()
        fun naviguerVersDemarrerQuiz()
        fun naviguerVersVoirPermission()
        fun attacherÉcouteurCreerQuiz()
        fun attacherÉcouteurDemarrerQuiz()
        fun attacherÉcouteurVoirPermission()
    }

    interface IPresentateurMenuPrincipal {
        fun creerQuiz()
        fun demarrerQuiz()
        fun voirPermissions()
    }
}