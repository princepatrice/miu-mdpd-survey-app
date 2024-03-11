package com.example.survey_app.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.survey_app.R
import com.example.survey_app.entites.Question

class SurveyApapter(private var questions: List<Question>) :
    RecyclerView.Adapter<QuestionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_layout, parent, false)
        return QuestionHolder(view)
    }

    override fun getItemCount(): Int {
        return questions.size
    }
   /* override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.questionTextView.text = questions[position].question
        holder.radioGroup.removeAllViews() // Clear existing views in the radio group

        val radioGroup = holder.radioGroup // Reference to the radio group
        val options = questions[position].options

        for ((index, option) in options.withIndex()) {
            val radioButton = RadioButton(holder.itemView.context)
            radioButton.id = index // Set a unique ID for each radio button
            radioButton.text = option
            radioButton.textSize = 16f

            radioGroup.addView(radioButton)
        }
    }
   */
   override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.questionTextView.text = questions[position].question;
        val radioButtons = questions[position].options.map { opt ->
            RadioButton(holder.radioGroup.context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                text = opt
                textSize = 16f
            }
        }

        radioButtons.forEach{rb -> holder.radioGroup.addView(rb)}
    }

}