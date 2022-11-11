package com.example.quizzer.domaine.entité

import java.util.*

class Quiz {
    var id:Int = 0
    var idCreateur:Int = 0
    lateinit var titre:String
    lateinit var question:String
    lateinit var choix:List<String>
    lateinit var reponses:List<Map<String,String>>


    fun getChoix(indice:Int): String {
        return choix[indice] as String
    }


    fun getReponse(index : Int ): Map<String,String> {
        return reponses[index-1]
    }

    fun mélangerQuestion(){
        return Collections.shuffle(reponses.toMutableList())
    }
}