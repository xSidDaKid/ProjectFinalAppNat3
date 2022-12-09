package com.example.quizzer.accesAuxDonnées

import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.Utilisateur

interface ISourceDeDonées {
    // Dans le contexte ou on récupère les réponses sous forme d'un texte exclusivement
    fun postQuiz(quiz: Quiz, id: Int)
    fun obtenirReponsesBrutes(): String
    fun obtenirUtilisateurs(): Map<Int, Utilisateur>
    fun obtenirPermissions(): Map<Int, PermissionScore>
    fun obtenirQuiz(): Map<Int, Quiz>
}