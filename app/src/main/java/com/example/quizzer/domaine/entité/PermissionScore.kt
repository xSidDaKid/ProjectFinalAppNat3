package com.example.quizzer.domaine.entité

class PermissionScore (var utilisateur: Utilisateur, var quiz: Quiz, var score:Int){


    override fun toString(): String {
        return "Quiz: " + quiz.titre + "\nScore: " + score
    }
}