package com.dawnimpulse.auth.utils

import com.google.android.gms.auth.api.signin.GoogleSignInClient

/**
 * Created by Saksham on 2018 03 06
 * Last Branch Update - google-auth
 * Updates :
 * Saksham - 2018 03 06 - google-auth - Initial
 */
object Config {
    lateinit var callback: (Any?, Any?) -> Unit
    lateinit var googleSignInClient: GoogleSignInClient
}