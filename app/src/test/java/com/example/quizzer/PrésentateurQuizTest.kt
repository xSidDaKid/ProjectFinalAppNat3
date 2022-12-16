package com.example.quizzer

import com.example.quizzer.accesAuxDonnées.ReponsesParDefaut
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.modèle
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz.IVueQuiz
import com.example.quizzer.presentation.quiz.PrésentateurQuiz
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import kotlin.text.Typography.times

class PrésentateurQuizTest {

    @Test
    fun `étant donné une vue avec un quiz choisi, lorsqu'on click un choix "Rouge", le score augmente de 1 et l'on continue à la prochaine question `(){

        val mockVue : IVueQuiz = Mockito.mock( IVueQuiz::class.java)
        val présentateur = PrésentateurQuiz(mockVue)

        val résultat_obtenu = présentateur.envoyerRéponse("Rouge", 1, ReponsesParDefaut().quizSelectionné())
        val résultat_attendu = true

        assertEquals(résultat_attendu, résultat_obtenu)

    }

    @Test
    fun `étant donné la deuxième question dans un quiz choisi, lorsqu'on click un choix "Rouge", le score n'augmente pas et l'on continue à la prochaine question `(){
        val mockVue : IVueQuiz = Mockito.mock( IVueQuiz::class.java)
        val présentateur = PrésentateurQuiz(mockVue)

        val résultat_obtenu = présentateur.envoyerRéponse("Rouge", 2, ReponsesParDefaut().quizSelectionné())
        val résultat_attendu = false

        assertEquals(résultat_attendu, résultat_obtenu)

    }

    @Test
    fun `étant donné la dernière question dans un quiz choisi, lorsqu'on click un choix "Rouge", le score n'augmente pas et l'on continue à la vue de score `(){
        val mockVue : IVueQuiz = Mockito.mock( IVueQuiz::class.java)
        val présentateur = PrésentateurQuiz(mockVue)

        Mockito.`when`( présentateur.envoyerRéponse("Rouge", 4, ReponsesParDefaut().quizSelectionné()) )
            .thenReturn (true)

        Mockito.verify( présentateur, times(1)).envoyerRéponse("Rouge", 4, ReponsesParDefaut().quizSelectionné())

    }
}