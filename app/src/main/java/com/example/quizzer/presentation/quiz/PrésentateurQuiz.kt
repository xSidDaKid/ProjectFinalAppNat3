package com.example.quizzer.presentation.quiz

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz.*


class PrésentateurQuiz(var modèle: Modèle, var vue: IVueQuiz = VueQuiz()) : IPrésentateurQuiz{
    override fun traiterRéponseSaisi(réponseChoisise: String) {
        TODO("Not yet implemented")
    }

    override fun traiterQuitterQuiz() {
        TODO("Not yet implemented")
    }

    override fun réinitialiserQuiz() {
        TODO("Not yet implemented")
    }

    private fun réinitialiserGrille(){
        modèle.initialiserQuizParDefaut()
        vue.réinitialiser()
    }
}