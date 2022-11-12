package com.example.quizzer.presentation.registration

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz
import com.example.quizzer.presentation.quiz.VueQuiz

class PrésentateurRegistration(var modèle: Modèle, var vue: IContratVueRegistration.IVueRegistration = VueRegistration()) :
    IContratVueRegistration.IPrésentateurRegistration {
}