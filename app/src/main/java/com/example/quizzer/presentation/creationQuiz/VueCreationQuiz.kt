package com.example.quizzer.presentation.creationQuiz

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizzer.R

class VueCreationQuiz : Fragment() {

    companion object {
        fun newInstance() = VueCreationQuiz()
    }

    private lateinit var viewModel: VueCreationQuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vue_creation_quiz, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VueCreationQuizViewModel::class.java)
        // TODO: Use the ViewModel
    }

}