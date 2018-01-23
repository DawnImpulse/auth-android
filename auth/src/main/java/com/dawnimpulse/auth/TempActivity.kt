package com.dawnimpulse.auth

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class TempActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)

        var dialogBuilder = AlertDialog.Builder(this);

        dialogBuilder.setTitle("Please Wait ... ");
        dialogBuilder.setMessage("Hello")
        dialogBuilder.setPositiveButton("OK") { _, _ ->
            Toast.makeText(this@TempActivity, "Hey dude", Toast.LENGTH_SHORT).show()
        }
        var inflater = layoutInflater;

        var dialog = dialogBuilder.create();
        dialog.show();
    }
}
