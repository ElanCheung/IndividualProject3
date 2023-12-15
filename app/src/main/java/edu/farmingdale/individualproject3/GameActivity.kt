package edu.farmingdale.individualproject3

import android.animation.ObjectAnimator
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import edu.farmingdale.individualproject3.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    //Keep track of sequence
    private var arrowSequence = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding for drag listeners for the holders
        binding.holder01.setOnDragListener(arrowDragListener)
        binding.holder02.setOnDragListener(arrowDragListener)
        binding.holder03.setOnDragListener(arrowDragListener)
        binding.holder04.setOnDragListener(arrowDragListener)
        binding.holder05.setOnDragListener(arrowDragListener)

        //binding for long-click listeners for arrow buttons
        binding.upMoveBtn.setOnLongClickListener(onLongClickListener)
        binding.downMoveBtn.setOnLongClickListener(onLongClickListener)
        binding.forwardMoveBtn.setOnLongClickListener(onLongClickListener)
        binding.backMoveBtn.setOnLongClickListener(onLongClickListener)

//        //setup for rocket animation
//        val rocketAnimation = binding.rocketAnimationView.background as AnimationDrawable
//        binding.bnRocket.setOnClickListener {
//            if (rocketAnimation.isRunning) {
//                rocketAnimation.stop()
//                binding.rocketAnimationView.visibility = View.INVISIBLE
//            } else {
//                binding.rocketAnimationView.visibility = View.VISIBLE
//                rocketAnimation.start()
//            }
//            //reset the rotation of rocket to original
//            binding.rocketAnimationView.rotation = 0f
//            binding.rocketAnimationView.translationY = 0f
//        }

//        //play button listener for animating the rocket
//        binding.bnPlay.setOnClickListener {
//            //variable to keep track of total duration of animations
//            var totalDuration = 0L
//
//            //iterate through each arrow type in list
//            arrowSequence.forEach { arrowType ->
//                val rocketAnimationView = binding.rocketAnimationView
//                when (arrowType) {
//                    "UP", "DOWN" -> {
//                        val translationY = if (arrowType == "UP") -200f else 200f
//                        //create objectanimator for moving up or down
//                        val animation = ObjectAnimator.ofFloat(rocketAnimationView, "translationY", translationY)
//                        //delay before animation starts using total duration of previous animations
//                        animation.startDelay = totalDuration
//                        //sets animation duration to 1 second
//                        animation.duration = 1000
//                        //starts animation
//                        animation.start()
//                        //add duration of this animation to total duration of animations
//                        totalDuration += animation.duration
//                    }
//                    "FORWARD", "BACK" -> {
//                        val rotation = if (arrowType == "FORWARD") 90f else -90f
//                        //create objectanimator for rotating right or left
//                        val animation = ObjectAnimator.ofFloat(rocketAnimationView, "rotation", rotation)
//                        //delay before animation starts using total duration of previous animations
//                        animation.startDelay = totalDuration
//                        //sets animation duration to 1 second
//                        animation.duration = 1000
//                        //starts animation
//                        animation.start()
//                        //add duration of this animation to total duration of animations
//                        totalDuration += animation.duration
//                    }
//                }
//            }
//            //after all animations, reset rockets rotation and position
//            binding.rocketAnimationView.postDelayed({
//                binding.rocketAnimationView.rotation = 0f
//                binding.rocketAnimationView.translationY = 0f
//            }, totalDuration)
//        }
    }

    private val onLongClickListener = View.OnLongClickListener { view: View ->
        (view as? Button)?.let {

            val item = ClipData.Item(it.tag as? CharSequence)
            val dragData = ClipData( it.tag as? CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)
            val myShadow = ArrowDragShadowBuilder(it)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.startDragAndDrop(dragData, myShadow, null, 0)
            } else {
                it.startDrag(dragData, myShadow, null, 0)
            }
            true
        }
        false
    }

    private val arrowDragListener = View.OnDragListener { view, dragEvent ->
        (view as? ImageView)?.let {
            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    return@OnDragListener true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    //Turn border to yellow upon highlight
                    it.setBackgroundResource(R.drawable.yellowborder)
                    return@OnDragListener true
                }
                DragEvent.ACTION_DRAG_EXITED-> {
                    //Turn border back to black upon exiting holder
                    it.setBackgroundResource(R.drawable.border)
                    return@OnDragListener true
                }
                //No need to handle this for our use case.
                DragEvent.ACTION_DRAG_LOCATION -> {
                    return@OnDragListener true
                }

                DragEvent.ACTION_DROP -> {
                    //Read color data from the clip data and apply it to the card view background.
                    val item: ClipData.Item = dragEvent.clipData.getItemAt(0)
                    val lbl = item.text.toString()
                    Log.d("DragDropEvent", "Dropped arrow type > >  " + lbl)

                    //ensures that list size is always at least 5
                    while (arrowSequence.size < 5) {
                        arrowSequence.add("")
                    }

                    //maintains sequence
                    when (view.id) {
                        R.id.holder01 -> arrowSequence[0] = lbl
                        R.id.holder02 -> arrowSequence[1] = lbl
                        R.id.holder03 -> arrowSequence[2] = lbl
                        R.id.holder04 -> arrowSequence[3] = lbl
                        R.id.holder05 -> arrowSequence[4] = lbl
                    }
                    when(lbl.toString()){
                        "UP"->view.setImageResource( R.drawable.ic_baseline_arrow_upward_24)
                        "DOWN"->view.setImageResource( R.drawable.ic_baseline_arrow_downward_24)
                        "FORWARD"->view.setImageResource(R.drawable.ic_baseline_arrow_forward_24)
                        "BACK"->view.setImageResource( R.drawable.ic_baseline_arrow_back_24)
                    }
                    //Turn border back to black
                    it.setBackgroundResource(R.drawable.border)
                    return@OnDragListener true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    return@OnDragListener true
                }
                else -> return@OnDragListener false
            }
        }
        false
    }

    private class ArrowDragShadowBuilder(view: View) : View.DragShadowBuilder(view) {
        private val shadow = view.background
        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            val width: Int = view.width
            val height: Int = view.height
            shadow?.setBounds(0, 0, width, height)
            size.set(width, height)
            touch.set(width / 2, height / 2)
        }
        override fun onDrawShadow(canvas: Canvas) {
            shadow?.draw(canvas)
        }
    }
}