package com.devhouse.dearmyfriends.views.holder

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devhouse.dearmyfriends.R
import com.devhouse.dearmyfriends.mng.LogManager
import com.devhouse.dearmyfriends.mng.LogType
import com.devhouse.dearmyfriends.views.setting.SettingMainListView

class SettingMainAdapter(mainV: SettingMainListView): RecyclerView.Adapter<SettingMainAdapter.SettingMainHolder>() {

    private var settingMain: SettingMainListView
    private val menuList = listOf<String>("공지사항", "알림설정", "버전관리")

    init {
        this.settingMain = mainV
    }

    inner class SettingMainHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var titleView: TextView

        fun bind(title: String, listener: OnClickListener) {
            itemView.setOnClickListener(listener)
            titleView = itemView.findViewById(R.id.setting_list_cell_title)
            titleView.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingMainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_setting_main, parent, false)
        return SettingMainHolder(view)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: SettingMainHolder, position: Int) {
        val listener = View.OnClickListener { it ->
            LogManager.instance.consoleLog(LogType.CHECK_ITEMDATA, "Check Count ::: " + position.toString())
            settingMain.moveView(position)
        }

        holder.bind(menuList[position], listener)
    }
}