package com.example.quizzer.presentation.login

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
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.login.IContratVuePresentateurLogin.IVueLogin
import com.google.android.material.textfield.TextInputEditText

/**
 * Classe qui permet de montrer l'interface pour se connecter et envoyer les données au présentateur
 *
 */
class VueLogin : Fragment(), IVueLogin {

    lateinit var navController: NavController;
    lateinit var btnLogin: Button
    lateinit var txtEnregistrer: TextView
    var présentateur: PresentateurLogin? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_login, container, false)
        présentateur = PresentateurLogin( this)

        btnLogin = vue.findViewById<Button>(R.id.btnLogin)
        txtEnregistrer = vue.findViewById<TextView>(R.id.txtEnregistrer)
        attacherÉcouteurLogin(vue)
        attacherÉcouteurEnregistrement()
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view);
    }

    /**
     * Méthode qui permet de diriger l'utilisateur vers le menu principal
     *
     */
    override fun naviguerMenu() {
        navController.popBackStack()
        navController.navigate(R.id.vueMenuPrincipal)
    }

    /**
     * Méthode qui permet de diriger l'utilisateur vers l'interface pour créer un utilisateur
     *
     */
    override fun naviguerEnregistrer() {
        navController.navigate(R.id.registrationFragment)
    }

    /**
     * Méthode qui permet le nom d'utilisateur et le mot de passe de l'utilisateur au présentateur
     *
     * @param vue Interface de login
     */
    override fun attacherÉcouteurLogin(vue: View) {
        btnLogin.setOnClickListener {
            var nomUtilisateur = vue.findViewById<TextInputEditText>(R.id.username).text.toString()
            var password = vue.findViewById<TextInputEditText>(R.id.password).text.toString()
            //IF TO BE DELETED
            if (nomUtilisateur == "" && password == "") {
                présentateur?.traiterMenu()
            } else if (présentateur?.verifierConnexion(nomUtilisateur, password) == true) {
                présentateur?.traiterMenu()
            } else {
                vue.findViewById<TextInputEditText>(R.id.username).setError("Invalide")
                vue.findViewById<TextInputEditText>(R.id.password).setError("Invalide")
                navController = Navigation.findNavController(vue);
            }
        }
    }

    /**
     * Méthode qui permet de diriger l'utilisateur vers l'interface pour créer un utilisateur
     *
     */
    override fun attacherÉcouteurEnregistrement() {
        txtEnregistrer.setOnClickListener {
            présentateur?.traiterEnregistrer()
        }
    }
}
