package com.example.quizzer.presentation.permission

import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IPresentateurPermission
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IVuePermission


class PresentateurPermission(var modèle:Modèle, var vue:IVuePermission = VuePermission()) {


    fun getTousPermissionsList(): Array<Pair<String, PermissionScore>> {

        return modèle.getTousPermission().toList().toTypedArray()
    }

}