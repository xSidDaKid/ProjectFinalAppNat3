package com.example.quizzer.presentation.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz.*


class VueQuiz : Fragment(), IVueQuiz {


    lateinit var navController : NavController;

    var présentateur : PrésentateurQuiz? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vue = inflater.inflate(R.layout.fragment_quiz, container, false)
        présentateur = PrésentateurQuiz( Modèle, this)

        présentateur?.réinitialiserQuiz()
        initialiserTexteBouttons(vue)
        attacherÉcouteurChoix( vue )
        prochaineRéponse(vue)
        println("111")
        return vue
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view);
    }


    private fun attacherÉcouteurChoix( vue : View ){

        for( i in 1 until 5 ) {
            println("333")
            (vue.findViewWithTag(Integer.toString(i)) as Button).setOnClickListener(
                View.OnClickListener {
                        view -> val boutton = view as Button
                        var reponse = boutton.text.toString()
                        présentateur?.envoyerRéponse(reponse,présentateur?.envoyerIndexRéponse())
                        prochaineRéponse(vue)
                        println("222")
                }
            )
        }
    }

    private fun prochaineRéponse(vue : View){
        var button = vue.findViewById(R.id.reponseQuiz) as Button
        button.text = présentateur?.envoyerProchaineRéponse()
    }

    private fun initialiserTexteBouttons(vue : View){
        print("initialiser")
        var listeChoix= présentateur?.envoyerChoix()
        for( i in 1 until 5 ) {
            var boutton = vue.findViewWithTag(Integer.toString(i)) as Button
            boutton.text= listeChoix?.get(i-1)

        }
    }


    override fun afficherRéponse(réponse: String) {
        TODO("Not yet implemented")
    }


}