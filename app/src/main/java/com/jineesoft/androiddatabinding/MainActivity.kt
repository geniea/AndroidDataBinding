package com.jineesoft.androiddatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jineesoft.androiddatabinding.data.Person
import com.jineesoft.androiddatabinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val p = Person( "Jang", 40)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.person = p
        binding.buttonName.setOnClickListener {
            changeName()
        }
    }
    private fun changeName( ){
        p.name = "name Changed"
        binding.invalidateAll()
    }
}
