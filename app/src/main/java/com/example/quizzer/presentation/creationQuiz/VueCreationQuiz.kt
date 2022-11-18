package com.example.quizzer.presentation.creationQuiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.MainActivity
import com.example.quizzer.R
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz.IVueCreation

class VueCreationQuiz : Fragment(), IVueCreation {

    lateinit var navController: NavController;
    lateinit var btnCreer: Button
    var présentateur: PresentateurCreationQuiz? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_creation_quiz, container, false)
        présentateur = PresentateurCreationQuiz(Modèle, this)

        btnCreer = vue.findViewById<Button>(R.id.btnCreer)
        attacherÉcouteurQuiz(vue)
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view);
    }

    override fun naviguerVersQuiz() {
        navController.navigate(R.id.quizFragment)
    }

    override fun afficherMessage(message : String){
       Toast.makeText(requireActivity(),message, Toast.LENGTH_LONG).show()
    }

    private fun attacherÉcouteurQuiz(vue: View) {
        btnCreer.setOnClickListener {
            var titre = vue.findViewById<EditText>(R.id.titre).text.toString()
            var question = vue.findViewById<EditText>(R.id.question).text.toString()
            var choix = vue.findViewById<EditText>(R.id.choix).text.toString()
            var reponse = vue.findViewById<EditText>(R.id.reponse).text.toString()

            présentateur?.traiterCreationQuiz(titre, question, choix, reponse)
        }
    }
}