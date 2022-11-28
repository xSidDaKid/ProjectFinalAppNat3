package com.example.quizzer.presentation.listQuiz

import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.listQuiz.IContratVuePresentateurListQuiz.*

class PresentateurListQuiz(
    var modele: Modèle,
    var vue: IVueListQuiz = VueListQuiz()
) : IPresentateurListQuiz {

    override fun getListeQuiz(): Array<Quiz> {
        return modele.getListeQuiz().toTypedArray()
    }

    override fun getQuiz(listePosition: Int) {
        modele.setIndexQuiz(listePosition)
        vue.naviguerVersQuiz()
    }

}