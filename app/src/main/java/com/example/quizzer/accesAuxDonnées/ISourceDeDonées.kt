package com.example.quizzer.accesAuxDonnées

interface ISourceDeDonées {
    fun obtenirReponses() :  List< Map<String,String> >

    // Dans le contexte ou on récupère les réponses sous forme d'un texte exclusivement
    fun obtenirReponsesBrutes() : String
}