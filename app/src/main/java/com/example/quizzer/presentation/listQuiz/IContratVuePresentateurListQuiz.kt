package com.example.quizzer.presentation.listQuiz

import com.example.quizzer.domaine.entité.Quiz

interface IContratVuePresentateurListQuiz {
    interface IVueListQuiz{
        fun naviguerVersQuiz(id: Int)
        fun initialiserListeQuiz()
    }

    interface IPresentateurListQuiz{
        fun  getListeQuiz(): List<Quiz>

    }
}