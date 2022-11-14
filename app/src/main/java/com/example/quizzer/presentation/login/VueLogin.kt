package com.example.quizzer.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizzer.R
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.login.IContratVuePresentateurLogin.IVueLogin
import com.google.android.material.textfield.TextInputEditText

class VueLogin : Fragment(), IVueLogin {

    lateinit var navController: NavController;
    lateinit var btnLogin: Button
    lateinit var btnEnregistrer: Button
    var présentateur: PresentateurLogin? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_login, container, false)
        présentateur = PresentateurLogin(Modèle, this)

        btnLogin = vue.findViewById<Button>(R.id.btnLogin)
        btnEnregistrer = vue.findViewById<Button>(R.id.btnEnregistrer)
        attacherÉcouteurLogin(vue)
        attacherÉcouteurEnregistrement()
        return vue
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view);
    }

    override fun naviguerMenu() {
        navController.popBackStack()
        navController.navigate(R.id.vueMenuPrincipal)
    }

    override fun naviguerEnregistrer() {
        navController.navigate(R.id.registrationFragment)
    }

    private fun attacherÉcouteurLogin(vue: View) {
        btnLogin.setOnClickListener {
            var nomUtilisateur = vue.findViewById<TextInputEditText>(R.id.username).text.toString()
            var password = vue.findViewById<TextInputEditText>(R.id.password).text.toString()
            if (présentateur?.verifierConnexion(nomUtilisateur, password) == true) {
                présentateur?.traiterMenu()
            } else {
                vue.findViewById<TextInputEditText>(R.id.username).setError("Invalide")
                vue.findViewById<TextInputEditText>(R.id.password).setError("Invalide")
                navController = Navigation.findNavController(vue);
            }
        }
    }

    private fun attacherÉcouteurEnregistrement() {
        btnEnregistrer.setOnClickListener {
            présentateur?.traiterEnregistrer()
        }
    }
}
