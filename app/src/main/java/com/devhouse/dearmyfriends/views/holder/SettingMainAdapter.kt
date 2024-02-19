package com.devhouse.dearmyfriends.views.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devhouse.dearmyfriends.R

class SettingMainAdapter(): RecyclerView.Adapter<SettingMainAdapter.SettingMainHolder>() {

    private val menuList = listOf<String>("공지사항", "알림설정", "버전관리")

    inner class SettingMainHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.setting_list_cell_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingMainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_setting_main, parent, false)
        return SettingMainHolder(view)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: SettingMainHolder, position: Int) {
        holder.titleView.text = menuList[position]
    }
}