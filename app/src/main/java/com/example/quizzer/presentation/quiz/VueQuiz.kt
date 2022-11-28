package com.example.quizzer.presentation.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz.IVueQuiz

/**
 * TODO: Refactoriser et à ajouter dans les méthodes dans l'interface(Contrat)
 * Vue quiz qui permet d'afficher les données et permet d'informer le Présenteur à la suite d'une action de l'utilisateur
 */
class VueQuiz : Fragment(), IVueQuiz {

    lateinit var navController: NavController;
    var présentateur: PrésentateurQuiz? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_quiz, container, false)
        présentateur = PrésentateurQuiz(Modèle, this)
        var quiz = présentateur?.réinitialiserQuiz()
        initialiserTexteBouttons(vue)
        attacherÉcouteurChoix(vue, quiz!!)
        prochaineRéponse(vue, quiz!!)
        afficherQuestion(vue, quiz!!)
        afficherTitre(vue, quiz!!)
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
    }

    /**
     * Méthode qui permet de savoir la réponse choisi par l'utilisateur
     *
     * @param vue Vue Quiz
     */
    private fun attacherÉcouteurChoix(vue: View, quiz: Quiz) {

        for (i in 1 until 5) {
            (vue.findViewWithTag(Integer.toString(i)) as Button).setOnClickListener(View.OnClickListener { view ->
                val boutton = view as Button
                var reponse = boutton.text.toString()
                présentateur?.envoyerRéponse(reponse, présentateur?.envoyerIndexRéponse(), quiz)
                prochaineRéponse(vue, quiz)
                afficherScore(vue, quiz)
            })
        }
    }

    /**
     * Méthode qui permet d'afficher les différents choix sur les bouttons
     *
     * @param vue Vue Quiz
     */
    private fun initialiserTexteBouttons(vue: View) {
        var quizListe = présentateur?.réinitialiserQuiz()
        var listeChoix = quizListe?.choix

        for (i in 1 until 5) {
            var boutton = vue.findViewWithTag(Integer.toString(i)) as Button
            boutton.text = listeChoix?.get(i - 1)

        }
    }

    /**
     * Méthode qui permet d'obtenir la prochaine réponse
     *
     * @param vue Vue Quiz
     */
    private fun prochaineRéponse(vue: View, quiz: Quiz) {
        var button = vue.findViewById(R.id.reponseQuiz) as Button
        var text = présentateur?.envoyerProchaineRéponse(quiz)
        if (text != "")
            button.text = text
        else
            navController.navigate(R.id.vueMenuPrincipal)
    }

    /**
     * Méthode qui permet d'afficher la question
     *
     * @param vue Vue Quiz
     */
    private fun afficherQuestion(vue: View, quiz: Quiz) {
        var textQuestion = vue.findViewById<TextView>(R.id.questionQuiz)
        textQuestion.text = présentateur?.getQuestion(quiz)
    }

    /**
     * Méthode qui permet d'afficher le titre du quiz
     *
     * @param vue Vue Quiz
     */
    private fun afficherTitre(vue: View, quiz: Quiz) {
        var textTitre = vue.findViewById<TextView>(R.id.titreQuiz)
        textTitre.text = présentateur?.getTitre(quiz)
    }

    /**
     * Méthode qui permet d'afficher le score de l'utilisateur
     *
     * @param vue Vue Quiz
     */
    private fun afficherScore(vue: View, quiz: Quiz) {
        var textScore = vue.findViewById<TextView>(R.id.scoreQuiz)
        textScore.text = "Score: " + présentateur?.getScore(quiz).toString()
    }

    override fun afficherRéponse(réponse: String) {
        TODO("Not yet implemented")
    }

}