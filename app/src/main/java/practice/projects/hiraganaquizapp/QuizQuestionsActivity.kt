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

    private var progressBar: ProgressBar? = null
    private var tvProgressBar: TextView? = null
    private var tvTitle: TextView? = null
    private var ivQuestion: ImageView? = null
    private var optionOne: TextView? = null
    private var optionTwo: TextView? = null
    private var optionThree: TextView? = null
    private var optionFour: TextView? = null
    private lateinit var options: ArrayList<TextView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        initialize()
        populate(Constants.questions())
        optionOne?.setOnClickListener { selected(it)  }
        optionTwo?.setOnClickListener { selected(it)  }
        optionThree?.setOnClickListener { selected(it)  }
        optionFour?.setOnClickListener { selected(it)  }

    }

    private fun populate(questions: ArrayList<Question>) {
        val currentPosition = 1
        val question: Question = questions[currentPosition - 1]
        val progressBarValue = "${currentPosition} / ${progressBar?.max}"
        tvTitle?.text = question.question
        ivQuestion?.setImageResource(question.image)
        progressBar?.progress = currentPosition
        tvProgressBar?.text = progressBarValue
        optionOne?.text = question.optionOne
        optionTwo?.text = question.optionTwo
        optionThree?.text = question.optionThree
        optionFour?.text = question.optionFour

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

        options = ArrayList(QuizQuestionsActivityConstants.optionsCapacity)
        options.add(optionOne)
        options.add(optionTwo)
        options.add(optionThree)
        options.add(optionFour)
    }

    private fun selected(view: View) {
        for (option in options){
            if (view!=option){
                option?.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                option?.typeface = Typeface.DEFAULT
            }
        }
        val textView = view as? TextView
        textView?.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        textView?.typeface = Typeface.DEFAULT_BOLD
    }

}
