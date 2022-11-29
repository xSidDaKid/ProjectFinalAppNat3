package com.example.quizzer.presentation.listQuiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentController
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.quizzer.R
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.listQuiz.IContratVuePresentateurListQuiz.IVueListQuiz

/**
 * Vue liste quiz qui permet d'afficher les données et permet d'informer le Présenteur à la suite d'une action de l'utilisateur
 *
 */
class VueListQuiz : Fragment(), IVueListQuiz {

    lateinit var navController: NavController
    lateinit var listQuiz: ListView
    lateinit var adapter: ArrayAdapter<Quiz>
    var présentateur: PresentateurListQuiz? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_list_quiz, container, false)
        présentateur = PresentateurListQuiz(Modèle, this)
        listQuiz = vue.findViewById(android.R.id.list)
        initialiserListeQuiz()
        attacherÉcouteurAuxQuiz()
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view);
    }

    /**
     * Méthode qui permet de diriger l'utilisateur vers la vue du quiz
     *
     */
    override fun naviguerVersQuiz() {
        findNavController().popBackStack(R.id.vueMenuPrincipal,false)
        findNavController().navigate(R.id.quizFragment)
    }

    /**
     * Méthode qui permet d'afficher la liste des quiz
     *
     */
    override fun initialiserListeQuiz() {
        adapter = ArrayAdapter<Quiz>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            présentateur!!.getListeQuiz()
        )
        this.listQuiz.adapter = adapter
        Log.d("Test1", "Initializing list quiz")
    }

    /**
     * Méthode qui permet savoir quel quiz l'utilisateur a choisi
     *
     */
    override fun attacherÉcouteurAuxQuiz() {
        listQuiz.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(requireActivity(), "clicked : $position", Toast.LENGTH_SHORT).show()
            présentateur?.reinitialiserReponse()
            présentateur?.getQuiz(position)
        }
    }
}