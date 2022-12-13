package com.example.quizzer.presentation.permission

import android.util.Log
import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.modèle
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IPresentateurPermission
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IVuePermission
import kotlinx.coroutines.*


class PresentateurPermission( var vue:IVuePermission = VuePermission()) : IPresentateurPermission{
    var listequiz = arrayOf<Quiz>()


    override fun getTousPermissionsList(): Array<Pair<String, PermissionScore>> {

        return modèle.getTousPermission().toList().toTypedArray()
    }

    override fun getPermission(position:Int): PermissionScore{
        var liste = modèle.getTousPermission().toList().toTypedArray()
        return liste[position].second
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
                vue.initialiserListeQuiz(listequiz)

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                //vue.afficherMessageErreur(e.toString())
            }
        }
        Log.d("testapiquiz", listequiz.toString())
        return listequiz
        //return modèle.getListeQuizSync().toTypedArray()
    }
    override fun dialogPermission(position: Int) {

        vue.montrerDialog(position)
    }

    override fun creerPermission(email: String, position: Int) {
        modèle.ajouterPermission(email,position)
    }


}