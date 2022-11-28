package com.example.quizzer.presentation.permission

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IVuePermission
import java.security.Permission


class VuePermission : Fragment(), IVuePermission {
    lateinit var navController: NavController;
    var présentateur:PresentateurPermission?=null

    lateinit var listPermission: ListView
    lateinit var adapter: ArrayAdapter<Pair<String,PermissionScore>>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_permission, container, false)
        présentateur = PresentateurPermission(Modèle,this)
        listPermission = vue.findViewById(android.R.id.list)
        initialiserListeQuiz()
        attacherÉcouteurAuxQuiz()
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view);
    }

    fun initialiserListeQuiz() {
        Log.d("testArray","creer array")
        adapter = ArrayAdapter<Pair<String,PermissionScore>>(requireContext(), android.R.layout.simple_list_item_1, présentateur!!.getTousPermissionsList())
        Log.d("testArray","creer array1")
        this.listPermission.adapter = adapter
        Log.d("testArray","creer array2")
    }

    fun attacherÉcouteurAuxQuiz() {
        listPermission.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(requireActivity(), "clicked : $position", Toast.LENGTH_LONG).show()
        }
    }


}