package practice.projects.hiraganaquizapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgressBar: TextView
    private lateinit var tvTitle: TextView
    private lateinit var ivQuestion: ImageView
    private lateinit var optionOne: TextView
    private lateinit var optionTwo: TextView
    private lateinit var optionThree: TextView
    private lateinit var optionFour: TextView
    private lateinit var options: ArrayList<TextView?>
    private lateinit var btnSubmit: Button

    private var selectedOption: Int = 0
    private var correctAnswer: Int = 0
    private var optionMap = emptyMap<Int, TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        initialize()
        populate(Constants.questions())
        optionOne.setOnClickListener { selected(it)  }
        optionTwo.setOnClickListener { selected(it)  }
        optionThree.setOnClickListener { selected(it)  }
        optionFour.setOnClickListener { selected(it)  }
        btnSubmit.setOnClickListener { clicked(it) }

    }

    private fun populate(questions: ArrayList<Question>) {
        val currentPosition = 1
        val question: Question = questions[currentPosition - 1]
        val progressBarValue = "$currentPosition / ${progressBar.max}"
        tvTitle.text = question.question
        ivQuestion.setImageResource(question.image)
        progressBar.progress = currentPosition
        tvProgressBar.text = progressBarValue
        optionOne.text = question.optionOne
        optionTwo.text = question.optionTwo
        optionThree.text = question.optionThree
        optionFour.text = question.optionFour
        correctAnswer = question.correctAnswer
    }

    private fun initialize() {
        progressBar = findViewById(R.id.tv_progress_bar)
        tvProgressBar = findViewById(R.id.tv_progress_number)
        tvTitle = findViewById(R.id.tv_title)
        ivQuestion = findViewById(R.id.iv_hiragana_image)
        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
        optionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit_answer)

        options = ArrayList(QuizQuestionsActivityConstants.optionsCapacity)
        options.add(optionOne)
        options.add(optionTwo)
        options.add(optionThree)
        options.add(optionFour)

        optionMap = mapOf(
            1 to optionOne,
            2 to optionTwo,
            3 to optionThree,
            4 to optionFour
        )
    }

    private fun selected(view: View) {
        optionMap.forEach {
            if (view!=it.value){
                it.value.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                it.value.typeface = Typeface.DEFAULT
            }else {
                selectedOption = it.key
            }
        }
        val textView = view as? TextView
        textView?.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        textView?.typeface = Typeface.DEFAULT_BOLD
    }

    private fun clicked(view: View) {
        if(selectedOption != 0) {
            Log.i("answer:", "evaluating answer")
            Log.i("selected answer:", "$selectedOption")
            if (isCorrectAnswer()) {
                Log.i("answer:", "answer is correct")

            }

        }else {
            Log.i("answer:", "please select an answer")
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_LONG).show()
        }
    }


    private fun isCorrectAnswer() : Boolean {
        if (selectedOption == correctAnswer) {
            return true
        }
        return false
    }


}
