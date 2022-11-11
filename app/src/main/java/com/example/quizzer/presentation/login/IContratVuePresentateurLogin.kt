package com.example.quizzer.presentation.login

interface IContratVuePresentateurLogin {
    interface IVueLogin {
        fun naviguerMenu()
        fun naviguerEnregistrer()
    }

    interface IPresentateurLogin {
        fun traiterMenu()
        fun traiterEnregistrer()
    }
}