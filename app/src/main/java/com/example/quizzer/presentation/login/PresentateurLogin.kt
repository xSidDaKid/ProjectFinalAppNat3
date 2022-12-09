package com.example.quizzer.presentation.login

import android.util.Log
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.Utilisateur
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.login.IContratVuePresentateurLogin.*
import com.example.quizzer.presentation.modèle
import kotlinx.coroutines.*

/**
 * Classe qui permet de communiquer avec le modèle
 *
 * @property modèle Objet qui permet de communiquer avec l'API
 * @property vue L'interface qui envoie les données à traiter
 */
class PresentateurLogin(
    var vue: IVueLogin = VueLogin()
) : IPresentateurLogin {
    private var listeUtilisateur = arrayOf<Utilisateur>()

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
        for (item in listeUtilisateur) {
            if (item.nomUtilisateur == nomUtilisateur) {
                if (item.motDePasse == password) {
                    modèle.getUilisateur(item)
                    return true
                }
            }
        }
        return false
    }

    /**
     * Méthode qui permet de faire une requête asynchrone pour avoir la liste des utilisateurs
     *
     */
    override fun getListeUtilisateur() {
        GlobalScope.launch(Dispatchers.Main) {

            //Ce bloc est exécuté dans le fil IO
            var job = async(SupervisorJob() + Dispatchers.IO) {
                //cette opération est longue
                modèle.getListeUtilisateur()
            }

            //en attendant la fin de la tâche,
            //l'exécution de cette coroutine est suspendue
            try {
                Log.d("testapiquiz", "debutAsync")
                var mapUtilisateur = job.await()
                for (item in mapUtilisateur) {
                    listeUtilisateur += item.value
                }

                //lorsque la tâche est terminée, la coroutine
                //reprend et on met à jour l'interface utilisateur

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }


}