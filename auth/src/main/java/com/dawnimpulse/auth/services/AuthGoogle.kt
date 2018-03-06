package com.dawnimpulse.auth.services

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.dawnimpulse.auth.utils.Config
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


/**
 * Created by DawnImpulse on 2018 03 06
 * Last Branch Update - google-auth
 * Updates :
 * DawnImpulse - 2018 03 06 - google-auth - Initial
 */

class AuthGoogle {
    private val NAME = "AuthGoogle"
    private lateinit var context: Context;
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callback: (Any?, Any?) -> Unit
    private val CODE_SIGN_IN = 0;
    private val CODE_SIGN_OUT = 1;

    /**
     * Constructor
     * @param context
     */
    constructor(context: Context) {
        this.context = context;

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    /**
     * Google Login
     * @param callback
     */
    public fun signIn(callback: (Any?, Any?) -> Unit) {
        Config.callback = callback
        Config.googleSignInClient = googleSignInClient
        (context as AppCompatActivity).startActivity(Intent(context, Inner::class.java))
    }

    public fun signOut(callback: (Any?, Any?) -> Unit) {

    }

    /**
     * Check if user is signed in or not
     * @return Boolean
     */
    public fun isUserSignedIn(): Boolean {
        return GoogleSignIn.getLastSignedInAccount(context) != null
    }

    /**
     * Fetch an account for signed in user
     * @return GoogleSignInAccount or null
     */
    public fun getSignedInUser(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context);
    }


    class Inner : Activity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val signInIntent = Config.googleSignInClient.signInIntent
            startActivityForResult(signInIntent, 1)
        }

        /**
         * Getting back our result form the activity
         */
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode === 1) {
                // The Task returned from this call is always completed, no need to attach
                // a listener.
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            }
        }

        /**
         * Handling a signInResult
         */
        private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
            try {
                val account = completedTask.getResult(ApiException::class.java)
                Toast.makeText(this, "Yipeee", Toast.LENGTH_SHORT);
                Log.d("Test", "SIGNED IN");
            } catch (e: ApiException) {
                Toast.makeText(this, "Nahhhh", Toast.LENGTH_SHORT);
                Log.w("Test", "signInResult:failed code=" + e.statusCode)
            }
        }

    }
}