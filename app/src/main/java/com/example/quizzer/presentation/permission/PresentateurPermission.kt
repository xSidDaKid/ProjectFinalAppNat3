package com.example.quizzer.presentation.permission

import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IPresentateurPermission
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IVuePermission


class PresentateurPermission(var modèle:Modèle, var vue:IVuePermission = VuePermission()) : IPresentateurPermission{


    override fun getTousPermissionsList(): Array<Pair<String, PermissionScore>> {

        return modèle.getTousPermission().toList().toTypedArray()
    }

    override fun getPermission(position:Int): PermissionScore{
        var liste = modèle.getTousPermission().toList().toTypedArray()
        return liste[position].second
    }

    override fun getListeQuiz(): Array<Quiz> {
        return modèle.getListeQuiz().toTypedArray()
    }

    override fun dialogPermission(position: Int) {

        vue.montrerDialog()
    }


}