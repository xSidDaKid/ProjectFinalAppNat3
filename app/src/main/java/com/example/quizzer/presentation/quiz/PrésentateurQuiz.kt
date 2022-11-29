package com.example.quizzer.presentation.quiz

import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz.IPrésentateurQuiz
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz.IVueQuiz

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

    /**
     * Méthode qui permet d'envoyer la réponse choisie par l'utilisateur au modèle
     *
     * @param réponse Réponse
     * @param indexRéponse Numéro de la réponse à trouver
     * @param quiz Le quiz que l'utilisateur est entrain de faire
     * @return True si c'est la bonne réponse; False si ce n'est pas la bonne réponse
     */
    override fun envoyerRéponse(réponse: String, indexRéponse: Int?, quiz: Quiz): Boolean {
        if (indexRéponse == null) {
            return modèle.soumettreRéponse(réponse, 0, quiz)
        }
        return modèle.soumettreRéponse(réponse, indexRéponse, quiz)
    }

    /**
     * Méthode qui permet d'avoir la prochaine réponse
     *
     * @param quiz Le quiz que l'utilisateur est entrain de faire
     * @return La prochaine réponse
     */
    override fun envoyerProchaineRéponse(quiz: Quiz): String {
        return modèle.getProchaineRéponse(quiz)
    }

    /**
     * Méthode qui permet d'avoir l'index de la réponse où l'utilisateur est rendu
     *
     * @return L'index
     */
    override fun envoyerIndexRéponse(): Int {
        return modèle.getIndexRéponse()
    }

    /**
     * Méthode qui permet d'initialiser un quiz
     *
     * @return Le quiz choisi
     */
    override fun réinitialiserQuiz(): Quiz {
        return modèle.initialiserQuizParDefaut()
    }

    /**
     * Méthode qui permet d'avoir la question du quiz
     *
     * @param quiz Le quiz choisi par l'utilisateur
     * @return La question du quiz
     */
    override fun getQuestion(quiz: Quiz): String {
        return modèle.getQuestion(quiz)
    }

    /**
     * Méthode qui permet d'avoir le titre du quiz
     *
     * @param quiz Le quiz choisi par l'utilisateur
     * @return Le titre du quiz
     */
    override fun getTitre(quiz: Quiz): String {
        return modèle.getTitre(quiz)
    }

    /**
     * Méthode qui permet d'avoir le score de l'utilisateur
     *
     * @param quiz Le quiz choisi par l'utilisateur
     * @return Le nombre de bonne réponse
     */
    override fun getScore(quiz: Quiz): Int {
        return modèle.getScore(quiz)
    }

    /**
     * Méthode qui permet de quitter un quiz
     *
     */
    override fun quitter() {
        modèle.reinitialiserReponse()
    }

}