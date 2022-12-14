package com.example.quizzer.presentation.registration

import com.example.quizzer.presentation.modèle
import com.example.quizzer.presentation.registration.IContratVueRegistration.IPrésentateurRegistration
import com.example.quizzer.presentation.registration.IContratVueRegistration.IVueRegistration

/**
 * Classe qui permet de communiquer avec le modèle
 *
 * @property modèle Objet qui permet de communiquer avec l'API
 * @property vue L'interface qui envoie les données à traiter
 */
class PrésentateurRegistration(var vue: IVueRegistration = VueRegistration()) :
    IPrésentateurRegistration {

    /**
     * Méthode qui permet d'envoyer les données sur l'utilisateur au modèle
     *
     * @param email Courriel de l'utilisateur
     * @param username Nom d'utilisateur de l'utilisateur
     * @param mdp Mot de passe de l'utilisateur
     */
    override fun traiterLogin(email: String, username: String, mdp: String) {
        modèle.ajouterUtilisateur(email, username, mdp)
        vue.naviguerLogin()
    }

    /**
     * Méthode qui permet de rediriger vers la page de login
     *
     */
    override fun traiterLogin() {
        vue.naviguerLogin()
    }

}