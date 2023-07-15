package com.slowerror.locationreminders.presentation.ui.reminder_list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.slowerror.locationreminders.domain.model.Reminder
import com.slowerror.locationreminders.presentation.ui.reminder_list.listener.ReminderClickListener

class ReminderAdapter(
    private val itemListener: ReminderClickListener
) : ListAdapter<Reminder, ReminderViewHolder>(ReminderDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        return ReminderViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickListener = itemListener)
    }

}

