package com.example.quizzer.domaine.entité

class Utilisateur (val id:Int, val courriel:String, val nomUtilisateur:String, val motDePasse:String){

    /* var id: Int = 0
    lateinit var courriel: String
    lateinit var nomUtilisateur: String
    lateinit var motDePasse: String

    init {
        nomUtilisateur = ""
        motDePasse = ""
    }*/
    override fun toString(): String {
        return "\nUtilisateur(id=$id, courriel='$courriel', nomUtilisateur='$nomUtilisateur', motDePasse='$motDePasse')"
    }
}