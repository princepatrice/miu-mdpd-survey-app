package com.example.survey_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.survey_app.ui.theme.Survey_appTheme

class MainActivity : ComponentActivity() {

    private lateinit var startSurveyButton: Button
    private lateinit var typeSelection:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.check(R.id.radioOption1)
        typeSelection="1"
        startSurveyButton = findViewById<Button>(R.id.start_survey_btn)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.radioOption1){
                typeSelection ="1"
            }else{
                typeSelection="2"
            }
        }

        startSurveyButton.setOnClickListener{
            val intent = Intent(this, SurveyActivity::class.java)
            intent.putExtra("type", typeSelection)
          startActivity(intent)
        }
    }


}

