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
package com.dawnimpulse.android.auth

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.dawnimpulse.auth.providers.AuthGoogle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by DawnImpulse on 2018 03 06
 * Last Branch Update - master
 * Updates :
 * DawnImpulse - 2018 03 06 - master - Initial
 * DawnImpulse - 2018 05 08 - v0.2.0 - Web Auth (Google)
 */

/**
 * The following activity is an example for the auth-library
 */
class MainActivity : AppCompatActivity() {
    private lateinit var authGoogle: AuthGoogle

    /**
     * On Create
     * @param savedInstanceState
     *
     * We will create an object of AuthGoogle
     * & hook up a button for signin & signout
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authGoogle = AuthGoogle(this);
        sign_in_button.setOnClickListener {
            if (authGoogle.isUserSignedIn()) {
                authGoogle.signOut({ error, resp ->
                    Toast.makeText(this, "$error $resp", Toast.LENGTH_SHORT).show()
                })
            } else {
                authGoogle.signIn("##",{ error, resp ->
                    Toast.makeText(this, "$error $resp", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

    /**
     * onResume
     *
     * Used to check if user is logged in or not
     */
    override fun onResume() {
        super.onResume()

        if (authGoogle.isUserSignedIn())
            sign_in_button.text = "SIGN OUT"
        else
            sign_in_button.text = "SIGN IN"
    }
}
