package com.shong.practice_floating_button

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    val TAG = "Main_TAGGG"
    lateinit var fab_Main : FloatingActionButton
    lateinit var fab_Sub1 : FloatingActionButton
    lateinit var fab_Sub2 : FloatingActionButton
    var fab_isOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_Main = findViewById(R.id.fab_main)
        fab_Sub1 = findViewById(R.id.fab_sub1)
        fab_Sub2 = findViewById(R.id.fab_sub2)
        fab_Main.setOnClickListener { onClick(fab_Main) }
        fab_Sub1.setOnClickListener { onClick(fab_Sub1) }
        fab_Sub2.setOnClickListener { onClick(fab_Sub2) }
    }


    fun onClick(v : View){
        when(v.id){
            R.id.fab_main ->{
                Log.d(TAG,"fab_main click!")
                basic_floating()
            }

            R.id.fab_sub1 ->{

            }
            R.id.fab_sub2 ->{

            }
            else -> {
                Log.d(TAG, "click event Id is wrong!")
            }
        }
    }


    fun basic_floating(){
        var px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60f, this.resources.displayMetrics)

        if(!fab_isOpened){
            var firstAnimator = ObjectAnimator.ofFloat(fab_Sub1, "translationY", 0f, -px)
            firstAnimator.target = fab_Sub1
            firstAnimator.duration = 400
            firstAnimator.interpolator = OvershootInterpolator()
            firstAnimator.start()

            var secondAnimator = ObjectAnimator
                .ofFloat(fab_Sub2,"translationY", 0f, -px * 2)
                .apply{
                    target = fab_Sub2
                    duration = 500
                    interpolator = OvershootInterpolator()
            }.start()
            fab_isOpened = true

        }else{
            var writeAnimator = ObjectAnimator.ofFloat(fab_Sub1, "translationY", -px * 2, 0f)
            writeAnimator.target = fab_Sub1
            writeAnimator.interpolator = OvershootInterpolator()
            writeAnimator.start()

            var secondAnimator = ObjectAnimator
                .ofFloat(fab_Sub2,"translationY", -px, 0f)
                .apply{
                    target = fab_Sub2
                    duration = 500
                    interpolator = OvershootInterpolator()
                }.start()
            fab_isOpened = false
        }

    }

}