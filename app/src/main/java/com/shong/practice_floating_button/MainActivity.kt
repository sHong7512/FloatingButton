package com.shong.practice_floating_button

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
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


    lateinit var fabMainSec : FloatingActionButton
    lateinit var fabSubSec1 : FloatingActionButton
    lateinit var fabSubSec2 : FloatingActionButton
    lateinit var fabSubSec3 : FloatingActionButton
    lateinit var fabSubSec4 : FloatingActionButton
    var fab_isOpenedSec = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_Main = findViewById(R.id.fab_main)
        fab_Sub1 = findViewById(R.id.fab_sub1)
        fab_Sub2 = findViewById(R.id.fab_sub2)
        fab_Main.setOnClickListener { onClick(it) }
        fab_Sub1.setOnClickListener { onClick(it) }
        fab_Sub2.setOnClickListener { onClick(it) }


        //sector form button
        val includeView = findViewById<View>(R.id.sectorButton)
        fabMainSec = includeView.findViewById(R.id.fabMain)
        fabSubSec1 = includeView.findViewById(R.id.fabSub1)
        fabSubSec2 = includeView.findViewById(R.id.fabSub2)
        fabSubSec3 = includeView.findViewById(R.id.fabSub3)
        fabSubSec4 = includeView.findViewById(R.id.fabSub4)
        fabMainSec.setOnClickListener{onClick(it)}
        fabSubSec1.setOnClickListener{onClick(it)}
        fabSubSec2.setOnClickListener{onClick(it)}
        fabSubSec3.setOnClickListener{onClick(it)}
        fabSubSec4.setOnClickListener{onClick(it)}
    }


    private fun onClick(v : View){
        when(v.id){
            R.id.fab_main ->{
                Log.d(TAG,"fab_main click!")
                basic_floating()
            }
            R.id.fabMain ->{
                Log.d(TAG,"fabMain click!")
                sectorForm()
            }

            else -> {
                Log.d(TAG, "click otherButton")
            }
        }
    }


    private fun basic_floating(){
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

    //sector form button
    private fun sectorForm(){
        val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, this.resources.displayMetrics)

        if(!fab_isOpened){
            createStartAnimator(fabSubSec1,px,"translationY")
            createStartAnimator(fabSubSec2,px/2,"translationX")
            createStartAnimator(fabSubSec2,px * Math.sqrt(3.0).toFloat()/2,"translationY")
            createStartAnimator(fabSubSec3,px * Math.sqrt(3.0).toFloat()/2,"translationX")
            createStartAnimator(fabSubSec3,px/2,"translationY")
            createStartAnimator(fabSubSec4,px,"translationX")
            fab_isOpened = true
        }else{
            createEndAnimator(fabSubSec1,px,"translationY")
            createEndAnimator(fabSubSec2,px/2,"translationX")
            createEndAnimator(fabSubSec2,px * Math.sqrt(3.0).toFloat()/2,"translationY")
            createEndAnimator(fabSubSec3,px * Math.sqrt(3.0).toFloat()/2,"translationX")
            createEndAnimator(fabSubSec3,px/2,"translationY")
            createEndAnimator(fabSubSec4,px,"translationX")
            fab_isOpened = false
        }
    }

    private val createStartAnimator : (View, Float, String) -> Unit = { v: View, px: Float, property: String ->
        ObjectAnimator.ofFloat(v, property, 0f, -px)
            .apply { target = v; duration = 400; interpolator = OvershootInterpolator() }.start()
    }

    private val createEndAnimator : (View, Float, String) -> Unit = { v: View, px: Float, property: String ->
        ObjectAnimator.ofFloat(v, property, -px, 0f).apply { target = v; duration = 300 }.start()
    }

}