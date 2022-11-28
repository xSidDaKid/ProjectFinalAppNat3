package com.example.quizzer.presentation.listQuiz

import com.example.quizzer.domaine.entité.Quiz

interface IContratVuePresentateurListQuiz {
    interface IVueListQuiz{
        fun initialiserListeQuiz()
        fun attacherÉcouteurAuxQuiz()
        fun naviguerVersQuiz()
    }

    interface IPresentateurListQuiz{
        fun  getListeQuiz(): Array<Quiz>
        fun getQuiz(listePosition: Int)

    }
}