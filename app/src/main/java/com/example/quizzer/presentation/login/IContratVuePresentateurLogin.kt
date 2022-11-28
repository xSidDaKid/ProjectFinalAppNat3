package com.example.quizzer.presentation.login

import android.view.View

/**
 * Interface qui permet à la vue de communiquer avec le présentateur et au présentateur de communiquer avec le modèle
 * Vue <--> Présentateur ET Présentateur <--> Modèle
 */
interface IContratVuePresentateurLogin {
    interface IVueLogin {
        fun naviguerMenu()
        fun naviguerEnregistrer()
        fun attacherÉcouteurLogin(vue: View)
        fun attacherÉcouteurEnregistrement()
    }

    interface IPresentateurLogin {
        fun traiterMenu()
        fun traiterEnregistrer()
        fun verifierConnexion(nomUtilisateur: String, password: String): Boolean
    }
}