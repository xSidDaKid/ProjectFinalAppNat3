package com.example.quizzer.presentation.listQuiz

import android.util.Log
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.listQuiz.IContratVuePresentateurListQuiz.IPresentateurListQuiz
import com.example.quizzer.presentation.listQuiz.IContratVuePresentateurListQuiz.IVueListQuiz
import com.example.quizzer.presentation.modèle
import kotlinx.coroutines.*

/**
 * Classe qui permet de communiquer avec le modèle et changer la vue
 *
 * @property modele Objet qui permet de communiquer avec l'API
 * @property vue Vue Liste Quiz
 */
class PresentateurListQuiz(
    var vue: IVueListQuiz = VueListQuiz()
) : IPresentateurListQuiz {
    var listequiz = arrayOf<Quiz>()

    /**
     * Méthode qui permet de recevoir la liste des quiz du modèle
     *
     * @return La liste des quiz
     */
    override fun getListeQuiz(): Array<Quiz> {
        GlobalScope.launch(Dispatchers.Main) {

            var job = async(SupervisorJob() + Dispatchers.IO) {
                modèle.getListePermission()
            }

            try {
                vue.afficherLoading()
                var mapPermission = job.await()

                for (item in mapPermission) {
                    if (item.value.utilisateur == modèle.utilisateurConnecte) {
                        listequiz += item.value.quiz!!
                    }
                }
                vue.initialiserListeQuiz(listequiz)
            } catch (e: java.lang.Exception) {
                vue.afficherMessageErreur("ici")
            }
        }
        return listequiz
    }

    /**
     * Méthode qui permet de recevoir le quiz choisi par l'utilisateur et diriger l'utiliser vers ce quiz
     *
     * @param listePosition La position du quiz
     */
    override fun getQuiz(listePosition: Int) {
        modèle.quizSelected = listequiz[listePosition]
        vue.naviguerVersQuiz()
    }

    /**
     * Méthode qui réinitialiser les réponses d'un quiz
     *
     */
    override fun reinitialiserReponse() {
        modèle.reinitialiserReponse()
    }
}