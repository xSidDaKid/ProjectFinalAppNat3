package com.example.quizzer.domaine.entité

class Quiz {
    var id:Int = 0
    lateinit var titre:String
    lateinit var question:String
    lateinit var choix:Array<String>
    lateinit var reponses:Array<String>


    fun getChoix(indice:Int): String {
        return choix[indice] as String
    }


    fun getReponse(indice:Int): String {
        return reponses[indice] as String
    }
}