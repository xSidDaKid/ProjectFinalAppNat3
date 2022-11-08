package com.example.quizzer.domaine.interacteur

import com.example.quizzer.accesAuxDonnées.ISourceDeDonées

class ObtenirReponses(var sourceDeDonées : ISourceDeDonées) {

    fun obtenirReponses() : List< Map<String,String> >{

        var reponses = trierReponses(sourceDeDonées.obtenirReponsesBrutes())
        return reponses
    }

    private fun trierReponses( reponses:String ) : List< Map<String,String>>{
        var premierFiltre = reponses.split(",")
        var réponsesTriés : List<Map<String,String>> = emptyList()

        for(item in premierFiltre){
            var deuxièmeFiltre = item.split(":")
            réponsesTriés +=  mapOf<String,String>(deuxièmeFiltre[0] to deuxièmeFiltre[1])
            

        }

        return réponsesTriés
    }
}