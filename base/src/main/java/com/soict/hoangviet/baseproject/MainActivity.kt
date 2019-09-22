package com.soict.hoangviet.baseproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.soict.hoangviet.baseproject.custom.AmountTextWatcher
import kotlinx.android.synthetic.main.activity_test.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        edt.setText("109,900")
        edt.addTextChangedListener(AmountTextWatcher(edt))
    }
}