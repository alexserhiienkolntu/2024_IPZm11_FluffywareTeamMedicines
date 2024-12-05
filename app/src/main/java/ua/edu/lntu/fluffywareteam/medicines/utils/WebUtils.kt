package ua.edu.lntu.fluffywareteam.medicines.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

class WebUtils {
    companion object {
        fun openWebsite(context: Context, url: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        }
    }
}
