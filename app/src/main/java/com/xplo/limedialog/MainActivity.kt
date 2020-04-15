package com.xplo.limedialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xlib.limedialog.LimeDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btTest.setOnClickListener {

            LimeDialog.Builder(supportFragmentManager)
                .setLayoutId(R.layout.custom_bongo_dialog)
                .setPosButtonText("yes")
                .setNegButtonText("no")
                .build()
                .show()

        }


    }
}
