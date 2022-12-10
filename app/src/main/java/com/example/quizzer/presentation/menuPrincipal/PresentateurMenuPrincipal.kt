package com.example.quizzer.presentation.menuPrincipal

import com.example.quizzer.presentation.menuPrincipal.IContratVuePresentateurMenuPrincipal.*
import com.example.quizzer.presentation.modèle

/**
 * Classe qui permet de communiquer avec le modèle
 *
 * @property modele Objet qui permet de communiquer avec l'API
 * @property vue L'interface qui envoie les données à traiter
 */
class PresentateurMenuPrincipal(
    var vue: IVueMenuPrincipal = VueMenuPrincipal()
) : IPresentateurMenuPrincipal {

    override fun getNomUtilisateur():String{
        return modèle.getNomUtilisateur()
    }
    /**
     * Méthode qui permet de rediriger vers la page de création d'un quiz
     *
     */
    override fun creerQuiz() {
        vue.naviguerVersCreationQuiz()
    }


    /**
     * TODO
     *
     */
    override fun demarrerListeQuiz() {
        vue.naviguerVersDemarrerQuiz()
    }

    override fun voirScore() {
        vue.naviguerVersListeScore()
    }

    override fun voirPermissions() {
        vue.naviguerVersVoirPermission()
    }
}