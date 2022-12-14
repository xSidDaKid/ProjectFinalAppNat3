package com.example.quizzer.presentation.permission

import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.Utilisateur

interface IContratVuePresentateurPermission {

    interface IVuePermission {
        fun montrerDialog(position: Int)
        fun initialiserListeQuiz(liste: Array<Quiz>?)
        fun afficherLoading()
        fun creerPermission(position: Int, user: Utilisateur)
    }

    interface IPresentateurPermission {
        //fun getTousPermissionsList(): Array<Pair<String, PermissionScore>>
        //fun getPermission(position: Int): PermissionScore
        fun dialogPermission(position: Int)
        fun ajoutPermission(quiz: Quiz, utilisateur: Utilisateur)
        fun findQuizChoisi(position: Int): Quiz
        fun findUserChoisi(email: String, position: Int)
    }
}