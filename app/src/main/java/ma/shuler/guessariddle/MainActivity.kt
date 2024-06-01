package ma.shuler.guessariddle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Random

class MainActivity : AppCompatActivity() {
    var riddles = listOf(
        Pair("Идёт сегодня дождик, бери с собою …!", "зонтик"),
        Pair("За спиной у нас висит, много сможет он вместить!", "рюкзак"),
        Pair("На пол снова убежало с моей кровати …", "одеяло"),
        Pair("Только на небо приплыли, тут же солнце собой скрыли.", "облака"),
        Pair("Будем чистыми с тобой, Смоем пеной всё густой!", "мыло"),
        Pair("В них прошли мы по дороге, Не промокли наши ноги!", "сапоги"),
        Pair("Она сидит на голове, Чтоб тепло было тебе!", "шапка"),
        Pair("Она красивая игрушка, Танюше верная подружка.", "кукла"),
        Pair("Как по ткани он пройдёт, Складочки все соберёт", "утюг"),
        Pair("На небо туча приплыла, Его с собой к нам привела.", "дождь"),
        Pair("Её руками обниму И сразу сладко так усну.", "подушка"),
        Pair("Её сначала разверни, А потом уж в рот клади.", "конфета"),
        Pair("Суп в тарелку мы нальём, Рукой затем её возьмём!", "ложка"),
        Pair("По утрам у тёти Маши, Ем вкуснейшую я …", "кашу"),
        Pair("Он разрежет всё подряд! Булку, фрукты и салат!", "нож"),
    )

    lateinit var name : TextView
    lateinit var riddleText : TextView
    lateinit var correctOrNo : TextView

    lateinit var riddle : Button
    lateinit var answer : Button
    lateinit var statistic : Button
    lateinit var newGame : Button

    lateinit var statText : TextView

    var correctAnswerCount = 0
    var currentRiddle = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.textView)
        riddleText = findViewById(R.id.textView2)
        correctOrNo = findViewById(R.id.textView3)
        statText = findViewById(R.id.textView22)

        riddle = findViewById(R.id.button)
        riddle.setOnClickListener {
            onRiddleClick()
        }

        answer = findViewById(R.id.button1)
        answer.setOnClickListener {
            onAnswerClick()
        }
        answer.isEnabled = false

        statistic = findViewById(R.id.button3)
        statistic.setOnClickListener {
            onStatisticClick()
        }
        statistic.isEnabled = false

        newGame = findViewById(R.id.button11)
        newGame.setOnClickListener {
            onNewGameClick()
        }

        riddles = riddles.shuffled(Random())
    }

    private fun onRiddleClick()
    {
        answer.isEnabled = true
        riddle.isEnabled = false
        correctOrNo.text = ""

        riddleText.text = riddles[currentRiddle].first

    }

    private fun onAnswerClick()
    {
        val builder = AlertDialog.Builder(this)
        val dialogLayout = layoutInflater.inflate(R.layout.answering_layout, null)

        val ans = dialogLayout.findViewById<TextInputLayout>(R.id.editTextAnswer)
        val editText = ans.findViewById<TextInputEditText>(R.id.textInputEditTextAnswer)
        editText.requestFocus()
        editText.setShowSoftInputOnFocus(true)

        builder.setView(dialogLayout)
            .setPositiveButton("Ответить") { dialog, which ->
                val ans = dialogLayout.findViewById<TextInputLayout>(R.id.editTextAnswer)

                if (ans.editText?.text.toString() == riddles[currentRiddle].second)
                {
                    correctOrNo.text = "Правильно!"
                    correctAnswerCount++
                }
                else
                {
                    correctOrNo.text = "Неправильно!"
                }
                answer.isEnabled = false
                riddle.isEnabled = true
                currentRiddle++
                statText.text = "Решено: ${currentRiddle}/10"

                if (currentRiddle == 10)
                {
                    statistic.isEnabled = true
                    answer.isEnabled = false
                    riddle.isEnabled = false
                }
            }

        builder.create().show()
    }

    private fun onStatisticClick()
    {
        val intent = Intent(this, Stat_activity::class.java)
        intent.putExtra("CorrectAnswerCount", correctAnswerCount)
        intent.putExtra("CurrentRiddle", currentRiddle)

        startActivity(intent)
    }

    private fun onNewGameClick()
    {
        riddles = riddles.shuffled(Random())
        currentRiddle = 0
        riddleText.text = ""

        riddle.isEnabled = true
        answer.isEnabled = false
        statistic.isEnabled = false

        correctOrNo.text = ""
        statText.text = "Решено: ${currentRiddle}/10"
    }
}