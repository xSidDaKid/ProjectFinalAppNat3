package com.example.quizzer.presentation.creationQuiz

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.presentation.creationQuiz.IContratVuePresentateurCreationQuiz.IVueCreation
import com.google.android.material.textfield.TextInputEditText
import java.util.*

/**
 * Classe qui permet de montrer l'interface pour créer un quiz et envoyer les données au présentateur
 *
 */
class VueCreationQuiz : Fragment(), IVueCreation {

    lateinit var navController: NavController
    lateinit var btnCreer: Button

    var présentateur: PresentateurCreationQuiz? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_creation_quiz, container, false)
        présentateur = PresentateurCreationQuiz(this)

        btnCreer = vue.findViewById<Button>(R.id.btnCreer)
        attacherÉcouteurQuiz(vue)
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
    }

    /**
     * Méthode qui affiche le quiz après la création du quiz
     *
     */
    override fun naviguerVersQuiz() {
        navController.popBackStack()
        navController.navigate(R.id.quizFragment)
    }

    override fun naviguerVersMenu() {
        navController.popBackStack()
        navController.navigate(R.id.vueMenuPrincipal)
    }

    /**
     * TODO
     *
     * @param message
     */
    override fun afficherMessage(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
    }

    /**
     * Méthode qui vérifie si les champs ne sont pas vide, puis envoie les données au présentateur
     *
     * @param vue Vue de Création d'un quiz
     */
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
                vue.findViewById<TextInputEditText>(R.id.titre).error = "Invalide"
            } else if (question == "") {
                vue.findViewById<TextInputEditText>(R.id.question).error = "Invalide"
            } else if (choix.contains("")) {
                vue.findViewById<TextInputEditText>(R.id.choix0).error = "Invalide"
                vue.findViewById<TextInputEditText>(R.id.choix1).error = "Invalide"
                vue.findViewById<TextInputEditText>(R.id.choix2).error = "Invalide"
                vue.findViewById<TextInputEditText>(R.id.choix3).error = "Invalide"
            } else if (reponse.contains("")) {
                vue.findViewById<TextInputEditText>(R.id.reponse0).error = "Invalide"
                vue.findViewById<TextInputEditText>(R.id.reponse1).error = "Invalide"
                vue.findViewById<TextInputEditText>(R.id.reponse2).error = "Invalide"
                vue.findViewById<TextInputEditText>(R.id.reponse3).error = "Invalide"
            } else {
                présentateur?.traiterCreationQuiz(titre, question, choix, reponse)
                présentateur?.ajoutPermission()
            }
        }
    }

    override fun afficherMessageErreur(s: String) {
        Toast.makeText(requireActivity(), s, Toast.LENGTH_LONG).show()
        Log.d("erreur", s)
    }

    fun addEventToCalendar(){
        val startMillis: Long = Calendar.getInstance().run {
            set(2022, 12, 19, 7, 30)
            timeInMillis
        }
        val endMillis: Long = Calendar.getInstance().run {
            set(2022, 0, 19, 8, 30)
            timeInMillis
        }
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
            .putExtra(CalendarContract.Events.TITLE, "Yoga")
            .putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
            .putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
            .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
            .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com")
        startActivity(intent)
    }
}