package com.example.quizzer.presentation.menuPrincipal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.presentation.menuPrincipal.IContratVuePresentateurMenuPrincipal.IVueMenuPrincipal

/**
 * Classe qui permet de montrer l'interface pour montrer le menu principal et envoyer les données au présentateur
 *
 */
class VueMenuPrincipal : Fragment(), IVueMenuPrincipal {

    lateinit var navController: NavController
    lateinit var btnDeconnecter: Button
    lateinit var btnCreerQuiz: Button
    lateinit var btnListeQuiz: Button
    lateinit var btnPermission: Button
    lateinit var btnScore: Button
    lateinit var nom: TextView

    var présentateur: PresentateurMenuPrincipal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_menu_principal, container, false)
        présentateur = PresentateurMenuPrincipal(this)

        nom = vue.findViewById(R.id.nom)
        btnDeconnecter = vue.findViewById<Button>(R.id.logout)
        btnCreerQuiz = vue.findViewById<Button>(R.id.btnCreerQuiz)
        btnListeQuiz = vue.findViewById<Button>(R.id.listeQuiz)
        btnPermission = vue.findViewById(R.id.btnPermission)
        btnScore = vue.findViewById(R.id.btnScore)

        attacherÉcouteurDeconnecter()
        attacherÉcouteurCreerQuiz()
        attacherÉcouteurDemarrerQuiz()
        attacherÉcouteurVoirPermission()
        attacherÉcouteurVoirScore()
        obtenirNomUtilisateur()

        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    /**
     * Méthode qui permet de se déconnecter
     *
     */
    private fun attacherÉcouteurDeconnecter() {
        btnDeconnecter.setOnClickListener {
            présentateur?.seDeconnecter()
        }
    }

    /**
     * Méthode qui redirige vers la page de login
     *
     */
    override fun naviguerVersLogin() {
        navController.navigate(R.id.vueLogin)
    }

    /**
     * Méthode qui affiche la page pour créer un compte
     *
     */
    private fun attacherÉcouteurCreerQuiz() {
        btnCreerQuiz.setOnClickListener {
            présentateur?.creerQuiz()
        }
    }

    /**
     * Méthode qui redirige vers la page pour créer un quiz
     *
     */
    override fun naviguerVersCreationQuiz() {
        navController.navigate(R.id.creationQuiz)
    }

    /**
     * Méthode qui redirige vers la page de la liste des quiz
     *
     */
    private fun attacherÉcouteurDemarrerQuiz() {
        btnListeQuiz.setOnClickListener {
            présentateur?.demarrerListeQuiz()
        }
    }

    /**
     * Méthode qui redirige vers la page de la liste des scores
     *
     */
    override fun naviguerVersListeQuiz() {
        navController.navigate(R.id.listQuiz)
    }

    /**
     * Méthode qui redirige vers la page des scores
     *
     */
    private fun attacherÉcouteurVoirScore() {
        btnScore.setOnClickListener {
            présentateur?.voirScore()
        }
    }

    /**
     * Méthode qui redirige vers la page de score
     *
     */
    override fun naviguerVersListeScore() {
        navController.navigate(R.id.scoreFragment)
    }

    /**
     * Méthode qui redirige vers pour inviter un utilisateur à un quiz
     *
     */
    private fun attacherÉcouteurVoirPermission() {
        btnPermission.setOnClickListener {
            présentateur?.voirPermissions()
        }
    }

    /**
     * Méthode qui redirige vers la page d'invitation
     *
     */
    override fun naviguerVersVoirPermission() {
        navController.navigate(R.id.permissionFragment)
        Log.d("test1", "ici")
    }

    /**
     * Méthode qui permet d'avoir le nom de l'utilisateur
     *
     */
    override fun obtenirNomUtilisateur() {
        nom.append(présentateur?.getNomUtilisateur())
    }


}