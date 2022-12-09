package com.example.quizzer.presentation.quiz

import android.view.View
import com.example.quizzer.domaine.entité.Quiz

/**
 * Interface qui permet à la vue de communiquer avec le présentateur et au présentateur de communiquer avec le modèle
 * Vue <--> Présentateur ET Présentateur <--> Modèle
 */
interface IContratVuePrésentateurQuiz {

    interface IVueQuiz {
        fun attacherÉcouteurChoix(vue: View, quiz: Quiz)
        fun initialiserTexteBouttons(vue: View)
        fun prochaineRéponse(vue: View, quiz: Quiz)
        fun afficherQuestion(vue: View, quiz: Quiz)
        fun afficherTitre(vue: View, quiz: Quiz)
        fun afficherScore(vue: View, quiz: Quiz)
        fun attacherÉcouteurQuitter(vue: View)
    }

    interface IPrésentateurQuiz {
        fun envoyerRéponse(réponse: String, indexRéponse: Int?, quiz: Quiz): Boolean
        fun envoyerProchaineRéponse(quiz: Quiz): String
        fun envoyerIndexRéponse(): Int
        fun réinitialiserQuiz(): Quiz?
        fun getQuestion(quiz: Quiz): String
        fun getTitre(quiz: Quiz): String
        fun getScore(quiz: Quiz): Int
        fun quitter()
    }
}