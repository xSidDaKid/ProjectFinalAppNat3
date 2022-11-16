package com.example.quizzer.presentation.quiz

import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz.*

/**
 * Classe Présenteur qui permet de mettre à jour la vue Quiz
 *
 * @property modèle Objet qui permet de recevoir les données de l'API
 * @property vue Vue Quiz
 */
class PrésentateurQuiz(var modèle: Modèle, var vue: IVueQuiz = VueQuiz()) : IPrésentateurQuiz {

/*    fun envoyerChoix(): List<String> {
        return modèle.getChoix()
    }*/

    fun envoyerRéponse(réponse: String, indexRéponse: Int?,quiz:Quiz): Boolean {
        if (indexRéponse == null) {
            return modèle.soumettreRéponse(réponse, 0,quiz)
        }
        return modèle.soumettreRéponse(réponse, indexRéponse,quiz)

    }

    fun envoyerProchaineRéponse(quiz:Quiz): String {
        return modèle.getProchaineRéponse(quiz)
    }

    fun envoyerIndexRéponse(): Int {
        return modèle.getIndexRéponse()
    }


    override fun traiterRéponseSaisi(réponseChoisise: String) {
        TODO("Not yet implemented")
    }

    override fun traiterQuitterQuiz() {
        TODO("Not yet implemented")
    }


    override fun réinitialiserQuiz(): Quiz {
        return modèle.initialiserQuizParDefaut()
    }

    fun getQuestion(quiz:Quiz): String {
        return modèle.getQuestion(quiz)
    }

    fun getTitre(quiz:Quiz): String {
        return modèle.getTitre(quiz)
    }

    fun getScore(quiz:Quiz): Int {
        return modèle.getScore(quiz)
    }
}