package com.example.agenda.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.time.MonthDay
import java.util.*

class DatePickerFragment(val callback: (result: String) -> Unit) : DialogFragment(), DatePickerDialog.OnDateSetListener{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c: Calendar = Calendar.getInstance()
        val year: Int = c.get(Calendar.YEAR)
        val month: Int = c.get(Calendar.MONTH)
        val day: Int = c.get(Calendar.DAY_OF_MONTH)
        val escolha=  DatePickerDialog(requireContext(), this, year, month, day)
        escolha.datePicker.minDate = c.timeInMillis
        return escolha
    }

    override fun onDateSet(picker: DatePicker?, year: Int, month: Int, day: Int) {
        val monthString = (month + 1).toString()
        val dayString = day.toString()
        val yearString = year.toString()
        callback("$dayString / $monthString / $yearString")
    }
}
