package com.dawnimpulse.auth

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Saksham on 2018 01 18
 * Last Branch Update - v
 * Updates :
 * Saksham - 2018 01 18 - v - Initial
 */
object Test {
    fun first(context: Context): Int {
        if (FirebaseAuth.getInstance().currentUser != null) {

        } else {
            context.startActivity(Intent(context, TempActivity::class.java))
            return 6
        }
        return 1
    }
}