package com.example.quizzer.presentation.creationQuiz

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz.IVueCreation
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


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
        //navController.popBackStack()
        navController.navigate(R.id.quizFragment)
    }

    override fun afficherMessage(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
    }

    override fun attacherÉcouteurQuiz(vue: View) {
        btnCreer.setOnClickListener {

            var titre = vue.findViewById<TextInputEditText>(R.id.titre).text.toString()
            var question = vue.findViewById<TextInputEditText>(R.id.question).text.toString()
            var choix = arrayListOf(
                vue.findViewById<TextInputEditText>(R.id.choix0).text.toString(),
                vue.findViewById<TextInputEditText>(R.id.choix1).text.toString(),
                vue.findViewById<TextInputEditText>(R.id.choix2).text.toString(),
                vue.findViewById<TextInputEditText>(R.id.choix3).text.toString()
            )
            var reponse = arrayListOf(
                vue.findViewById<TextInputEditText>(R.id.reponse0).text.toString(),
                vue.findViewById<TextInputEditText>(R.id.reponse1).text.toString(),
                vue.findViewById<TextInputEditText>(R.id.reponse2).text.toString(),
                vue.findViewById<TextInputEditText>(R.id.reponse3).text.toString()
            )

            if (titre == "") {
                vue.findViewById<TextInputEditText>(R.id.titre).setError("Invalide")
            } else if (question == "") {
                vue.findViewById<TextInputEditText>(R.id.question).setError("Invalide")
            } else if (choix.contains("")) {
                vue.findViewById<TextInputEditText>(R.id.choix0).setError("Invalide")
                vue.findViewById<TextInputEditText>(R.id.choix1).setError("Invalide")
                vue.findViewById<TextInputEditText>(R.id.choix2).setError("Invalide")
                vue.findViewById<TextInputEditText>(R.id.choix3).setError("Invalide")
            } else if (reponse.contains("")) {
                vue.findViewById<TextInputEditText>(R.id.reponse0).setError("Invalide")
                vue.findViewById<TextInputEditText>(R.id.reponse1).setError("Invalide")
                vue.findViewById<TextInputEditText>(R.id.reponse2).setError("Invalide")
                vue.findViewById<TextInputEditText>(R.id.reponse3).setError("Invalide")
            } else {
                présentateur?.traiterCreationQuiz(titre, question, choix, reponse)
            }
        }
    }
}