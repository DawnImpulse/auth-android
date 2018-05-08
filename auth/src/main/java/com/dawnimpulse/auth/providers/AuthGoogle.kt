/*
ISC License

Copyright 2018, Saksham (DawnImpulse)

Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
provided that the above copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE
OR PERFORMANCE OF THIS SOFTWARE.
*/
package com.dawnimpulse.auth.providers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dawnimpulse.auth.R
import com.dawnimpulse.auth.utils.C
import com.dawnimpulse.auth.utils.Config
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


/**
 * Created by DawnImpulse on 2018 03 06
 * Last Branch Update - v0.2.0
 * Updates :
 * DawnImpulse - 2018 03 06 - google-auth - Initial
 * DawnImpulse - 2018 05 08 - v0.2.0 - google web auth
 */

class AuthGoogle {
    private var context: Context;
    private var googleSignInClient: GoogleSignInClient
    private lateinit var callback: (Any?, Any?) -> Unit

    /**
     * Constructor
     * @param context
     */
    constructor(context: Context) {
        this.context = context;

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        this.googleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    /**
     * Google SignIn
     * @param callback
     */
    fun signIn(callback: (Any?, Any?) -> Unit) {
        Config.callback = callback
        Config.googleSignInClient = googleSignInClient
        (context as AppCompatActivity).startActivity(Intent(context, Inner::class.java))
    }

    /**
     * Google SignIn with Web Auth for backend
     * @param webAuthId
     * @param callback
     */
    fun signIn(webAuthId: String, callback: (Any?, Any?) -> Unit) {
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(webAuthId)
                .requestEmail()
                .build()

        Config.callback = callback
        Config.googleSignInClient = GoogleSignIn.getClient(context, gso)
        (context as AppCompatActivity).startActivity(Intent(context, Inner::class.java))
    }

    /**
     * Google SignOut
     */
    fun signOut(callback: (Any?, Any?) -> Unit) {
        googleSignInClient.signOut()
                .addOnCompleteListener { task ->
                    if (task.isComplete) {
                        //Logging out user successful
                        Log.d(C.MODULE, "Successfully Signed Out")
                        callback(null, true)
                    } else {
                        //Get the exception if task is failed
                        if (task.exception == null) {
                            Log.d(C.MODULE, task.exception.toString())
                            callback(task.exception, null)
                        } else {
                            //if the exception is null send Task Failed to the user
                            Log.d(C.MODULE, task.exception.toString())
                            callback("Task Failed", null)
                        }
                    }
                }
    }

    /**
     * Check if user is signed in or not
     * @return Boolean
     */
    fun isUserSignedIn(): Boolean {
        return GoogleSignIn.getLastSignedInAccount(context) != null
    }

    /**
     * Fetch an account for signed in user
     * @return GoogleSignInAccount or null
     */
    fun getSignedInUser(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context);
    }

    /**
     * Inner class for handling google login
     */
    class Inner : AppCompatActivity() {
        private val CODE_SIGN_IN = 0;

        /**
         * On Create method
         * @param savedInstanceState
         *
         * Used to start signin process
         */
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.transparent_layout)
            startActivityForResult(Config.googleSignInClient.signInIntent, CODE_SIGN_IN)
        }

        /**
         * onActivityResult
         * @param requestCode
         * @param resultCode
         * @param data
         *
         * Getting back our result from Google & pass to handle signIn method
         */
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == CODE_SIGN_IN)
                handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(data))
        }

        /**
         * @param completedTask
         *
         * Handling a signInResult
         */
        private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
            try {
                //fetching user's account
                val account = completedTask.getResult(ApiException::class.java)
                Log.d(C.MODULE, "SIGNED IN - ${account.displayName}");
                //finishing the inner intent and passing values in callback
                Config.callback(null, account);
                finish()

            } catch (e: ApiException) {
                //if there is an exception while signing in the user
                Log.d(C.MODULE, "signInResult:failed code=" + e.statusCode)
                //finishing the inner intent and passing values in callback
                Config.callback("signInResult:failed code=" + e.statusCode, null);
                finish()
            }
        }

    }
}