package com.example.quizzer.presentation.registration

import android.view.View
/**
 * Interface qui permet à la vue de communiquer avec le présentateur et au présentateur de communiquer avec le modèle
 * Vue <--> Présentateur ET Présentateur <--> Modèle
 */
interface IContratVueRegistration {
    interface IVueRegistration {
        fun naviguerLogin()
        fun attacherÉcouteurEnregistrer(vue: View)
        fun attacherÉcouteurConnexion()
    }

    interface IPrésentateurRegistration {
        fun traiterLogin(email: String, username: String, mdp: String)
        fun traiterLogin()
    }
}