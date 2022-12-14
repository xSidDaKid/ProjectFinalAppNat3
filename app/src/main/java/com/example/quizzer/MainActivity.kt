package com.example.quizzer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzer.accesAuxDonnées.SourceAPI
import com.example.quizzer.presentation.modèle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("testapi", "oncreate")
        modèle.sourceDeDonne = SourceAPI(this)

        /*   // Création de la vue
           val vue = VueQuiz()
           // et du présentateur
           val présentateur = PrésentateurQuiz(Modèle, vue)
           vue.présentateur = présentateur

           // Ajout du fragment
           val ft = supportFragmentManager.beginTransaction()
           ft.add(R.id.layoutQuiz, vue)
           ft.commit()*/


    }
}