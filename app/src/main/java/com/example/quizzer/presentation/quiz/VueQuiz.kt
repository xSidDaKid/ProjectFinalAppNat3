package com.example.quizzer.presentation.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import com.example.quizzer.R
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz.*


class VueQuiz : Fragment(), IVueQuiz {

    var présentateur : PrésentateurQuiz? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vue = inflater.inflate(R.layout.fragment_quiz, container, false)
        présentateur = PrésentateurQuiz( Modèle, this)


        initialiserTexteBouttons(vue)
        attacherÉcouteurChoix( vue )
        return vue
    }

    private fun attacherÉcouteurChoix( vue : View ){

        for( i in 1 until 5 ) {
            (vue.findViewWithTag(Integer.toString(i)) as Button).setOnClickListener(
                View.OnClickListener {
                        view -> val boutton = view as Button
                        var reponse = boutton.text.toString()


                    //presentateur envoi de la reponse
                }
            )
        }
    }

    private fun initialiserTexteBouttons(vue : View){
        // À appliquer sur le presentateur
        var listeChoix= Modèle.getChoix()
        for( i in 1 until 5 ) {
            var boutton = vue.findViewWithTag(Integer.toString(i)) as Button
            boutton.text= listeChoix[i-1]

        }
    }


    override fun afficherRéponse(réponse: String) {
        TODO("Not yet implemented")
    }

    override fun réinitialiser() {
        TODO("Not yet implemented")
    }
}