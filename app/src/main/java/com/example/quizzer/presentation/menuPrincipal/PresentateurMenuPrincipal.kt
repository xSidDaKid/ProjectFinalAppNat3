package com.example.quizzer.presentation.menuPrincipal

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.menuPrincipal.IContratVuePresentateurMenuPrincipal.*

class PresentateurMenuPrincipal(
    var modele: Modèle,
    var vue: IVueMenuPrincipal = VueMenuPrincipal()
) : IPresentateurMenuPrincipal {

    override fun creerQuiz() {
        vue.naviguerVersCreationQuiz()
    }

    override fun demarrerQuiz() {
        vue.naviguerVersDemarrerQuiz()
    }
}