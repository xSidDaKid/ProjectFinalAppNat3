package com.example.quizzer.presentation.listQuiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import androidx.navigation.NavController
import com.example.quizzer.R
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.listQuiz.IContratVuePresentateurListQuiz.*
import com.example.quizzer.presentation.login.PresentateurLogin

class VueListQuiz : ListFragment(), IVueListQuiz {

    lateinit var navController: NavController
    lateinit var listQuiz: ListView
    lateinit var adapter: ArrayAdapter<Quiz>;
    var présentateur: PresentateurListQuiz? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_list_quiz, container, false)
        présentateur = PresentateurListQuiz(Modèle, this)
        listQuiz = vue.findViewById(android.R.id.list)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, présentateur!!.getListeQuiz())
        return vue
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun naviguerVersQuiz(id: Int) {
        TODO("Not yet implemented")
    }

    override fun initialiserListeQuiz() {
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, présentateur!!.getListeQuiz())
        listQuiz.adapter = adapter
    }
}