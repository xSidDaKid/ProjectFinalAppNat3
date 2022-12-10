package com.example.quizzer.presentation

import android.util.Log
import com.example.quizzer.accesAuxDonnées.ISourceDeDonées
import com.example.quizzer.accesAuxDonnées.ReponsesParDefaut
import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz
import com.example.quizzer.domaine.entité.QuizUtilisateurScore
import com.example.quizzer.domaine.entité.Utilisateur
import com.example.quizzer.domaine.interacteur.ObtenirReponses
import com.example.quizzer.domaine.interacteur.VerificationReponse

/**
 * Classe qui permet d'intéragir avec la base de donnée
 */
class Modèle(var sourceDeDonne: ISourceDeDonées = ReponsesParDefaut()) {

    private var quizScore = QuizUtilisateurScore()
    //private var utilisateur = Utilisateur()


    var tourDesRéponses: Int = 0
    var quizListe = mutableListOf<Quiz>()
    var utilisateurListe = mutableListOf<Utilisateur>()
    var mapPermission = mapOf<String, PermissionScore>()
    var permissionListe = mutableListOf<PermissionScore>()
    var indexQuizListe: Int = 0

    var utilisateurConnecte = Utilisateur("", "", "")
    var quizSelected = Quiz("", "", emptyList(), emptyList(), utilisateurConnecte)

    var mapQuiz = mapOf<Int, Quiz>()
    var mapUser = mapOf<Int, Utilisateur>()
    var mapPermissionScore = mapOf<Int, PermissionScore>()

    init {
        var user1 = Utilisateur("a@mail.com", "bob", "mdp")
        var user2 = Utilisateur("b@mail.com", "marc", "mdp")
        var user3 = utilisateurConnecte


//        var permission1 = PermissionScore(user1,newQuiz1,0)
//        var permission2 = PermissionScore(user1,newQuiz2,0)
//        var permission3 = PermissionScore(user3,newQuiz2,5)
//        var permission4 = PermissionScore(user3,newQuiz1,2)
//        var permission5 = PermissionScore(user3,newQuiz2,10)
//
//        permissionListe.add(permission1)
//        permissionListe.add(permission2)
//        permissionListe.add(permission3)
//        permissionListe.add(permission4)
//        permissionListe.add(permission5)
//        mapPermission+= ("1" to permission1)
//        mapPermission+=("2" to permission2)
//        mapPermission+=("3" to permission3)
    }

    /**
     * API - GET
     */

    /**
     * Méthode qui permet d'avoir la liste des quiz
     *
     * @return Liste des quiz
     */
    fun getListeQuiz(): Map<Int, Quiz> {
        mapQuiz = sourceDeDonne.obtenirQuiz()
        return mapQuiz
    }

    /**
     * Méthode qui permet d'avoir la liste des utilisateurs
     *
     * @return Liste des utilisateurs
     */
    fun getListeUtilisateur(): Map<Int, Utilisateur> {
        mapUser = sourceDeDonne.obtenirUtilisateurs()
        return mapUser
    }

    /**
     * Méthode qui permet d'avoir la liste des permissions
     *
     * @return Liste des permissions
     */
    fun getListePermission(): Map<Int, PermissionScore> {
        mapPermissionScore = sourceDeDonne.obtenirPermissions()
        return mapPermissionScore
    }

    /**
     * API - POST
     */

    /**
     * Méthode qui permet d'ajouter un nouveau quiz à la base de donnée
     *
     * @param titre Titre du Quiz
     * @param question Question du Quiz
     * @param choix Choix du Quiz
     * @param reponse Réponses à associer à un quiz
     */
    fun ajouterQuiz(titre: String, question: String, choix: List<String>, reponse: List<String>) {
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

        var newQuiz = Quiz(titre, question, choix, reponseTrier, mapUser.getValue(getIdUtilisateur()))
        sourceDeDonne.postQuiz(newQuiz, 2)
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
        var newUtilisateur = Utilisateur(email, username, mdp)
        sourceDeDonne.postUtilisateur(newUtilisateur)
        utilisateurListe.add(newUtilisateur)
    }

    /**
     * Méthode qui permet d'ajouter une nouvelle permission à la base de donnée
     *
     * @param email Email de l'utilisateur
     * @param username Nom d'utilisateur de l'utilisateur
     * @param mdp Mot de passe de l'utilisateur
     */
    fun ajouterPermission(quiz: Quiz, utilisateur: Utilisateur, score: Int) {
        var newPermissionScore = PermissionScore(utilisateur, quiz, score)
        sourceDeDonne.postPermissionScore(newPermissionScore)
    }

