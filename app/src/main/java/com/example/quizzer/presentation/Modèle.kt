package com.example.quizzer.presentation

import com.example.quizzer.accesAuxDonnées.ReponsesParDefaut
import com.example.quizzer.domaine.entité.PermissionScore
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

    var utilisateurConnecte=Utilisateur("bobo@mail.com","bobby","root")
    var tourDesRéponses: Int = 0
    var quizListe = mutableListOf<Quiz>()
    var utilisateurListe = mutableListOf<Utilisateur>()
    var mapPermission = mapOf<String,PermissionScore>()
    var permissionListe = mutableListOf<PermissionScore>()
    var indexQuizListe : Int = 0
    init {
        quizListe.add(Quiz( "Test", "Lol", listOf("1", "2", "3", "4"), listOf(mapOf("1" to "hello", "2" to "test", "3" to "lol"))))
        quizListe.add(Quiz( "Test2", "Lol", listOf("1", "2", "3", "4"), listOf(mapOf("1" to "hello", "2" to "test", "3" to "lol"))))
        quizListe.add(Quiz( "Test3", "Lol", listOf("1", "2", "3", "4"), listOf(mapOf("1" to "hello", "2" to "test", "3" to "lol"))))
        var newQuiz1 = Quiz(
            "Les fruits et leurs couleurs",
            "Quelle est la couleur du fruit?",
            listOf<String>("Jaune", "Rouge", "Orange", "Vert"),
            ObtenirReponses().obtenirReponses(ReponsesParDefaut()))
        var newQuiz2 = Quiz(
            "Les fruits et leurs couleurs numero2",
            "quiz2?",
            listOf<String>("Jaune", "Rouge", "Orange", "Vert"),
            ObtenirReponses().obtenirReponses(ReponsesParDefaut()))

        quizListe.add(newQuiz1)
        quizListe.add(newQuiz2)
        var user1 = Utilisateur("a@mail.com","bob","mdp")
        var user2 = Utilisateur("b@mail.com","marc","mdp")
        var user3 = utilisateurConnecte


        var permission1 = PermissionScore(user1,newQuiz1,0)
        var permission2 = PermissionScore(user1,newQuiz2,0)
        var permission3 = PermissionScore(user3,newQuiz2,5)
        var permission4 = PermissionScore(user3,newQuiz1,2)
        var permission5 = PermissionScore(user3,newQuiz2,10)

        permissionListe.add(permission1)
        permissionListe.add(permission2)
        permissionListe.add(permission3)
        permissionListe.add(permission4)
        permissionListe.add(permission5)
//        mapPermission+= ("1" to permission1)
//        mapPermission+=("2" to permission2)
//        mapPermission+=("3" to permission3)
    }

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
        return quizListe[indexQuizListe]
    }

    fun setIndexQuiz(quizIndex: Int){
        indexQuizListe = quizIndex
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

        var newQuiz = Quiz( titre, question, choix, reponseTrier)
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
        var newUtilisateur = Utilisateur( email, username, mdp)
        utilisateurListe.add(newUtilisateur)
    }

    /**
     * Méthode qui permet de mettre réponse dans un tableau (Map)
     *
     * @param reponseBrut Réponse brut du créateur du quiz avec les , et :
     * @return
     */
    fun getReponseTrier(reponseBrut: String): List<Map<String, String>> {
        return ObtenirReponses().trierReponses(reponseBrut)
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
     * Méthode qui permet d'avoir le score de l'utilisateur
     *
     * @param quiz Quiz choisi
     * @return Le score de l'utilisateur
     */
    fun getScore(quiz: Quiz): Int {
        return quizScore.score
    }

    /**
     * Méthode qui permet d'avoir la liste des quiz
     *
     * @return Liste des quiz
     */
    fun getListeQuiz(): List<Quiz> {
        return quizListe
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

    fun ajouterPermission(email: String, position: Int) {
        var utilisateurTrouve:Utilisateur
        var quizTrouve:Quiz
        quizTrouve= quizListe.get(position)
        for(utilisateur in utilisateurListe){
            if (utilisateur.courriel==email){
                utilisateurTrouve=utilisateur
                var permission = PermissionScore(utilisateurTrouve,quizTrouve,0)
                permissionListe.add(permission)
            }
        }
    }

    fun getListePermissionParEmail(): MutableList<PermissionScore> {
        var listeFiltré= mutableListOf<PermissionScore>()
        for (permission in permissionListe){
            if(permission.utilisateur.courriel== utilisateurConnecte.courriel){
                listeFiltré.add(permission)
            }

        }
        return listeFiltré
    }


}