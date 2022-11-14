package com.example.quizzer.domaine.entité

class Utilisateur {

    var id: Int = 0
    lateinit var courriel: String
    lateinit var nomUtilisateur: String
    lateinit var motDePasse: String

    init {
        nomUtilisateur = ""
        motDePasse = ""
    }
}