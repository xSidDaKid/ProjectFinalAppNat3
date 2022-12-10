package com.example.quizzer.presentation.menuPrincipal

import com.example.quizzer.domaine.entité.Utilisateur

/**
 * Interface qui permet à la vue de communiquer avec le présentateur et au présentateur de communiquer avec le modèle
 * Vue <--> Présentateur ET Présentateur <--> Modèle
 */
interface IContratVuePresentateurMenuPrincipal {
    interface IVueMenuPrincipal {
        fun naviguerVersLogin()
        fun naviguerVersCreationQuiz()
        fun naviguerVersListeQuiz()
        fun naviguerVersVoirPermission()
        fun naviguerVersListeScore()
        fun obtenirNomUtilisateur()
    }

    interface IPresentateurMenuPrincipal {
        fun seDeconnecter()
        fun creerQuiz()
        fun voirPermissions()
        fun demarrerListeQuiz()
        fun voirScore()
        fun getNomUtilisateur(): String
    }
}