package com.example.quizzer.accesAuxDonnées

class ReponsesParDefaut : ISourceDeDonées{
    override fun obtenirReponsesBrutes(): String {
        var question = "Banane:Jaune,Fraise:Rouge,Citrouille:Orange,Pomme Granny Smith:Vert"
        return question
    }

}