package com.example.quizzer.accesAuxDonnées

class ReponsesParDefaut : ISourceDeDonées{
    override fun obtenirReponses(): List<Map<String, String>> {
        TODO("Not yet implemented")
    }

    override fun obtenirReponsesBrutes(): String {
        var question = "Banane:Jaune,Fraise:Rouge,Citrouille:Orange,Pomme Granny Smith:Vert"
        return question
    }

}