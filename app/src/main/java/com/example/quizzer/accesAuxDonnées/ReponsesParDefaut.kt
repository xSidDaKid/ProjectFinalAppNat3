package com.example.quizzer.accesAuxDonnées

import android.util.Log
import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.Utilisateur

class ReponsesParDefaut:ISourceDeDonées{
    override fun obtenirReponsesBrutes(): String {
        var question = "Banane:Jaune,Fraise:Rouge,Citrouille:Orange,Pomme Granny Smith:Vert"
        return question
    }

    override fun obtenirUtilisateurs(): MutableList<Utilisateur> {
        return mutableListOf()
    }

    override fun obtenirPermissions(): MutableList<PermissionScore> {
        Log.d("testapi","chercher PAR DEFAULT")
        return mutableListOf()
    }

    override fun obtenirQuiz(): MutableList<Quiz> {
        return mutableListOf()
    }

}