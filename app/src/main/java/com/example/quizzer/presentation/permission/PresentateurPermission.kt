package com.example.quizzer.presentation.permission

import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.modèle
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IPresentateurPermission
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IVuePermission


class PresentateurPermission( var vue:IVuePermission = VuePermission()) : IPresentateurPermission{


    override fun getTousPermissionsList(): Array<Pair<String, PermissionScore>> {

        return modèle.getTousPermission().toList().toTypedArray()
    }

    override fun getPermission(position:Int): PermissionScore{
        var liste = modèle.getTousPermission().toList().toTypedArray()
        return liste[position].second
    }

    override fun getListeQuiz(): Map<Int,Quiz> {
        return modèle.getListeQuiz()
    }

    fun getListeQuizSync(): Array<Quiz> {
        return modèle.getListeQuizSync().toTypedArray()
    }
    override fun dialogPermission(position: Int) {

        vue.montrerDialog(position)
    }

    override fun creerPermission(email: String, position: Int) {
        modèle.ajouterPermission(email,position)
    }


}