package ma.shuler.guessariddle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class Stat_activity : AppCompatActivity() {

    lateinit var riddleCount : TextView
    lateinit var correctCount : TextView
    lateinit var incorrectCount : TextView

    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat)

        val CurrentRiddle = intent.getIntExtra("CurrentRiddle", 0)
        val CorrectAnswerCount = intent.getIntExtra("CorrectAnswerCount", 0)

        btn = findViewById(R.id.button2)
        btn.setOnClickListener {
            onBtnClick()
        }

        riddleCount = findViewById(R.id.textView4)
        correctCount = findViewById(R.id.textView5)
        incorrectCount = findViewById(R.id.textView6)

        riddleCount.setText("Количество решенных загадок: ${CurrentRiddle}")
        correctCount.setText("Правильно было решено загадок: ${CorrectAnswerCount}")
        incorrectCount.setText("Неправильно было решено загадок: ${CurrentRiddle - CorrectAnswerCount}")
    }

    private fun onBtnClick()
    {
        super.finish()
    }

}