package com.example.quizzer.presentation.quiz

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz.*


class PrésentateurQuiz(var modèle: Modèle, var vue: IVueQuiz = VueQuiz()) : IPrésentateurQuiz{

     fun envoyerChoix():List<String>{
        return modèle.getChoix()
    }

    fun envoyerRéponse(réponse:String, indexRéponse: Int?):Boolean{
        if(indexRéponse==null){
            return modèle.soumettreRéponse(réponse,0)
        }
        return modèle.soumettreRéponse(réponse,indexRéponse)

    }

    fun envoyerProchaineRéponse(): String{
        return modèle.getProchaineRéponse()
    }

    fun envoyerIndexRéponse():Int{
        return modèle.getIndexRéponse()
    }



    override fun traiterRéponseSaisi(réponseChoisise: String) {
        TODO("Not yet implemented")
    }

    override fun traiterQuitterQuiz() {
        TODO("Not yet implemented")
    }


    override fun réinitialiserQuiz(){
        modèle.initialiserQuizParDefaut()
    }
}