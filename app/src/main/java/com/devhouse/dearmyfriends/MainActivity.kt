package com.devhouse.dearmyfriends

import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.item.Sentence
import com.devhouse.dearmyfriends.viewModel.SentenceViewModel

class MainActivity: BaseActivity(R.layout.activity_main) {

    lateinit var swipeView: View
    lateinit var contentLabel: TextView
    lateinit var writerLabel: TextView
    lateinit var sentenceVM: SentenceViewModel

    var sentenceArray: ArrayList<Sentence> = ArrayList<Sentence>()
    var currentNum: Int = 0

    private val httpHandle: Handler = Handler()

    override fun initModel() {
        super.initModel()

        swipeView = findViewById(R.id.swipe_main_view)
        contentLabel = findViewById(R.id.sentence_title_label)
        writerLabel = findViewById(R.id.sentence_msg_label)

        sentenceVM = SentenceViewModel(this)
        sentenceVM.getSetences()
    }

    override fun initAction() {
        super.initAction()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            MotionEvent.ACTION_SCROLL -> {

                return true
            }
        }

        return false
    }

    /* show Sentence */
    fun showSentence(sentences: ArrayList<Sentence>) {
        this.sentenceArray = sentences

        if(this.sentenceArray.size > 0) {
            this.currentNum = 0
            this.showSentenceView()
        }
    }

    fun showSentenceView() {
        httpHandle.post {
            contentLabel.text = this.sentenceArray[currentNum].content
            writerLabel.text = this.sentenceArray[currentNum].writer
        }
    }
}
