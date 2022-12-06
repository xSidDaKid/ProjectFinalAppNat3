package com.example.quizzer.presentation.score


import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.score.IContratVuePresentateurScore.IPresentateurScore
import com.example.quizzer.presentation.score.IContratVuePresentateurScore.IVueScore


class PresentateurScore(var modèle:Modèle,var vue:IVueScore): IPresentateurScore{

    override fun getListePermission(): MutableList<PermissionScore> {
        return modèle.getListePermissionParEmail()
    }

}