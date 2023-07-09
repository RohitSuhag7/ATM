package com.example.atm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.atm.databinding.ActivityMainBinding
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val denominations = mutableMapOf(
        2000 to 0,
        500 to 0,
        300 to 0,
        200 to 0,
        100 to 0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.depositB.setOnClickListener {
            val twoThousandCurrencyETV = binding.twoThousandCurrencyETV.text.toString()
            val fiveHundredCurrencyNoETV = binding.fiveHundredCurrencyNoETV.text.toString()
            val twoHundredCurrencyNoETV = binding.twoHundredCurrencyNoETV.text.toString()
            val oneHundredCurrencyNoETV = binding.oneHundredCurrencyNoETV.text.toString()
            val threeHundredTwentyFiveNoETV = binding.threeHundredTwentyFiveNoETV.text.toString()

            if (twoThousandCurrencyETV.isNotEmpty()) {
                deposit(twoThousandCurrencyETV, 2000)
            } else {
                deposit("0", 2000)
            }

            if (fiveHundredCurrencyNoETV.isNotEmpty()) {
                deposit(fiveHundredCurrencyNoETV, 500)
            } else {
                deposit("0", 500)
            }

            if (threeHundredTwentyFiveNoETV.isNotEmpty()) {
                deposit(threeHundredTwentyFiveNoETV, 300)
            } else {
                deposit("0", 300)
            }

            if (twoHundredCurrencyNoETV.isNotEmpty()) {
                deposit(twoHundredCurrencyNoETV, 200)
            } else {
                deposit("0", 200)
            }

            if (oneHundredCurrencyNoETV.isNotEmpty()) {
                deposit(oneHundredCurrencyNoETV, 100)
            } else {
                deposit("0", 100)
            }

            totalAmount()
            clearEditTextFields()
        }

        binding.withdrawAmountB.setOnClickListener {
            if (binding.withdrawAmountET.text.toString().isNotEmpty()) {
                val withdrawalAmount = binding.withdrawAmountET.text.toString().toInt()
                val withdrawalDenominations = withdraw(withdrawalAmount)
                if (withdrawalDenominations != null) {
                    for (denomination in withdrawalDenominations.keys) {
                        val count = withdrawalDenominations[denomination]
                        when (denomination) {
                            2000 -> {
                                binding.twoThousandCurrencyNoTV.text =
                                    (binding.twoThousandCurrencyNoTV.text.toString()
                                        .toInt() - count!!).toString()
                                totalAmount()
                            }

                            500 -> {
                                binding.fiveHundredCurrencyNoTV.text =
                                    (binding.fiveHundredCurrencyNoTV.text.toString()
                                        .toInt() - count!!).toString()
                                totalAmount()
                            }

                            300 -> {
                                binding.threeHundredTwentyFiveCurrencyNoTV.text =
                                    (binding.threeHundredTwentyFiveCurrencyNoTV.text.toString()
                                        .toInt() - count!!).toString()
                                totalAmount()
                            }

                            200 -> {
                                binding.twoHundredCurrencyNoTV.text =
                                    (binding.twoHundredCurrencyNoTV.text.toString()
                                        .toInt() - count!!).toString()
                                totalAmount()
                            }

                            100 -> {
                                binding.oneHundredCurrencyNoTV.text =
                                    (binding.oneHundredCurrencyNoTV.text.toString()
                                        .toInt() - count!!).toString()
                                totalAmount()
                            }

                            else -> {
                                Toast.makeText(
                                    this,
                                    "$denomination not Available",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        "$withdrawalAmount. Insufficient funds or denominations not available.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(this, "Please Enter Valid Amount.", Toast.LENGTH_LONG).show()
            }
            binding.withdrawAmountET.text?.clear()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun totalAmount() {
        val twoThousandDenomination = binding.twoThousandDenomination.text.toString().toInt()
        val twoThousandCurrencyNoTV = binding.twoThousandCurrencyNoTV.text.toString().toInt()
        val fiveHundredDenomination = binding.fiveHundredDenomination.text.toString().toInt()
        val fiveHundredCurrencyNoTV = binding.fiveHundredCurrencyNoTV.text.toString().toInt()
        val twoHundredDenomination = binding.twoHundredDenomination.text.toString().toInt()
        val twoHundredCurrencyNoTV = binding.twoHundredCurrencyNoTV.text.toString().toInt()
        val oneHundredDenomination = binding.oneHundredDenomination.text.toString().toInt()
        val oneHundredCurrencyNoTV = binding.oneHundredCurrencyNoTV.text.toString().toInt()
        val threeHundredTwentyFiveDenomination =
            binding.threeHundredTwentyFiveDenomination.text.toString().toInt()
        val threeHundredTwentyFiveNoTV =
            binding.threeHundredTwentyFiveCurrencyNoTV.text.toString().toInt()

        binding.totalAmount.text =
            ((twoThousandDenomination * twoThousandCurrencyNoTV) + (fiveHundredDenomination * fiveHundredCurrencyNoTV) + (twoHundredDenomination * twoHundredCurrencyNoTV) + (oneHundredDenomination * oneHundredCurrencyNoTV) + (threeHundredTwentyFiveDenomination * threeHundredTwentyFiveNoTV)).toString()
    }

    @SuppressLint("SetTextI18n")
    private fun deposit(amount: String, denomination: Int) {
        if (denominations.containsKey(denomination)) {
            denominations[denomination] = denominations[denomination]!! + amount.toInt()

            when (denomination) {
                2000 -> {
                    binding.twoThousandCurrencyNoTV.text =
                        (binding.twoThousandCurrencyNoTV.text.toString()
                            .toInt() + amount.toInt()).toString()
                }

                500 -> {
                    binding.fiveHundredCurrencyNoTV.text =
                        (binding.fiveHundredCurrencyNoTV.text.toString()
                            .toInt() + amount.toInt()).toString()
                }

                200 -> {
                    binding.twoHundredCurrencyNoTV.text =
                        (binding.twoHundredCurrencyNoTV.text.toString()
                            .toInt() + amount.toInt()).toString()
                }

                300 -> {
                    binding.threeHundredTwentyFiveCurrencyNoTV.text =
                        (binding.threeHundredTwentyFiveCurrencyNoTV.text.toString()
                            .toInt() + amount.toInt()).toString()
                }

                100 -> {
                    binding.oneHundredCurrencyNoTV.text =
                        (binding.oneHundredCurrencyNoTV.text.toString()
                            .toInt() + amount.toInt()).toString()
                }
            }
        } else {
            Toast.makeText(this, "Invalid denomination. Deposit failed.", Toast.LENGTH_LONG).show()
        }
    }

    private fun withdraw(amount: Int): Map<Int, Int>? {
        val withdrawalDenominations = mutableMapOf(
            2000 to 0,
            500 to 0,
            300 to 0,
            200 to 0,
            100 to 0
        )
        var remainingAmount = amount

        for (denomination in denominations.keys.sortedDescending()) {
            if (remainingAmount >= denomination && denominations[denomination]!! > 0) {
                val count = min(remainingAmount / denomination, denominations[denomination]!!)
                withdrawalDenominations[denomination] = count
                remainingAmount -= count * denomination
            }
        }

        if (remainingAmount == 0) {
            for (denomination in withdrawalDenominations.keys) {
                val count = withdrawalDenominations[denomination]!!
                if (count > 0) {
                    denominations[denomination] = denominations[denomination]!! - count
                }
            }
            return withdrawalDenominations.filterValues { it > 0 }
        }

        return null
    }

    private fun clearEditTextFields() {
        binding.twoThousandCurrencyETV.text?.clear()
        binding.fiveHundredCurrencyNoETV.text?.clear()
        binding.twoHundredCurrencyNoETV.text?.clear()
        binding.oneHundredCurrencyNoETV.text?.clear()
        binding.threeHundredTwentyFiveNoETV.text?.clear()
    }
}
