package com.example.survey_app.helper


import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.survey_app.R

class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var questionTextView:TextView
    var radioGroup: RadioGroup

    init {
        questionTextView = itemView.findViewById(R.id.question_textview)
        radioGroup = itemView.findViewById(R.id.radio_group)

        radioGroup.removeAllViews()
    }
}