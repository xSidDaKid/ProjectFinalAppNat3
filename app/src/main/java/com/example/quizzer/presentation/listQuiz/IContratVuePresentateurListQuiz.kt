package com.example.quizzer.presentation.listQuiz

import com.example.quizzer.domaine.entité.Quiz

/**
 * Interface qui permet à la vue de communiquer avec le présentateur et au présentateur de communiquer avec le modèle
 * Vue <--> Présentateur ET Présentateur <--> Modèle
 */
interface IContratVuePresentateurListQuiz {
    interface IVueListQuiz {
        fun initialiserListeQuiz(liste: Array<Quiz>?)
        fun attacherÉcouteurAuxQuiz()
        fun naviguerVersQuiz()
        fun afficherMessageErreur(s: String)
        fun afficherLoading()
    }

    interface IPresentateurListQuiz {
        fun getListeQuiz(): Array<Quiz>
        fun getQuiz(listePosition: Int)
        fun reinitialiserReponse()
    }
}