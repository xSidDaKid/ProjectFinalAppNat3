package com.example.quizzer.presentation.listQuiz

import com.example.quizzer.domaine.entité.Quiz

interface IContratVuePresentateurListQuiz {
    interface IVueListQuiz{
        fun naviguerVersQuiz(id: Int)
        fun initialiserListeQuiz()
        fun attacherÉcouteurAuxQuiz()
    }

    interface IPresentateurListQuiz{
        fun  getListeQuiz(): Array<Quiz>

    }
}