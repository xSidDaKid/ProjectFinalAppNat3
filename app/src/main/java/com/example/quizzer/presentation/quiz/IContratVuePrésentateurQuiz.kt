package com.example.quizzer.presentation.quiz

import com.example.quizzer.domaine.entité.Quiz

/**
 * Interface qui permet à la vue de communiquer avec le présentateur et au présentateur de communiquer avec le modèle
 * Vue <--> Présentateur ET Présentateur <--> Modèle
 */
interface IContratVuePrésentateurQuiz {

    interface IVueQuiz {
        fun afficherRéponse(réponse: String)

    }

    interface IPrésentateurQuiz {
        fun envoyerRéponse(réponse: String, indexRéponse: Int?, quiz: Quiz): Boolean
        fun envoyerProchaineRéponse(quiz: Quiz): String
        fun envoyerIndexRéponse(): Int
        fun réinitialiserQuiz(): Quiz
        fun getQuestion(quiz: Quiz): String
        fun getTitre(quiz: Quiz): String
        fun getScore(quiz: Quiz): Int
    }
}