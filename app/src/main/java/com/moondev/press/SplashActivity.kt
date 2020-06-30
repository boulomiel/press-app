package com.moondev.press
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {


    var runnable : Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val toastOne = Toast.makeText(this,"Please check your connection", Toast.LENGTH_LONG)
        toastOne.setGravity(0,0 ,300)

            if(!isOnline(this)){
                toastOne.show()
            }

         runnable = Runnable {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        logo.animate().rotationY(360f).scaleY(1.5f).scaleX(1.5f)
            .setDuration(2000).withEndAction(runnable)


    }



}
