package com.valjapan.pelsonaandroid

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    var millisInFuture = 10000
    var time: Int = 10
    var count: Int = 0
    lateinit var timer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton.setOnClickListener {
            setTimer()
            timer.start()
            count = 0
            startButton.visibility = View.INVISIBLE
            countText.text = count.toString()
        }

        tapButton.setOnClickListener {
            if (time in 1..time) {
                count++
                countText.text = count.toString()
                layout.setBackgroundColor(
                    Color.argb(
                        255,
                        Random.nextInt(50..150),
                        Random.nextInt(50..150),
                        Random.nextInt(50..150)
                    )
                )
            } else {
                return@setOnClickListener
            }
        }
    }

    fun setTimer() {

        timer = object : CountDownTimer(millisInFuture.toLong(), 1000) {
            override fun onFinish() {
                startButton.visibility = View.VISIBLE
                tapButton.setBackgroundColor(Color.parseColor("#929292"))
                finishJudge()
            }

            override fun onTick(p0: Long) {
                tapButton.setBackgroundColor(Color.parseColor("#29abe2"))
                time--
                timerText.text = time.toString()
            }
        }

    }

    fun finishJudge() {
        AlertDialog.Builder(this)
            .setTitle("結果発表！")
            .setMessage("あなたは$count 回叩けました！\nさらにハードにしますか？")
            .setPositiveButton("YES") { dialog, which ->
                // Yesが押された時の挙動
                time += 30
                count = 0
                millisInFuture += 30000
            }
            .setNegativeButton("NO") { dialog, which ->
                time = 10
                count = 0
                millisInFuture = 10000
            }
            .setCancelable(false)
            .setIcon(R.mipmap.ic_launcher)
            .show()
        setTimer()
    }
}