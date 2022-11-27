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

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun naviguerVersQuiz(id: Int) {
        TODO("Not yet implemented")
    }

    override fun initialiserListeQuiz() {
        adapter = ArrayAdapter<Quiz>(requireContext(), android.R.layout.simple_list_item_1, présentateur!!.getListeQuiz())
        this.listQuiz.adapter = adapter
        Log.d("Test1", "Initializing list quiz")

    }

    override fun attacherÉcouteurAuxQuiz() {
        Log.d("Test3", "Looking if it even gets here")
       // this.listQuiz.onItemClickListener = AdapterView.OnItemClickListener {
        //        parent, view, position, id ->
       //     Toast.makeText(requireActivity(),"Clicked item : $position", Toast.LENGTH_LONG).show()
       //     Log.d("Test2", "Adding on Item Click listener")
       // }
        this.listQuiz.onItemClickListener = object : OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(requireActivity(), "Clicked item : $p1", Toast.LENGTH_LONG).show()
                Log.d("Test2", "Adding on Item Click listener")
            }
        }
    }
}