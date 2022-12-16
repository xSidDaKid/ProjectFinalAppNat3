package com.example.quizzer

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz
import com.example.quizzer.presentation.creationQuiz.PresentateurCreationQuiz
import org.junit.Test
import org.mockito.Mockito

class PrésentateurCreationQuiz {

    @Test
    fun `étant donné qu'on a crée un Quiz, le calendrier devrait être initié avec les mêmes données que la question`() {

        //Mise en place
        val mockVue : IContratVuePresentateurCreationQuiz.IVueCreation = Mockito.mock( IContratVuePresentateurCreationQuiz.IVueCreation::class.java )
        val modèle = Mockito.mock( Modèle::class.java )

        val présentateur = PresentateurCreationQuiz(mockVue)

        // Action
        présentateur.traiterCreationQuiz("test","question",listOf("Rouge", "Blue", "Jaune", "Vert"),listOf("Rouge", "Blue", "Jaune", "Vert"))

        Mockito.verify( présentateur ).traiterCreationQuiz("test","question",listOf("Rouge", "Blue", "Jaune", "Vert"),listOf("Rouge", "Blue", "Jaune", "Vert"))

    }
}