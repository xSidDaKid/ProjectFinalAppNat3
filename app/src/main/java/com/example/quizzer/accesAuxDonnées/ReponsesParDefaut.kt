package com.example.quizzer.accesAuxDonnées

import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.Utilisateur

class ReponsesParDefaut : ISourceDeDonées {
    override fun obtenirReponsesBrutes(): String {
        var question = "Banane:Jaune,Fraise:Rouge,Citrouille:Orange,Pomme Granny Smith:Vert"
        return question
    }

    override fun obtenirUtilisateurs(): Map<Int, Utilisateur> {
        TODO("Not yet implemented")
    }

    override fun obtenirPermissions(): Map<Int, PermissionScore> {
        TODO("Not yet implemented")
    }

    override fun obtenirQuiz(): Map<Int, Quiz> {
        TODO("Not yet implemented")
    }

    override fun postQuiz(quiz: Quiz, id: Int) {
        TODO("Not yet implemented")
    }

    override fun postUtilisateur(utilisateur: Utilisateur) {
        TODO("Not yet implemented")
    }

    override fun postPermissionScore(permissionScore: PermissionScore) {
        TODO("Not yet implemented")
    }

}