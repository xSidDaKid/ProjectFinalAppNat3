package com.example.quizzer.presentation.listQuiz

import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.presentation.Modèle
import com.example.quizzer.presentation.listQuiz.IContratVuePresentateurListQuiz.IPresentateurListQuiz
import com.example.quizzer.presentation.listQuiz.IContratVuePresentateurListQuiz.IVueListQuiz

/**
 * Classe qui permet de communiquer avec le modèle et changer la vue
 *
 * @property modele Objet qui permet de communiquer avec l'API
 * @property vue Vue Liste Quiz
 */
class PresentateurListQuiz(
    var modele: Modèle,
    var vue: IVueListQuiz = VueListQuiz()
) : IPresentateurListQuiz {

    /**
     * Méthode qui permet de recevoir la liste des quiz du modèle
     *
     * @return La liste des quiz
     */
    override fun getListeQuiz(): Array<Quiz> {
        return modele.getListeQuiz().toTypedArray()
    }

    /**
     * Méthode qui permet de recevoir le quiz choisi par l'utilisateur et diriger l'utiliser vers ce quiz
     *
     * @param listePosition La position du quiz
     */
    override fun getQuiz(listePosition: Int) {
        modele.setIndexQuiz(listePosition)
        vue.naviguerVersQuiz()
    }

    /**
     * Méthode qui réinitialiser les réponses d'un quiz
     *
     */
    override fun reinitialiserReponse() {
        modele.reinitialiserReponse()
    }
}