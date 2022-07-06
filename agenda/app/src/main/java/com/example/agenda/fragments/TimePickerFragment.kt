package com.example.agenda.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.time.MonthDay
import java.util.*

class TimePickerFragment(val callback: (result: String) -> Unit) : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c: Calendar = Calendar.getInstance()
        val hour = c[Calendar.HOUR_OF_DAY]
        val minute = c[Calendar.MINUTE]

        return TimePickerDialog(requireContext(), this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(time: TimePicker?, hour: Int, minute: Int) {
        val hourString = hour.toString()
        val minuteString = minute.toString()
        callback("$hourString : $minuteString")
    }
}