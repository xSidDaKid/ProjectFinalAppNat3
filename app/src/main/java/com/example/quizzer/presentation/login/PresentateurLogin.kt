package com.example.quizzer.presentation.login

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.login.*

class PresentateurLogin(
    var modele: Modèle,
    var vue: IContratVuePresentateurLogin.IVueLogin = VueLogin()
) :
    IContratVuePresentateurLogin.IPresentateurLogin {
    override fun traiterMenu() {
        vue.naviguerMenu()
    }

    override fun traiterEnregistrer() {
        vue.naviguerEnregistrer()
    }
}