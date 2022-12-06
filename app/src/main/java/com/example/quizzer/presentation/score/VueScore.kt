package com.example.quizzer.presentation.score

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.registration.PrésentateurRegistration

/**
 * A simple [Fragment] subclass.
 * Use the [ScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VueScore : Fragment(),IContratVuePresentateurScore.IVueScore {

    lateinit var navController: NavController;
    var présentateur: PresentateurScore? = null
    lateinit var listScore: ListView
    lateinit var adapter: ArrayAdapter<PermissionScore>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_score, container, false)
        présentateur = PresentateurScore(Modèle, this)
        listScore = vue.findViewById(android.R.id.list)
        initialiserListeQuiz()
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view);
    }

    fun initialiserListeQuiz() {
        adapter = ArrayAdapter<PermissionScore>(requireContext(), android.R.layout.simple_list_item_1, présentateur!!.getListePermission())
        this.listScore.adapter = adapter
    }

}