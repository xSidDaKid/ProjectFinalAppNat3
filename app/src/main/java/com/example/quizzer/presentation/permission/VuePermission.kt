package com.example.quizzer.presentation.permission

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.MainActivity
import com.example.quizzer.R
import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IVuePermission
import java.security.Permission


class VuePermission : Fragment(), IVuePermission {
    lateinit var navController: NavController;
    var présentateur:PresentateurPermission?=null

    lateinit var listPermission: ListView
    lateinit var adapter: ArrayAdapter<Quiz>

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
        adapter = ArrayAdapter<Quiz>(requireContext(), android.R.layout.simple_list_item_1, présentateur!!.getListeQuiz())
        Log.d("testArray","creer array1")
        this.listPermission.adapter = adapter
        Log.d("testArray","creer array2")
    }

    fun attacherÉcouteurAuxQuiz() {
        listPermission.setOnItemClickListener { parent, view, position, id ->
            présentateur?.dialogPermission(position)
        }
    }



    override fun montrerDialog() {
        var dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(requireView())
        var input = dialog.findViewById(R.id.txtCourriel) as EditText
        var btnAjouter = dialog.findViewById(R.id.btnAjouter) as Button
        dialog.show()


//        return activity?.let {
//            val builder = AlertDialog.Builder(requireContext())
//            // Get the layout inflater
//            val inflater = requireActivity().layoutInflater;
//            Log.d("test", "icicicic")
//
//            // Inflate and set the layout for the dialog
//            // Pass null as the parent view because its going in the dialog layout
//            builder.setView(inflater.inflate(R.layout.dialog_permission, null))
//                // Add action buttons
//                .setPositiveButton("Ajouter", DialogInterface.OnClickListener { dialog, id ->
//                    Toast.makeText(requireActivity(), "clicked ", Toast.LENGTH_LONG).show()
//                })
//            builder.create()
//
//        } ?: throw IllegalStateException("Activity cannot be null")


    }


}