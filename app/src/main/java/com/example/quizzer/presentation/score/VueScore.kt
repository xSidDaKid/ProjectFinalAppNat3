package com.example.quizzer.presentation.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.domaine.entité.PermissionScore

/**
 * A simple [Fragment] subclass.
 * Use the [ScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VueScore : Fragment(), IContratVuePresentateurScore.IVueScore {

    var présentateur: PresentateurScore? = null

    lateinit var navController: NavController
    lateinit var listScore: ListView
    lateinit var adapter: ArrayAdapter<PermissionScore>
    lateinit var loading: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_score, container, false)
        présentateur = PresentateurScore(this)
        listScore = vue.findViewById(android.R.id.list)
        loading = vue.findViewById(R.id.loading)
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        présentateur?.getListePermission()
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        initialiserListeScore(présentateur?.getListePermission())
    }

    override fun initialiserListeScore(liste: MutableList<PermissionScore>?) {
        adapter = ArrayAdapter<PermissionScore>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            liste!!
        )
        this.listScore.adapter = adapter
        loading.visibility = View.GONE
    }

    override fun afficherMessageErreur(s: String) {
        Toast.makeText(requireActivity(), s, Toast.LENGTH_SHORT).show()
    }

    override fun afficherLoading() {
        loading.visibility = View.VISIBLE
    }


}