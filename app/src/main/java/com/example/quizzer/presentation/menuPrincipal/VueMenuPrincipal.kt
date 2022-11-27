package com.example.quizzer.presentation.menuPrincipal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.creationQuiz.PresentateurCreationQuiz

class VueMenuPrincipal : Fragment(), IContratVuePresentateurMenuPrincipal.IVueMenuPrincipal {

    lateinit var navController: NavController;
    lateinit var btnCreerQuiz: Button
    lateinit var btnDemarrer: Button
    var présentateur: PresentateurMenuPrincipal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_menu_principal, container, false)
        présentateur = PresentateurMenuPrincipal(Modèle, this)

        btnCreerQuiz = vue.findViewById<Button>(R.id.btnCreerQuiz)
        btnDemarrer = vue.findViewById<Button>(R.id.btnDemarrer)
        attacherÉcouteurCreerQuiz()
        attacherÉcouteurDemarrerQuiz()

        return vue
    }

    private fun attacherÉcouteurCreerQuiz() {
        btnCreerQuiz.setOnClickListener {
            présentateur?.creerQuiz()
        }
    }

    private fun attacherÉcouteurDemarrerQuiz() {
        btnDemarrer.setOnClickListener {
            présentateur?.demarrerQuiz()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view);
    }

    override fun naviguerVersCreationQuiz() {
        navController.navigate(R.id.creationQuiz)
    }

    override fun naviguerVersListeQuiz() {
        navController.navigate(R.id.listQuiz)
    }

    override fun naviguerVersDemarrerQuiz() {
        navController.navigate(R.id.quizFragment)
    }

}