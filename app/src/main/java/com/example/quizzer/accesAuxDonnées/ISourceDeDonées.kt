package com.example.quizzer.accesAuxDonnées

import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.Utilisateur

interface ISourceDeDonées {
    // Dans le contexte ou on récupère les réponses sous forme d'un texte exclusivement
    fun obtenirReponsesBrutes() : String
    fun obtenirUtilisateurs(): MutableList<Utilisateur>
    fun obtenirPermissions(): MutableList<PermissionScore>
    fun obtenirQuiz(): MutableList<Quiz>
    fun postQuiz(quiz: Quiz, id: Int)
}