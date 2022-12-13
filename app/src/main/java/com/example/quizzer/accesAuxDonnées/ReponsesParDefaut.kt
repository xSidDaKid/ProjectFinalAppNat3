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
        var quiz1 = Quiz("Example1",
            "Quelle est la couleur de la voiture par défaut?",
            listOf("Rouge", "Blue","Jaune","Vert"),
            listOf(mapOf("Rouge" to "Lamborghini"), mapOf("Blue" to "Pagani"), mapOf("Jaune" to "Mustang"), mapOf("Vert" to "Urus")),
            Utilisateur("carlover@gmail.com", "CarLover4200","CarLover9000")
        )
        var quiz = mapOf(1 to quiz1)
        return quiz
    }

    override fun postQuiz(quiz: Quiz, id: Int) {
        TODO("Not yet implemented")
    }

    fun quizSelectionné(): Quiz {
        var quiz = Quiz("Example1",
            "Quelle est la couleur de la voiture par défaut?",
            listOf("Rouge", "Blue","Jaune","Vert"),
            listOf(mapOf("Lamborghini" to "Rouge"), mapOf("Pagani" to "Blue"), mapOf("Mustang" to "Jaune"), mapOf("Urus" to "Vert")),
            Utilisateur("carlover@gmail.com", "CarLover4200","CarLover9000")
        )
        return quiz
    }

    override fun postUtilisateur(utilisateur: Utilisateur) {
        TODO("Not yet implemented")
    }

    override fun postPermissionScore(permissionScore: PermissionScore) {
        TODO("Not yet implemented")
    }

    override fun updatePermissionScore(permissionScore: PermissionScore) {
        TODO("Not yet implemented")
    }

}