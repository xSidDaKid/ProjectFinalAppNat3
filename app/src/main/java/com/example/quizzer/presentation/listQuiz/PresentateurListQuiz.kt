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

    /**
     * Méthode qui permet de recevoir la liste des quiz du modèle
     *
     * @return La liste des quiz
     */
    override fun getListeQuiz(): Array<Quiz> {

        var listequiz = arrayOf<Quiz>()

        GlobalScope.launch(Dispatchers.Main) {

            //Ce bloc est exécuté dans le fil IO
            var job = async(SupervisorJob() + Dispatchers.IO) {
                //cette opération est longue
                modèle.getListeQuiz()
            }

            //en attendant la fin de la tâche,
            //l'exécution de cette coroutine est suspendue
            try {
                Log.d("testapiquiz", "debutAsync")
                vue.afficherLoading()
                var mapQuiz = job.await()
                for (item in mapQuiz) {
                    if (item.key == modèle.getIdUtilisateur()) {
                        listequiz += item.value
                    }
                }

                //lorsque la tâche est terminée, la coroutine
                //reprend et on met à jour l'interface utilisateur
                vue.initialiserListeQuiz(listequiz)

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                vue.afficherMessageErreur(e.toString())
            }
        }
        Log.d("testapiquiz", listequiz.toString())
        return listequiz
    }

    /**
     * Méthode qui permet de recevoir le quiz choisi par l'utilisateur et diriger l'utiliser vers ce quiz
     *
     * @param listePosition La position du quiz
     */
    override fun getQuiz(listePosition: Int) {
        modèle.getIDQuiz(listePosition)
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