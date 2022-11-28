package com.example.quizzer.presentation

import com.example.quizzer.accesAuxDonnées.ReponsesParDefaut
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.QuizUtilisateurScore
import com.example.quizzer.domaine.entité.Utilisateur
import com.example.quizzer.domaine.interacteur.ObtenirReponses
import com.example.quizzer.domaine.interacteur.VerificationReponse
import com.example.quizzer.domaine.interacteur.VerificationReponseCreationQuiz

/**
 * Object qui permet d'intéragir avec la base de donnée
 * TODO: Ajout des quiz dans la BD,
 * @IMPORTANT: Pour une raison quelconque, lors de l'ajout d'un quiz dans la liste, elle est ajouté au début de la liste et non à la fin!!!
 */
object Modèle {

    private var quizScore = QuizUtilisateurScore()
    //private var utilisateur = Utilisateur()

    var tourDesRéponses: Int = 0
    var quizListe = mutableListOf<Quiz>()
    var utilisateurListe = mutableListOf<Utilisateur>()

    /**
     * Méthode qui permet d'initialiser un quiz a partir d'une liste
     *
     */
    fun initialiserQuizParDefaut(): Quiz {
        /*var newQuiz = Quiz(
            0,
            "Les fruits et leurs couleurs",
            "Quelle est la couleur du fruit?",
            listOf<String>("Jaune", "Rouge", "Orange", "Vert"),
            ObtenirReponses().obtenirReponses(ReponsesParDefaut())
        )*/
        //quizListe.add(newQuiz)
        return quizListe[0]
    }

    /**
     * Méthode qui permet de valider une réponse et de changer le score de l'utilisateur
     *
     * @param reponse La réponse de l'utilisateur
     * @param index
     * @return
     */
    fun soumettreRéponse(reponse: String, index: Int, quiz: Quiz): Boolean {
        var verification = VerificationReponse().verificationReponse(reponse, index, quiz)
        if (verification) {
            quizScore.score++
        }
        return verification
    }

    /**
     * Méthode qui permet d'ajouter un nouveau quiz à la base de donnée
     *
     * @param titre Titre du Quiz
     * @param question Question du Quiz
     * @param choix Choix du Quiz
     * @param reponse Réponses à associer à un quiz
     */
    fun ajouterQuiz(
        titre: String,
        question: String,
        choix: List<String>,
        reponse: List<String>
    ) {
        var compteur = 0
        var reponseTrier: List<Map<String, String>> = emptyList()

        while (compteur < choix.size) {
            var reponseTrierChoix: List<String> =
                ObtenirReponses().trierReponses2(reponse[compteur])
            for (item in reponseTrierChoix) {
                reponseTrier += mapOf(item to choix[compteur])
            }
            compteur++
        }

        var newQuiz = Quiz(1, titre, question, choix, reponseTrier)
        quizListe.add(newQuiz)
    }

    /**
     * Méthode qui permet d'ajouter un nouveau utilisateur à la base de donnée
     *
     * @param email Email de l'utilisateur
     * @param username Nom d'utilisateur de l'utilisateur
     * @param mdp Mot de passe de l'utilisateur
     */
    fun ajouterUtilisateur(email: String, username: String, mdp: String) {
        var newUtilisateur = Utilisateur(1, email, username, mdp)
        utilisateurListe.add(newUtilisateur)
    }

    /**
     * Méthode qui permet de mettre réponse dans un tableau (Map)
     *
     * @param reponseBrut Réponse brut du créateur du quiz avec les , et :
     * @return
     */
    /*fun getReponseTrier(reponseBrut: String): List<Map<String, String>> {
        return ObtenirReponses().trierReponses(reponseBrut)
    }*/
    /* fun getReponseTrier(reponseBrut: List<String>): List<String> {
         return ObtenirReponses().trierReponses(reponseBrut)
     }*/

    /**
     * Méthode qui permet d'avoir la prochaine réponse
     *
     * @param quiz Quiz choisi
     * @return
     */
    fun getProchaineRéponse(quiz: Quiz): String {
        if (tourDesRéponses >= quiz.reponses.size)
            return ""
        else {
            var réponse = quiz.reponses.get(tourDesRéponses)
            tourDesRéponses++
            var réponseString = réponse.toList().get(0).first
            return réponseString
        }
    }

    /**
     * TODO
     *
     * @return
     */
    fun getIndexRéponse(): Int {
        return tourDesRéponses
    }

    /**
     * Méthode qui permet d'avoir le titre du Quiz
     *
     * @param quiz Quiz choisi
     * @return Le titre du Quiz
     */
    fun getTitre(quiz: Quiz): String {
        return quiz.titre
    }

    /**
     * Méthode qui permet d'avoir la question du Quiz
     *
     * @param quiz Quiz choisi
     * @return La question du Quiz
     */
    fun getQuestion(quiz: Quiz): String {
        return quiz.question
    }

    /**
     * Méthode qui permet d'avoir le score de l'utilisateur
     *
     * @param quiz Quiz choisi
     * @return Le score de l'utilisateur
     */
    fun getScore(quiz: Quiz): Int {
        return quizScore.score
    }

    /**
     * Méthode qui vérifie si l'utilisateur existe dans la BD
     *
     * @param nomUtilisateur Nom de l'utilisateur
     * @param password Mot de passe de l'utilisateur
     * @return True s'il existe; False s'il n'existe pas
     */
    fun getUilisateur(nomUtilisateur: String, password: String): Boolean {
        for (item in utilisateurListe) {
            if (item.nomUtilisateur == nomUtilisateur) {
                if (item.motDePasse == password) {
                    return true
                }
            }
        }
        return false
    }

    fun getRéponseParIndex(index: Int, quiz: Quiz): Map<String, String> {
        return quiz.reponses.get(index)
    }

    fun getReponses(quiz: Quiz): List<Map<String, String>> {
        return quiz.reponses
    }

    fun getChoix(quiz: Quiz): List<String> {
        return quiz.choix
    }


    fun getnomUtilisateur(utilisateur: Utilisateur): String {
        return utilisateur.nomUtilisateur
    }

    fun getPassword(utilisateur: Utilisateur): String {
        return utilisateur.motDePasse
    }

    fun veriferQuiz(choix: String, reponse: String): String {
        return VerificationReponseCreationQuiz().verificationReponseCreationQuiz(choix, reponse)
    }


}