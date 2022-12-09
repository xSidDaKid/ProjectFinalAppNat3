package com.example.quizzer.presentation.permission

import android.app.Dialog
import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz

interface IContratVuePresentateurPermission {

    interface IVuePermission{
        fun montrerDialog(position: Int)

    }

    interface IPresentateurPermission{
        fun getTousPermissionsList(): Array<Pair<String, PermissionScore>>
        fun getPermission(position: Int): PermissionScore
        fun getListeQuiz(): Map<Int, Quiz>
        fun dialogPermission(position: Int)
        fun creerPermission(email: String, position: Int)
    }
}