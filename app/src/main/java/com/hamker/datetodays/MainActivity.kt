package com.hamker.datetodays

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(
            this,
            "WARNING: Provided days may be a bit incorrect",
            Toast.LENGTH_LONG
        ).show()

        // When "SELECT DATE" button is clicked
        buttonSelectDate.setOnClickListener { view ->
            clickSelectDate(view)
        }

    }

    private fun clickSelectDate(view: View) {
        val c = Calendar.getInstance()
        val curYear = c.get(Calendar.YEAR)
        val curMonth = c.get(Calendar.MONDAY)
        val curDayOfMonth = c.get(Calendar.DAY_OF_MONTH)

        // Open calender dialog
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener {
                    _, year, month, dayOfMonth ->
                    val yearsBeen = curYear - year // Can be zero
                    var monthsBeen = yearsBeen * 12 // Can be zero
                    monthsBeen += (curMonth - month)
                    var daysBeen = monthsBeen * 30 // Can be zero
                    daysBeen += (curDayOfMonth - dayOfMonth) // Contains the final "days"

                    val newDate = "$dayOfMonth/$month/$year"

                    // Update the date in view
                    tvSelectedDate.text = "${tvSelectedDate.text.split(':')[0]}: $newDate"

                    // Update the final "days" text
                    tvDays.text = "$daysBeen days"
            }
            ,curYear
            ,curMonth
            ,curDayOfMonth
        ).show()
    }
}