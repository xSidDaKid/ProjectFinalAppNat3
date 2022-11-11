package com.example.quizzer.presentation.creationQuiz

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz.IVueCreation
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz.IPresentateurCreation

class PresentateurCreationQuiz(var modele: Modèle, var vue: IVueCreation = VueCreationQuiz()) :
    IPresentateurCreation {

    override fun traiterCreationQuiz() {
        vue.naviguerVersQuiz()
    }
}