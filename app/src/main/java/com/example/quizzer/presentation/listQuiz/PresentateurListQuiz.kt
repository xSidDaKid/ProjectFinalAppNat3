package com.example.quizzer.presentation.listQuiz

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.listQuiz.IContratVuePresentateurListQuiz.*

class PresentateurListQuiz(
    var modele: Modèle,
    var vue: IVueListQuiz = VueListQuiz()
) : IPresentateurListQuiz {

}