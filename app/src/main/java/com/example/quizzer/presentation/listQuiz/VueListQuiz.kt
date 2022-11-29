package com.example.quizzer.presentation.listQuiz

import android.content.Context
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.quizzer.R
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.listQuiz.IContratVuePresentateurListQuiz.*
import com.example.quizzer.presentation.login.PresentateurLogin

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

    override fun naviguerVersQuiz() {
        findNavController().navigate(R.id.action_listQuiz_to_quizFragment)
    }

    override fun initialiserListeQuiz() {
        adapter = ArrayAdapter<Quiz>(requireContext(), android.R.layout.simple_list_item_1, présentateur!!.getListeQuiz())
        this.listQuiz.adapter = adapter
        Log.d("Test1", "Initializing list quiz")
    }

    override fun attacherÉcouteurAuxQuiz() {
        listQuiz.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(requireActivity(), "clicked : $position", Toast.LENGTH_SHORT).show()
            présentateur?.getQuiz(position)
        }
    }
}