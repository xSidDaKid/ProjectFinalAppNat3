package com.example.quizzer.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.presentation.registration.IContratVueRegistration.IVueRegistration
import com.google.android.material.textfield.TextInputEditText

/**
 * Classe qui permet de montrer l'interface pour créer un utilisateur et envoyer les données au présentateur
 *
 */
class VueRegistration : Fragment(), IVueRegistration {
    lateinit var navController: NavController
    var présentateur: PrésentateurRegistration? = null
    lateinit var txtConnexion: TextView
    lateinit var btnEnregistrer: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_registration, container, false)
        présentateur = PrésentateurRegistration(this)

        txtConnexion = vue.findViewById<TextView>(R.id.connexion)
        btnEnregistrer = vue.findViewById<Button>(R.id.btnEnregistrer)

        attacherÉcouteurEnregistrer(vue)
        attacherÉcouteurConnexion()
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    /**
     * Méthode qui affiche la page de login après la création d'un utilisateur
     *
     */
    override fun naviguerLogin() {
        navController.popBackStack()
        navController.navigate((R.id.vueLogin))
    }

    /**
     * Méthode qui vérifie si les champs ne sont pas vide, puis envoie les données au présentateur
     *
     * @param vue Vue de Création d'un compte
     */
    override fun attacherÉcouteurEnregistrer(vue: View) {
        btnEnregistrer.setOnClickListener {
            var email = vue.findViewById<TextInputEditText>(R.id.email).text.toString()
            var username = vue.findViewById<TextInputEditText>(R.id.nomUtilisateur).text.toString()
            var mdp = vue.findViewById<TextInputEditText>(R.id.password).text.toString()
            var confirmationMdp =
                vue.findViewById<TextInputEditText>(R.id.confirmerPassword).text.toString()

            if (email == "") {
                vue.findViewById<TextInputEditText>(R.id.email).error = "Invalide"
            } else if (username == "") {
                vue.findViewById<TextInputEditText>(R.id.nomUtilisateur).error = "Invalide"
            } else if (mdp == "") {
                vue.findViewById<TextInputEditText>(R.id.password).error = "Invalide"
            } else if (confirmationMdp == "") {
                vue.findViewById<TextInputEditText>(R.id.confirmerPassword).error = "Invalide"
            } else if (mdp != confirmationMdp) {
                vue.findViewById<TextInputEditText>(R.id.password).error =
                    "Les mots de passe ne correspondent pas"
                vue.findViewById<TextInputEditText>(R.id.confirmerPassword).error =
                    "Les mots de passe ne correspondent pas"
            } else {
                présentateur?.traiterLogin(email, username, mdp)
            }
        }
    }

    /**
     * Méthode qui affiche la page de login si l'utilisateur a déjà un compte
     *
     */
    override fun attacherÉcouteurConnexion() {
        txtConnexion.setOnClickListener {
            présentateur?.traiterLogin()
        }

    }
}