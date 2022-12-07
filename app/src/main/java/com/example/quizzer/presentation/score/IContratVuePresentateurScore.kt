package com.example.quizzer.presentation.score

import com.example.quizzer.domaine.entité.PermissionScore

interface IContratVuePresentateurScore {
    interface IVueScore{
        fun initialiserListeScore(liste:MutableList<PermissionScore>?)
        fun afficherMessageErreur(s: String)
    }

    interface IPresentateurScore{

        fun getListePermission(): MutableList<PermissionScore>
    }
}