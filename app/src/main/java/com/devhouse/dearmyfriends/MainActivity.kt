package com.devhouse.dearmyfriends

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.devhouse.dearmyfriends.base.BaseActivity
import com.devhouse.dearmyfriends.item.MsgPopInfo
import com.devhouse.dearmyfriends.item.Sentence
import com.devhouse.dearmyfriends.mng.LogManager
import com.devhouse.dearmyfriends.mng.LogType
import com.devhouse.dearmyfriends.mng.OnSwipeTouchListener
import com.devhouse.dearmyfriends.mng.PopType
import com.devhouse.dearmyfriends.mng.SwipeAction
import com.devhouse.dearmyfriends.viewModel.SentenceViewModel
import com.devhouse.dearmyfriends.views.setting.SettingMainListView

class MainActivity : BaseActivity(R.layout.activity_main), OnClickListener, SwipeAction {

    lateinit var sentenceBox: LinearLayout
    lateinit var settingBtn: Button
    lateinit var swipeView: View
    lateinit var contentLabel: TextView
    lateinit var writerLabel: TextView
    lateinit var likeCntLabel: TextView
    lateinit var sentenceVM: SentenceViewModel
    lateinit var likeCntBtn: LinearLayout

    var sentenceArray: ArrayList<Sentence> = ArrayList<Sentence>()
    var currentNum: Int = 0

    private val httpHandle: Handler = Handler()

    override fun initView() {
        super.initView()

        sentenceBox = findViewById(R.id.main_sentence_box)
        settingBtn = findViewById(R.id.main_btn_setting)
        swipeView = findViewById(R.id.swipe_main_view)
        contentLabel = findViewById(R.id.sentence_title_label)
        writerLabel = findViewById(R.id.sentence_msg_label)
        likeCntLabel = findViewById(R.id.main_like_cnt_label)
        likeCntBtn = findViewById(R.id.like_cnt_btn)

        contentLabel.setOnClickListener(this)
        settingBtn.setOnClickListener(this)
        likeCntBtn.setOnClickListener(this)

        swipeView.setOnTouchListener(OnSwipeTouchListener(this))
    }

    override fun initModel() {
        super.initModel()

        // 로티 인풋
        this.lottieLoadingV = findViewById(R.id.lottie_loading_view) // 로티 인잇
        this.loadingBarAction(true)

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
            } else if (view.id == R.id.like_cnt_btn) {
                // 좋아요 통신
                this.loadingBarAction(true)
                sentenceVM.callLikeCntAdd(this.sentenceArray[currentNum].sentenceId)
            } else if (view.id == R.id.sentence_title_label) {
                this.copySentenceAndWriter()
            }
        }
    }

    /* show Sentence */
    fun showSentence(sentences: ArrayList<Sentence>) {
        this.sentenceArray = sentences

        if(this.sentenceArray.size > 0) {
            this.currentNum = 0
            this.showSentenceView()
        }
    }

    fun addAfterAction() {
        val likeCnt = this.sentenceArray[currentNum].likeCnt.toInt() + 1
        this.sentenceArray[currentNum].likeCnt = likeCnt.toString()

        Handler(mainLooper).post {
            showSentenceView()
        }
    }

    fun showSentenceView() {
        httpHandle.post {
            this.loadingBarAction(false)
            contentLabel.text = this.sentenceArray[currentNum].content
            writerLabel.text = "- " + this.sentenceArray[currentNum].writer + " -"
            likeCntLabel.text = this.sentenceArray[currentNum].likeCnt
        }
    }

    fun copySentenceAndWriter() {
        val copyMsg = this.sentenceArray[currentNum].content + "\n\n" + this.sentenceArray[currentNum].writer
        LogManager.instance.consoleLog(LogType.CHECK_ITEMDATA, copyMsg)

        val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.setPrimaryClip(ClipData.newPlainText("", copyMsg))

        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
            val msgPopInfo = MsgPopInfo()
            msgPopInfo.type = PopType.TOAST
            msgPopInfo.content = "복사가 완료되었습니다. \nSNS 나 문자를 통해 명언을 공유해보세요!"
            this.showMsgPop(msgPopInfo)
        }
    }

    override fun onSwipeRight() {
        if(0 < currentNum) {
            currentNum = currentNum - 1
            this.showSentenceView()
        }
    }

    override fun onSwipeLeft() {
        if(currentNum + 1 < sentenceArray.size) {
            currentNum = currentNum + 1
            this.showSentenceView()
        }
    }

    override fun onSwipeTop() {
    }

    override fun onSwipeBottom() {
    }
}
