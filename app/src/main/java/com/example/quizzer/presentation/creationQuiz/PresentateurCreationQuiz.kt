package com.example.quizzer.presentation.creationQuiz

import com.example.quizzer.R
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz.IVueCreation
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz.IPresentateurCreation
import com.google.android.material.textfield.TextInputEditText

class PresentateurCreationQuiz(var modele: Modèle, var vue: IVueCreation = VueCreationQuiz()) :
    IPresentateurCreation {

    override fun traiterCreationQuiz(
        titre: String,
        question: String,
        choix: List<String>,
        reponse: List<String>
    ) {

        /*   //var réponsesTriés = trierReponses(reponse)

           // Demander l'utilisateur de vérifier ses réponses dans la vue
           var message = modele.veriferQuiz(choix, reponse)

           if(message != ""){
               vue.afficherMessage(message)
           }else{
               modele.ajouterQuiz(titre, question, choix, reponse)
               vue.naviguerVersQuiz()
           }
   */

        //var réponsesTriés = modele.getReponseTrier(reponse)
        modele.ajouterQuiz(titre, question, choix, reponse)
        vue.naviguerVersQuiz()

    }

}