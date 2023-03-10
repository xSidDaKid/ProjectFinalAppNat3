package com.example.quizzer.presentation.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.quizzer.R
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz.IVueQuiz

/**
 * Vue quiz qui permet d'afficher les données et permet d'informer le Présenteur à la suite d'une action de l'utilisateur
 */
class VueQuiz : Fragment(), IVueQuiz {

    //lateinit var navController: NavController;
    var présentateur: PrésentateurQuiz? = null
    lateinit var btnQuitter: Button
    var meilleurScore:Int? = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_quiz, container, false)
        présentateur = PrésentateurQuiz(this)
        var quiz = présentateur?.réinitialiserQuiz()
        btnQuitter = vue.findViewById<Button>(R.id.btnQuitter)
        meilleurScore=présentateur?.getScore()
        initialiserTexteBouttons(vue)
        attacherÉcouteurChoix(vue, quiz!!)
        prochaineRéponse(vue, quiz)
        afficherQuestion(vue, quiz)
        afficherTitre(vue, quiz)
        attacherÉcouteurQuitter(vue)
        afficherScore(vue, quiz)
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Navigation.findNavController(view)
    }

    /**
     * Méthode qui permet de savoir la réponse choisi par l'utilisateur
     *
     * @param vue Vue Quiz
     */
    override fun attacherÉcouteurChoix(vue: View, quiz: Quiz) {

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
    override fun initialiserTexteBouttons(vue: View) {
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
    override fun prochaineRéponse(vue: View, quiz: Quiz) {
        var button = vue.findViewById(R.id.reponseQuiz) as Button
        var text = présentateur?.envoyerProchaineRéponse(quiz)
        if (text != "") {
            button.text = text
        }else {
            présentateur?.updateScore(meilleurScore!!)
            findNavController().navigate(R.id.vueMenuPrincipal)
        }
    }

    /**
     * Méthode qui permet d'afficher la question
     *
     * @param vue Vue Quiz
     */
    override fun afficherQuestion(vue: View, quiz: Quiz) {
        var textQuestion = vue.findViewById<TextView>(R.id.questionQuiz)
        textQuestion.text = présentateur?.getQuestion(quiz)
    }

    /**
     * Méthode qui permet d'afficher le titre du quiz
     *
     * @param vue Vue Quiz
     */
    override fun afficherTitre(vue: View, quiz: Quiz) {
        var textTitre = vue.findViewById<TextView>(R.id.titreQuiz)
        textTitre.text = présentateur?.getTitre(quiz)
    }

    /**
     * Méthode qui permet d'afficher le score de l'utilisateur
     *
     * @param vue Vue Quiz
     */
    override fun afficherScore(vue: View, quiz: Quiz) {
        var textScore = vue.findViewById<TextView>(R.id.scoreQuiz)
        textScore.text = "Score: " + présentateur?.getScore().toString()
    }

    /**
     * Méthode qui permet de quitter un quiz
     *
     * @param vue Le quiz
     */
    override fun attacherÉcouteurQuitter(vue: View) {
        btnQuitter.setOnClickListener {
            présentateur?.quitter()
            findNavController().navigate(R.id.vueMenuPrincipal)
        }
    }
}