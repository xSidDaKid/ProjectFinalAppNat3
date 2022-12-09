package com.example.quizzer.presentation.creationQuiz

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz.IPresentateurCreation
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz.IVueCreation
import com.example.quizzer.presentation.modèle
import kotlinx.coroutines.*

/**
 * Classe qui permet de communiquer avec le modèle
 *
 * @property modele Objet qui permet de communiquer avec l'API
 * @property vue L'interface qui envoie les données à traiter
 */
class PresentateurCreationQuiz(var vue: IVueCreation = VueCreationQuiz()) :
    IPresentateurCreation {

    /**
     * Méthode qui permet d'envoyer les données sur le quiz au modèle
     *
     * @param titre Titre du quiz
     * @param question Question du quiz
     * @param choix Les choix du quiz
     * @param reponse Les réponses du quiz
     */
    override fun traiterCreationQuiz(
        titre: String, question: String, choix: List<String>, reponse: List<String>
    ) {

        GlobalScope.launch(Dispatchers.Main) {

            //Ce bloc est exécuté dans le fil IO
            var job = async(SupervisorJob() + Dispatchers.IO) {
                //cette opération est longue
                modèle.ajouterQuiz(titre, question, choix, reponse)
            }


            try{

                vue.afficherMessageErreur( "Ajout réussi")

            }
            catch(e: java.lang.Exception ){
                vue.afficherMessageErreur( "ici")
            }
        }

        vue.naviguerVersMenu()
    }

}