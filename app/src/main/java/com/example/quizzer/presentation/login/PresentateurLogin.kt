package com.example.quizzer.presentation.login

import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.login.IContratVuePresentateurLogin.*
import com.example.quizzer.presentation.modèle

/**
 * Classe qui permet de communiquer avec le modèle
 *
 * @property modèle Objet qui permet de communiquer avec l'API
 * @property vue L'interface qui envoie les données à traiter
 */
class PresentateurLogin(
    var vue: IVueLogin = VueLogin()
) : IPresentateurLogin {

    /**
     * Méthode qui permet de rediriger vers le menu principal
     *
     */
    override fun traiterMenu() {
        vue.naviguerMenu()
    }

    /**
     * Méthode qui permet de rediriger vers la page de création d'un compte
     *
     */
    override fun traiterEnregistrer() {
        vue.naviguerEnregistrer()
    }

    /**
     * Méthode qui permet de vérifier si l'utilisateur existe
     *
     * @param nomUtilisateur Nom de l'utilisateur
     * @param password Mot de passe de l'utilisateur
     * @return True s'il existe; False s'il n'existe pas
     */
    override fun verifierConnexion(nomUtilisateur: String, password: String): Boolean {
        return modèle.getUilisateur(nomUtilisateur, password)
    }


}