package com.example.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tip.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    fun calculateTip() {
        val costInTextField = binding.cost.text.toString()
        val cost = costInTextField.toDouble()

        val tipPercentInTextField = binding.tipOptions.text.toString()
        val tipPercentage = tipPercentInTextField.toDouble()

        var tip = tipPercentage / 100 * cost
        var pay = tip + cost

        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            pay = kotlin.math.ceil(pay)
            tip = pay - cost
        }

        val formattedPay = NumberFormat.getCurrencyInstance().format(pay)
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        binding.pay.text = getString(R.string.pay, formattedPay)
    }

}