package ua.edu.lntu.fluffywareteam.medicines

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity

inline fun <reified T : ComponentActivity> jumpToActivity(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent);
}