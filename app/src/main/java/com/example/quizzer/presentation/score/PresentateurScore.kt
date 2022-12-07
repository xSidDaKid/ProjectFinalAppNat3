package com.example.quizzer.presentation.score


import android.util.Log
import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.modèle
import com.example.quizzer.presentation.score.IContratVuePresentateurScore.IPresentateurScore
import com.example.quizzer.presentation.score.IContratVuePresentateurScore.IVueScore
import kotlinx.coroutines.*


class PresentateurScore(var vue:IVueScore): IPresentateurScore{

    override fun getListePermission(): MutableList<PermissionScore> {
        Log.d("avant","avantapi")


        GlobalScope.launch(Dispatchers.Main) {

            //Ce bloc est exécuté dans le fil IO
            var job = async(SupervisorJob() + Dispatchers.IO) {
                //cette opération est longue
                modèle.chercherPermissions()
            }

            //en attendant la fin de la tâche,
            //l'exécution de cette coroutine est suspendue
            try{
                var permission = job.await()

                //lorsque la tâche est terminée, la coroutine
                //reprend et on met à jour l'interface utilisateur
                vue.initialiserListeScore(permission)

            }
            catch(e: java.lang.Exception ){
                vue.afficherMessageErreur( "ici")
            }
        }



        return modèle.getListePermissionParEmail()
    }

}