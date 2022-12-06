package com.example.quizzer.presentation.score

import com.example.quizzer.domaine.entité.PermissionScore

interface IContratVuePresentateurScore {
    interface IVueScore{

    }

    interface IPresentateurScore{

        fun getListePermission(): MutableList<PermissionScore>
    }
}