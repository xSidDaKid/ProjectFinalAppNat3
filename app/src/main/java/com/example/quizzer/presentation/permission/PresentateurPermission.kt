package com.example.quizzer.presentation.permission

import android.util.Log
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.Utilisateur
import com.example.quizzer.presentation.modèle
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IPresentateurPermission
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IVuePermission
import kotlinx.coroutines.*


class PresentateurPermission(var vue: IVuePermission = VuePermission()) : IPresentateurPermission {
    var listequiz = arrayOf<Quiz>()
    var utilisateur = modèle.utilisateurConnecte


    /*override fun getTousPermissionsList(): Array<Pair<String, PermissionScore>> {

        return modèle.getTousPermission().toList().toTypedArray()
    }*/

    /*override fun getPermission(position: Int): PermissionScore {
        var liste = modèle.getTousPermission().toList().toTypedArray()
        return liste[position].second
    }*/


    override fun dialogPermission(position: Int) {

        vue.montrerDialog(position)
    }

    fun getListeQuizSync(): Array<Quiz> {
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
                    if (item.value.utilisateur == modèle.utilisateurConnecte) {
                        listequiz += item.value
                    }
                }

                //lorsque la tâche est terminée, la coroutine
                //reprend et on met à jour l'interface utilisateur
                if (listequiz.isEmpty()) {
                    vue.montrerDialog()
                } else {
                    vue.initialiserListeQuiz(listequiz)
                }

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                //vue.afficherMessageErreur(e.toString())
            }
        }
        Log.d("testapiquiz", listequiz.toString())
        return listequiz
        //return modèle.getListeQuizSync().toTypedArray()
    }

    override fun findUserChoisi(email: String, position: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            var job = async(SupervisorJob() + Dispatchers.IO) {
                modèle.getListeUtilisateur()
            }

            try {
                vue.afficherLoading()
                var mapUtilisateur = job.await()
                for (item in mapUtilisateur) {
                    if (item.value.courriel == email) {
                        utilisateur = item.value
                    }
                    break
                }

                vue.creerPermission(position, utilisateur)

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

    }

    override fun findQuizChoisi(position: Int): Quiz {
        return listequiz[position]

    }

    override fun ajoutPermission(quiz: Quiz, utilisateur: Utilisateur) {
        modèle.quizSelected = quiz
        modèle.ajouterPermission(utilisateur)
    }


}