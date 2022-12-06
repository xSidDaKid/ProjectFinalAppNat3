package com.example.quizzer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizzer.accesAuxDonnées.SourceAPI
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.quiz.PrésentateurQuiz
import com.example.quizzer.presentation.quiz.VueQuiz
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Modèle.sourceDeDonne = SourceAPI(this)
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