    /**
     * UTILISATEUR - MÉTHODE POUR AVOIR LES INFOS DE L'UTILISATEUR
     */

    /**
     * Avoir le ID de l'utilisateur
     *
     * @return ID Utilisateur
     */
    fun getIdUtilisateur(): Int {
        for ((key, value) in mapUser) {
            if (utilisateurConnecte == value) {
                return key
            }
        }
        return 0
    }

    /**
     * Méthode qui sauvegarde les informations sur l'utilisateur apres la connexion
     *
     * @param nomUtilisateur Nom de l'utilisateur
     * @param password Mot de passe de l'utilisateur
     * @return True s'il existe; False s'il n'existe pas
     */
    fun getUilisateur(utilisateur: Utilisateur) {
        utilisateurConnecte = utilisateur
    }

    /**
     * Méthode qui permet d'avoir le nom de l'utilisateur
     *
     * @return Nom d'utilisateur
     */
    fun getNomUtilisateur(): String {
        return utilisateurConnecte.nomUtilisateur
    }

    /**
     * QUIZ - MÉTHODE POUR AVOIR LES INFOS D'UN QUIZ
     */
    fun getIDQuiz(quizIndex: Int) {
        indexQuizListe = quizIndex + 1
        for ((key, value) in mapQuiz) {
            if (key == indexQuizListe) {
                quizSelected = value
            }
        }
    }

    /**
     * Méthode qui permet d'initialiser un quiz a partir d'une liste
     *
     */
    fun getQuizSelectionner(): Quiz {
        return quizSelected
    }

    /**
     * MÉTHODE POUR INITIALISER UN QUIZ
     */

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
     * Méthode qui permet d'avoir le score de l'utilisateur
     *
     * @param quiz Quiz choisi
     * @return Le score de l'utilisateur
     */
    fun getScore(quiz: Quiz): Int {
        return quizScore.score
    }

    /**
     * Méthode qui permet d'avoir la prochaine réponse
     *
     * @param quiz Quiz choisi
     * @return
     */
    fun getProchaineRéponse(quiz: Quiz): String {
        var réponseString = ""
        if (tourDesRéponses >= quiz.reponses.size) {
            tourDesRéponses = 0
        } else {
            var réponse = quiz.reponses.get(tourDesRéponses)
            tourDesRéponses++
            var réponseString = réponse.toList().get(0).first
            return réponseString
        }
        return réponseString
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
     *
     * --------------------------------
     */

    fun getListeQuizSync(): List<Quiz> {
        return quizListe
    }

    fun getTousPermission(): Map<String, PermissionScore> {
        return mapPermission
    }

    /**
     * Méthode qui permet de réinitialiser le tour des réponses
     *
     */
    fun reinitialiserReponse() {
        tourDesRéponses = 0
    }

    fun ajouterPermission(email: String, position: Int) {
        var utilisateurTrouve: Utilisateur
        var quizTrouve: Quiz
        quizTrouve = quizListe.get(position)
        for (utilisateur in utilisateurListe) {
            if (utilisateur.courriel == email) {
                utilisateurTrouve = utilisateur
                var permission = PermissionScore(utilisateurTrouve, quizTrouve, 0)
                permissionListe.add(permission)
            }
        }
    }

    fun getListePermissionParEmail(): MutableList<PermissionScore> {
        var listeFiltré = mutableListOf<PermissionScore>()
        for (permission in permissionListe) {
            if (permission.utilisateur?.courriel == utilisateurConnecte.courriel) {
                listeFiltré.add(permission)
            }

        }
        return listeFiltré
    }

    fun chercherPermissions(): MutableList<PermissionScore> {
        Log.d("testapi", "chercherPermission")
        var mapPermissionScore = sourceDeDonne.obtenirPermissions()
        for (item in mapPermissionScore) {
            permissionListe.add(item.value)
        }
        return permissionListe
    }

    fun getRéponseParIndex(index: Int, quiz: Quiz): Map<String, String> {
        return quiz.reponses.get(index)
    }

    /*
  /**
  MÉTHODE INUTILISÉ
 */
    fun getReponseTrier(reponseBrut: String): List<Map<String, String>> {
        return ObtenirReponses().trierReponses(reponseBrut)
    }

    fun getReponses(quiz: Quiz): List<Map<String, String>> {
        return quiz.reponses
    }

    fun getChoix(quiz: Quiz): List<String> {
        return quiz.choix
    }

    fun getPassword(utilisateur: Utilisateur): String {
        return utilisateur.motDePasse
    }

    fun veriferQuiz(choix: String, reponse: String): String {
        return VerificationReponseCreationQuiz().verificationReponseCreationQuiz(choix, reponse)
    }*/
}

var modèle = Modèle()