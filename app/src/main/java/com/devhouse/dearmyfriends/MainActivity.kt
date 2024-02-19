package com.devhouse.dearmyfriends

import android.content.Intent
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.item.Sentence
import com.devhouse.dearmyfriends.viewModel.SentenceViewModel
import com.devhouse.dearmyfriends.views.setting.SettingMainListView

class MainActivity: BaseActivity(R.layout.activity_main), OnClickListener {

    lateinit var settingBtn: Button
    lateinit var swipeView: View
    lateinit var contentLabel: TextView
    lateinit var writerLabel: TextView
    lateinit var sentenceVM: SentenceViewModel

    var sentenceArray: ArrayList<Sentence> = ArrayList<Sentence>()
    var currentNum: Int = 0

    private val httpHandle: Handler = Handler()

    override fun initView() {
        super.initView()

        settingBtn = findViewById(R.id.main_btn_setting)
        swipeView = findViewById(R.id.swipe_main_view)
        contentLabel = findViewById(R.id.sentence_title_label)
        writerLabel = findViewById(R.id.sentence_msg_label)

        settingBtn.setOnClickListener(this)
    }

    override fun initModel() {
        super.initModel()

        sentenceVM = SentenceViewModel(this)
        sentenceVM.getSetences()
    }

    override fun initAction() {
        super.initAction()
    }

    override fun onClick(v: View?) {
        v?.let { view: View ->
            if(view.id == R.id.main_btn_setting) {
                val moveSetting = Intent(this, SettingMainListView::class.java)
                startActivity(moveSetting)
            }
        }
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
