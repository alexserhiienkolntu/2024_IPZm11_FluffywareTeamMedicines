package ua.edu.lntu.fluffywareteam.medicines.utils

import android.content.Context
import android.widget.Toast

class Alert {
    companion object {
        fun show(context: Context, text: String) {
            Toast.makeText(
                context,
                text,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}