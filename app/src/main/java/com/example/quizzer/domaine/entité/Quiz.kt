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
