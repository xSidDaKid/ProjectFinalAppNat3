package com.example.quizzer.presentation.quiz

import com.example.quizzer.domaine.entité.Quiz

interface IContratVuePrésentateurQuiz {

    interface IVueQuiz {
        fun afficherRéponse(réponse: String)

    }

    interface IPrésentateurQuiz {
        fun traiterRéponseSaisi(réponseChoisise: String)

        fun traiterQuitterQuiz()

        //fun réinitialiserQuiz():Quiz

    }
}