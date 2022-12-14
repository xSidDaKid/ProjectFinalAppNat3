package com.example.quizzer

import com.example.quizzer.accesAuxDonnées.ISourceDeDonées
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.interacteur.ObtenirReponses
import com.example.quizzer.domaine.interacteur.VerificationReponse
import com.example.quizzer.domaine.interacteur.VerificationReponseCreationQuiz
import com.example.quizzer.presentation.Modèle
import org.junit.Assert
import org.junit.Test

class InteracteurTests {
/**
    @Test
    fun `Étant donné une source de donné prédéfinie, lorsqu'on obtiens les réponses, on reçois une liste des maps avec nos réponses`() {
        var cobayeSource = object : ISourceDeDonées{
            override fun obtenirReponsesBrutes(): String {
                var liste = "Banane:Jaune,Fraise:Rouge"
                return liste
            }

        }

        var résultat_obtenu = ObtenirReponses().obtenirReponses(cobayeSource)

        var résultalt_attendu : List<Map<String, String>> = emptyList()
        résultalt_attendu += mapOf("Banane" to "Jaune")
        résultalt_attendu += mapOf("Fraise" to "Rouge")

        Assert.assertEquals(résultalt_attendu,résultat_obtenu)
    }


    @Test
    fun `Étant donné les chaines valides pour creer un quiz, lorsqu'on valide les chaines, on obtient un message vide`() {
        var cobayeRéponses= "Rouge,Vert,Jaune,Orange"
        var cobayeChoix="Banane:Jaune,Fraise:Rouge,Fraise:Rouge"
        var résultat_obtenu = VerificationReponseCreationQuiz().verificationReponseCreationQuiz(cobayeRéponses,cobayeChoix)
        var résultalt_attendu = ""

        Assert.assertEquals(résultalt_attendu,résultat_obtenu)
    }

    @Test
    fun `Étant donné une question qui demande la couleur d'une fraise, Lorsqu'on répond Rouge, on obtien True`(){

        var cobayeChoix = listOf<String>("Rouge","Vert","Jaune","Orange")
        var cobayeReponses : List<Map<String, String>> = emptyList()
        cobayeReponses += mapOf("Fraise" to "Rouge")


        var cobayeQuiz = Quiz(5,"test","test",cobayeChoix,cobayeReponses)

        var résultat_obtenu = VerificationReponse().verificationReponse("Rouge",1,cobayeQuiz)
        var résultalt_attendu = true
        Assert.assertEquals(résultalt_attendu,résultat_obtenu)
    }
    **/
}