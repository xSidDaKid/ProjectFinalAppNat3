package com.example.quizzer.presentation.registration

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.registration.IContratVueRegistration.IPrésentateurRegistration
import com.example.quizzer.presentation.registration.IContratVueRegistration.IVueRegistration

class PrésentateurRegistration(var modèle: Modèle, var vue: IVueRegistration = VueRegistration()) :
    IPrésentateurRegistration {

    override fun traiterLogin() {
        vue.naviguerLogin()
    }
}