package com.example.quizzer.presentation.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizzer.R
import com.example.quizzer.presentation.quiz.IContratVuePrésentateurQuiz.*


class VueQuiz : Fragment(), IVueQuiz {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }



    override fun afficherRéponse(réponse: String) {
        TODO("Not yet implemented")
    }

    override fun réinitialiser() {
        TODO("Not yet implemented")
    }
}