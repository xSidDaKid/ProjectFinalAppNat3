package com.example.quizzer.presentation

import com.example.quizzer.accesAuxDonnées.ReponsesParDefaut
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.QuizUtilisateurScore
import com.example.quizzer.domaine.interacteur.ObtenirReponses
import com.example.quizzer.domaine.interacteur.VerificationReponse

/**
 * Object qui permet d'intéragir avec la base de donnée
 */
object Modèle {

    private var quiz = Quiz()
    private var quizScore = QuizUtilisateurScore()
    var tourDesRéponses: Int = 0
    var reponseBrut: String = ""

    /**
     * Méthode qui permet d'initialiser un quiz
     *
     */
    fun initialiserQuizParDefaut() {
        quiz.reponses = ObtenirReponses().obtenirReponses(ReponsesParDefaut())
        quiz.choix = listOf<String>("Jaune", "Rouge", "Orange", "Vert")
        quiz.titre = "Les fruits et leurs couleurs"
        quiz.question = "Quelle est la couleur du fruit?"
    }

    /**
     * Méthode qui permet de valider une réponse et de changer le score de l'utilisateur
     *
     * @param reponse La réponse de l'utilisateur
     * @param index
     * @return
     */
    fun soumettreRéponse(reponse: String, index: Int): Boolean {
        var verification = VerificationReponse().verificationReponse(reponse, index, quiz)
        if (verification) {
            quizScore.score++
        }
        return verification
    }

    /**
     * Méthode qui permet d'ajouter un nouveau quiz à la base de donnée
     *
     * @param titre
     * @param question
     * @param choix
     * @param reponse
     * @return
     */
    fun ajouterQuiz(
        titre: String,
        question: String,
        choix: String,
        reponse: String
    ): String {
        var reponseTrier = getReponseTrier(reponse)
        //A ajouter dans la BD
        return titre
    }

    /**
     * Méthode qui permet de mettre réponse dans un tableau (Map)
     *
     * @param reponseBrut Réponse brut du créateur du quiz avec les , et :
     * @return
     */
    fun getReponseTrier(reponseBrut: String): List<Map<String, String>> {
        return ObtenirReponses().trierReponses(reponseBrut)
    }

    fun getProchaineRéponse(): String {
        var réponse = quiz.reponses.get(tourDesRéponses)
        tourDesRéponses++
        var réponseString = réponse.toList().get(0).first
        return réponseString
    }

    fun getIndexRéponse(): Int {
        return tourDesRéponses
    }

    fun getRéponseParIndex(index: Int): Map<String, String> {
        return quiz.reponses.get(index)
    }

    fun getReponses(): List<Map<String, String>> {
        return quiz.reponses
    }

    fun getChoix(): List<String> {
        return quiz.choix
    }

    fun getTitre(): String {
        return quiz.titre
    }

    fun getQuestion(): String {
        return quiz.question
    }

    fun getScore(): Int {
        println(quizScore.score)
        return quizScore.score
    }

}