package com.devhouse.dearmyfriends.views.holder

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devhouse.dearmyfriends.R
import com.devhouse.dearmyfriends.item.AppNotice
import com.devhouse.dearmyfriends.mng.LogManager
import com.devhouse.dearmyfriends.mng.LogType
import com.devhouse.dearmyfriends.views.setting.AppNoticeListView

class AppNoticeAdapter(listParent: AppNoticeListView, noticeList: ArrayList<AppNotice>): RecyclerView.Adapter<AppNoticeAdapter.AppNoticeHolder>() {

    private var listParentV: AppNoticeListView
    private var notiList: ArrayList<AppNotice>

    init {
        this.listParentV = listParent
        this.notiList = noticeList
    }
    inner class AppNoticeHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var titleLabel: TextView
        lateinit var dateLabel: TextView

        fun bind(listener: OnClickListener, info: AppNotice) {
            itemView.setOnClickListener(listener)
            titleLabel.text = info.title
            dateLabel.text = info.regDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppNoticeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_app_notice_list, parent, false)
        return AppNoticeHolder(view)
    }

    override fun getItemCount(): Int {
        return notiList.size
    }

    override fun onBindViewHolder(holder: AppNoticeHolder, position: Int) {
        val listener = View.OnClickListener { it ->
            LogManager.instance.consoleLog(LogType.CHECK_ITEMDATA, "Check Count ::: " + position.toString())
            listParentV.moveView(notiList[position])
        }

        holder.bind(listener, notiList[position])
    }
}