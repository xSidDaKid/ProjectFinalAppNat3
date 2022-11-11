package com.example.quizzer.presentation.login

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

class VueLogin : Fragment(), IContratVuePresentateurLogin.IVueLogin {


    lateinit var navController: NavController;
    lateinit var btnLogin: Button
    var présentateur: PresentateurLogin? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_login, container, false)
        présentateur = PresentateurLogin(Modèle, this)

        btnLogin = vue.findViewById<Button>(R.id.btnLogin)
        attacherÉcouteurLogin()
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view);
    }

    override fun naviguerMenu() {
        navController.navigate(R.id.vueMenuPrincipal)
    }

    private fun attacherÉcouteurLogin() {
        btnLogin.setOnClickListener {
            présentateur?.traiterMenu()
        }
    }
}