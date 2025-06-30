package com.epilabs.epiguard.ui.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun DateTimePicker(
    modifier: Modifier = Modifier,
    onDateTimeSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    var selectedDateTime by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Button(onClick = {
            val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                TimePickerDialog(
                    context,
                    { _, hour, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hour)
                        calendar.set(Calendar.MINUTE, minute)
                        selectedDateTime = "${dayOfMonth}/${month + 1}/$year $hour:$minute"
                        onDateTimeSelected(selectedDateTime)
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                ).show()
            }

            DatePickerDialog(
                context,
                dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }) {
            Text(text = if (selectedDateTime.isEmpty()) "Pick Date & Time" else "Selected: $selectedDateTime")
        }
    }
}
@Preview
@Composable
fun MyScreen() {
    DateTimePicker { dateTime ->
        // handle the selected datetime
        println("Selected DateTime: $dateTime")
    }
}