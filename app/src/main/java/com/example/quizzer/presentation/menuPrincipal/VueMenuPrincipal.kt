package com.example.quizzer.presentation.menuPrincipal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.menuPrincipal.IContratVuePresentateurMenuPrincipal.IVueMenuPrincipal

/**
 * Classe qui permet de montrer l'interface pour montrer le menu principal et envoyer les données au présentateur
 *
 */
class VueMenuPrincipal : Fragment(), IVueMenuPrincipal {

    lateinit var navController: NavController;
    lateinit var btnCreerQuiz: Button
    lateinit var btnDemarrer: Button
    lateinit var btnPermission: Button

    var présentateur: PresentateurMenuPrincipal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_menu_principal, container, false)
        présentateur = PresentateurMenuPrincipal(Modèle, this)

        btnPermission = vue.findViewById(R.id.btnPermission)
        btnCreerQuiz = vue.findViewById<Button>(R.id.btnCreerQuiz)
        btnDemarrer = vue.findViewById<Button>(R.id.btnDemarrer)
        attacherÉcouteurCreerQuiz()
        attacherÉcouteurDemarrerQuiz()
        attacherÉcouteurVoirPermission()
        Log.d("test1","created")

        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view);
    }

    /**
     * Méthode qui affiche la page pour créer un compte
     *
     */
    override fun attacherÉcouteurCreerQuiz() {
        btnCreerQuiz.setOnClickListener {
            présentateur?.creerQuiz()
        }
    }

    /**
     * TODO
     *
     */
    override fun attacherÉcouteurDemarrerQuiz() {
        btnDemarrer.setOnClickListener {
            présentateur?.demarrerListeQuiz()
        }
    }

    override fun attacherÉcouteurListQuiz() {

    }



    override fun attacherÉcouteurVoirPermission() {
        Log.d("test1","attached")
        btnPermission.setOnClickListener {
            présentateur?.voirPermissions()
            Log.d("test1","pressed")
        }
    }
    /**
     * Méthode qui redirige vers la page pour créer un quiz
     *
     */
    override fun naviguerVersCreationQuiz() {
        navController.navigate(R.id.creationQuiz)
    }

    override fun naviguerVersListeQuiz() {
        navController.navigate(R.id.listQuiz)
    }

    override fun naviguerVersDemarrerQuiz() {
        navController.navigate(R.id.listQuiz)
    }

    override fun naviguerVersVoirPermission() {
        navController.navigate(R.id.permissionFragment)
        Log.d("test1","ici")
    }

}