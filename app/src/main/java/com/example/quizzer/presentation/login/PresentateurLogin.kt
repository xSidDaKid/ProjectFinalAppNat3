package com.example.quizzer.presentation.login

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.login.IContratVuePresentateurLogin.*

class PresentateurLogin(
    var modele: Modèle,
    var vue: IVueLogin = VueLogin()
) : IPresentateurLogin {

    override fun traiterMenu() {
        vue.naviguerMenu()
    }

    override fun traiterEnregistrer() {
        vue.naviguerEnregistrer()
    }

    override fun verifierConnexion(nomUtilisateur: String, password: String): Boolean {
        return nomUtilisateur== "" && password == ""
    }


}