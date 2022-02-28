package practice.projects.hiraganaquizapp

object Constants {
    fun getQuestions(): ArrayList<Question> {
        val questions = ArrayList<Question>()
        val question1 = Question(
            1,
            "What character is this?",
            R.drawable.im_hiragana_a,
            "ka",
            "ra",
            "o",
            "a",
            4
        )

        val question2 = Question(
            1,
            "What character is this?",
            R.drawable.im_hiragana_e,
            "e",
            "i",
            "o",
            "a",
            1
        )

        questions.add(question1)
        questions.add(question2)
        return questions
    }

}