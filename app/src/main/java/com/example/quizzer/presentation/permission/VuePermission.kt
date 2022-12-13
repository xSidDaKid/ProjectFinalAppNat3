package com.example.quizzer.presentation.permission

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.Utilisateur
import com.example.quizzer.presentation.permission.IContratVuePresentateurPermission.IVuePermission


class VuePermission : Fragment(), IVuePermission {
    lateinit var navController: NavController;
    var présentateur: PresentateurPermission? = null

    lateinit var listPermission: ListView
    lateinit var adapter: ArrayAdapter<Quiz>
    lateinit var loading: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_permission, container, false)
        présentateur = PresentateurPermission(this)
        listPermission = vue.findViewById(android.R.id.list)
        loading = vue.findViewById(R.id.loading)
        attacherÉcouteurAuxQuiz()
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        initialiserListeQuiz(présentateur?.getListeQuizSync())
    }

    override fun initialiserListeQuiz(liste: Array<Quiz>?) {
        Log.d("testArray", "creer array")
        adapter = ArrayAdapter<Quiz>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            liste!!
        )
        this.listPermission.adapter = adapter
        loading.setVisibility(View.GONE)
    }

    fun attacherÉcouteurAuxQuiz() {
        listPermission.setOnItemClickListener { parent, view, position, id ->
            présentateur?.dialogPermission(position)
            Toast.makeText(requireActivity(), "clicked : $position", Toast.LENGTH_SHORT).show()

        }
    }

    override fun afficherLoading() {
        loading.visibility = View.VISIBLE
    }

    /*fun afficherToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    fun creerPermission(email: String, position: Int) {
        présentateur?.findQuizChoisi(position)
        présentateur?.creerPermission(email, position)
    }*/

    override fun montrerDialog(position: Int) {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Veuillez écrire le email")
        var inputEmail: String = ""
        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton(
            "Ajouter"
        ) { dialog, which -> verifierEmail(input.text.toString(), position) }
        builder.setNegativeButton(
            "Annuler"
        ) { dialog, which -> dialog.cancel() }

        builder.show()
//        var dialog = Dialog(requireActivity())
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(true)
//        dialog.setContentView(R.layout.dialog_permission)
//        var input = dialog.findViewById(R.id.txtCourriel) as EditText
//        var btnAjouter = dialog.findViewById(R.id.btnAjouter) as Button
//        dialog.show()


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

    private fun verifierEmail(email: String, position: Int) {
        présentateur?.findUserChoisi(email, position)
    }

    override fun creerPermission(position: Int, user: Utilisateur) {
        loading.visibility = View.GONE
        if (user != null) {
            var quiz = présentateur?.findQuizChoisi(position)
            présentateur?.ajoutPermission(quiz!!, user)
        } else {

        }
    }

}