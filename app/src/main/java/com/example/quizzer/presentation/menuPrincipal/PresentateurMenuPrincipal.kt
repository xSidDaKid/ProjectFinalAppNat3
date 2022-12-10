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

    /**
     * Méthode qui permet de se déconnecter
     *
     */
    override fun seDeconnecter() {
        vue.naviguerVersLogin()
    }

    /**
     * Méthode qui permet d'avoir le nom de l'utilisateur
     *
     * @return Nom d'utilisateur
     */
    override fun getNomUtilisateur(): String {
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
     * Méthode qui permet de rediriger vers la page de la liste des quiz
     *
     */
    override fun demarrerListeQuiz() {
        vue.naviguerVersListeQuiz()
    }

    /**
     * Méthode qui permet de rediriger vers la page d'invitation
     *
     */
    override fun voirPermissions() {
        vue.naviguerVersVoirPermission()
    }

    /**
     * Méthode qui permet de rediriger vers la page de score
     *
     */
    override fun voirScore() {
        vue.naviguerVersListeScore()
    }

}