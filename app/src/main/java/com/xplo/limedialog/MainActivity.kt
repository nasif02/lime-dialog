package com.xplo.limedialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.xlib.limedialog.LimeDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btTest).setOnClickListener {

            LimeDialog.Builder(supportFragmentManager)
                .setLayoutId(R.layout.custom_bongo_dialog)
                .setPosButtonText("yes")
                .setNegButtonText("no")
                .build()
                .show()

        }


    }
}
