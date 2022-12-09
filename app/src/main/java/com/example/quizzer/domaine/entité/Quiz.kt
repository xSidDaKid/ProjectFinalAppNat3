package com.example.quizzer.domaine.entité

import java.util.*

class Quiz(
    var titre: String,
    var question: String,
    var choix: List<String>,
    var reponses: List<Map<String, String>>
) {
    /*var id: Int = 0
    var idCreateur: Int = 0
    open lateinit var titre: String
    open lateinit var question: String
    open lateinit var choix: List<String>
    open lateinit var reponses: List<Map<String, String>>
*/
/*    init {
        choix = emptyList<String>()
        reponses = emptyList<Map<String, String>>()
        question = ""
        titre = ""
    }*/
    fun getChoix(indice: Int): String {
        return this.choix[indice] as String
    }

    fun getChoixPourJson():String{
        var string:String=""
        for(item in choix){
            string+=item+","
        }
        return string.dropLast(1)
    }

    fun getReponsePourJson():String{
        var string:String=""
        for(item in reponses){
            string+= item.toList().get(0).first+";"+item.toList().get(0).second+","
        }

        return string.dropLast(1)
    }

    fun getReponse(index: Int): Map<String, String> {
        return reponses[index - 1]
    }

    fun mélangerQuestion() {
        return Collections.shuffle(reponses.toMutableList())
    }

    override fun toString(): String {
        return "Titre: " + titre + "\nQuestion: " + question
    }
}
