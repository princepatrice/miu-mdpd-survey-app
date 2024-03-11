package com.example.survey_app

import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.survey_app.entites.Question
import com.example.survey_app.helper.SurveyApapter
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.survey_app.helper.QuestionHolder


class SurveyActivity : ComponentActivity() {
    private lateinit var  surveyTitle: TextView
    private lateinit var  submitBtn: Button
    private lateinit var  recyclerView: RecyclerView
    private lateinit var questions: List<Question>
    private lateinit var  response_textview: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.layout_survey)
        surveyTitle = findViewById(R.id.type_survey)
        submitBtn =  findViewById(R.id.submit_btn)
        recyclerView = findViewById(R.id.recycler_view)
        response_textview = findViewById(R.id.response_textview)
       initApp()

    }
    private fun initApp(){

        val type = intent.getStringExtra("type")
       // Toast.makeText(this, R.string.food_preferences, Toast.LENGTH_SHORT).show()
       if(type=="1"){
           surveyTitle.setText(R.string.food_preferences)
            questions = listOf(
                Question(
                    resources.getString(R.string.food_q1),
                    resources.getStringArray(R.array.food_q1_options)),
                Question(
                    resources.getString(R.string.food_q2),
                    resources.getStringArray(R.array.food_q2_options)),
                Question(
                    resources.getString(R.string.food_q3),
                    resources.getStringArray(R.array.yes_no_options)),
                Question(
                    resources.getString(R.string.food_q5),
                    resources.getStringArray(R.array.yes_no_options)),
            )
        }else{
           surveyTitle.setText(R.string.diatery_habits)
            questions = listOf(
                Question(
                    resources.getString(R.string.dietary_q1),
                    resources.getStringArray(R.array.yes_no_options)),
                Question(
                    resources.getString(R.string.dietary_q2),
                    resources.getStringArray(R.array.yes_no_options)),
                Question(
                    resources.getString(R.string.dietary_q3),
                    resources.getStringArray(R.array.yes_no_options)),
                Question(
                    resources.getString(R.string.dietary_q5),
                    resources.getStringArray(R.array.yes_no_options)),
            )
        }
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = SurveyApapter(questions)
        recyclerView.adapter = adapter


        submitBtn.setOnClickListener {
            var allQuestionsAnswered = true

            // Loop through each child view of the RecyclerView
            for (i in 0 until recyclerView.childCount) {
                val itemView = recyclerView.getChildAt(i)

                // Get the ViewHolder for the current child view
                val viewHolder = recyclerView.getChildViewHolder(itemView)

                // Check if the ViewHolder is a QuestionHolder and if its radioGroup has no checked button
                if (viewHolder is QuestionHolder && viewHolder.radioGroup.checkedRadioButtonId == -1) {
                    allQuestionsAnswered = false
                    break // Exit loop as soon as one unanswered question is found
                }
            }

            // Validate if all questions are answered
            if (!allQuestionsAnswered) {
                Toast.makeText(this, "You've not answered one or more questions.", Toast.LENGTH_SHORT).show()
            } else {
                var response = ""
                // Loop through each ViewHolder
                for (i in 0 until recyclerView.childCount) {
                    val itemView = recyclerView.getChildAt(i)
                    val viewHolder = recyclerView.getChildViewHolder(itemView) as QuestionHolder
                    val rad = findViewById<RadioButton>(viewHolder.radioGroup.checkedRadioButtonId)
                    response += "${viewHolder.questionTextView.text}: ${rad.text}\n"
                }
                response_textview.setText(response)
            }
        }


    }

}
