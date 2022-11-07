package com.example.quizzer.presentation.quiz

interface IContratVuePrésentateurQuiz {

    interface IVueQuiz{
        fun afficherRéponse(réponse: String)

        fun réinitialiser()
    }

    interface IPrésentateurQuiz{
        fun traiterRéponseSaisi(réponseChoisise: String)

        fun traiterQuitterQuiz()

        fun réinitialiserQuiz()

    }
}