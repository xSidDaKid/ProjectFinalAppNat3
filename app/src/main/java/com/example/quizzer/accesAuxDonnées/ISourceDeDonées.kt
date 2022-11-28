package com.example.quizzer.accesAuxDonnées

interface ISourceDeDonées {
    // Dans le contexte ou on récupère les réponses sous forme d'un texte exclusivement
    fun obtenirReponsesBrutes() : String
}