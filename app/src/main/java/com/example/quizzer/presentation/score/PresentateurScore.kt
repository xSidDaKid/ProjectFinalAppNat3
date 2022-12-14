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
        var listePermisionScore = mutableListOf<PermissionScore>()

        GlobalScope.launch(Dispatchers.Main) {

            var job = async(SupervisorJob() + Dispatchers.IO) {
                modèle.getListePermission()
            }

            try{
                vue.afficherLoading()
                var mapPermission = job.await()

                for (item in mapPermission) {
                    if (item.value.utilisateur == modèle.utilisateurConnecte) {
                        listePermisionScore += item.value
                    }
                }
                vue.initialiserListeScore(listePermisionScore)

            }
            catch(e: java.lang.Exception ){
                vue.afficherMessageErreur( "ici")
            }
        }



        return listePermisionScore
    }

}