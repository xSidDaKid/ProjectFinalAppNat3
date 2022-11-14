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
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.registration.IContratVueRegistration.IVueRegistration


class VueRegistration : Fragment(), IVueRegistration {
    lateinit var navController: NavController;
    var présentateur: PrésentateurRegistration? = null
    lateinit var txtConnexion: TextView
    lateinit var btnEnregistrer: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.fragment_registration, container, false)
        présentateur = PrésentateurRegistration(Modèle, this)

        txtConnexion = vue.findViewById<TextView>(R.id.connexion)
        btnEnregistrer = vue.findViewById<Button>(R.id.btnEnregistrer)

        attacherÉcouteurConnexion()
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view);
    }

    override fun naviguerLogin() {
        navController.popBackStack()
        navController.navigate((R.id.vueLogin))
    }

    private fun attacherÉcouteurConnexion() {
        txtConnexion.setOnClickListener {
            présentateur?.traiterLogin()
        }
        btnEnregistrer.setOnClickListener {
            présentateur?.traiterLogin()
        }
    }
}