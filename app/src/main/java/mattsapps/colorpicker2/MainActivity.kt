package mattsapps.colorpicker2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.SurfaceView
import android.view.View
import android.widget.*


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    var red = 0
    var green = 0
    var blue = 0
    var count = 0




    val SHARED_PREFS = "Saved_Colors"
    var TEXT = "Text"
    var savedRed = "savedRed"
    var savedGreen = "savedGreen"
    var savedBlue = "savedBlue"


    var finishedN : TextView? =null
    var redbits :TextView?=null
    var greenbits: TextView?=null
    var bluebits: TextView? =null
    var editText : EditText ?=null

    var firstCN = ""
    var firstRed = 0
    var firstGreen = 0
    var firstBlue = 0



    var secondCN = ""
    var secondRed = 0
    var secondGreen = 0
    var secondBlue = 0

    var thirdCN = ""
    var thirdRed = 0
    var thirdGreen = 0
    var thirdBlue = 0

    var button : Button ?= null


    var surfaceView : SurfaceView?=null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(getDrawable(R.drawable.ic_action_name))

        editText = findViewById<EditText>(R.id.editText) as EditText

        finishedN = findViewById<TextView>(R.id.finishedN) as TextView

        redbits = findViewById<TextView>(R.id.textView) as TextView
        greenbits = findViewById<TextView>(R.id.textView2) as TextView
        bluebits = findViewById<TextView>(R.id.textView3) as TextView
        surfaceView = findViewById<SurfaceView>(R.id.surfaceView) as SurfaceView

        button = findViewById<Button>(R.id.sendMe) as Button

        button!!.setOnClickListener{
            val intent = this.intent
            val info = intent.extras
            info.putInt("First_Red",firstRed)
            setResult(Activity.RESULT_OK,intent)
            finish()

        }



        surfaceView!!.setBackgroundColor(Color.argb(255, red, green, blue))


        val redSeekbar = findViewById<SeekBar>(R.id.seekBar) as SeekBar
        redSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                red = p1
                redSeekbar.progress = red
                surfaceView!!.setBackgroundColor(Color.argb(255, red, green, blue))
                redbits!!.setText(red.toString())

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })


        val greenSeekbar = findViewById<SeekBar>(R.id.seekBar2) as SeekBar
        greenSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                green = p1
                greenSeekbar.progress = green
                surfaceView!!.setBackgroundColor(Color.argb(255, red, green, blue))
                greenbits!!.setText(green.toString())
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })


        val blueSeekbar = findViewById<SeekBar>(R.id.seekBar3) as SeekBar
        blueSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                blue = p1
                blueSeekbar.progress = blue
                surfaceView!!.setBackgroundColor(Color.argb(255, red, green, blue))
                bluebits!!.setText(blue.toString())
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {



        val first_color_button = findViewById<Button>(R.id.first_color) as Button
        val second_color_button = findViewById<Button>(R.id.second_color) as Button
        val third_color_button = findViewById<Button>(R.id.third_color) as Button



        val sp = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = sp.edit()




        return when (item.itemId) {
            R.id.save_button -> {
                first_color_button.visibility = View.VISIBLE
                second_color_button.visibility = View.VISIBLE
                third_color_button.visibility = View.VISIBLE
                editor.putString(TEXT,editText!!.text.toString())
                editor.putInt(savedRed,seekBar.progress)
                editor.putInt(savedGreen,seekBar2.progress)
                editor.putInt(savedBlue,seekBar3.progress)
                count+=1
                editor.apply()


                true
            }
            R.id.load_button -> {




                var newName = sp.getString(TEXT, "YOU MESSED UP")
                var newRed = sp.getInt(savedRed, 255)
                var newGreen = sp.getInt(savedGreen, 255)
                var newBlue = sp.getInt(savedBlue, 255)


                Toast.makeText(applicationContext, newName + " "  + newRed + " " + newGreen + " " + newBlue, Toast.LENGTH_SHORT).show()

                if (count == 1) {
                    firstCN = newName
                    firstRed = newRed
                    firstGreen = newGreen
                    firstBlue = newBlue
                    first_color_button.setTextColor(Color.argb(255, newRed, newGreen, newBlue))
                    first_color_button.setBackgroundColor(first_color_button.currentTextColor)
                    button!!.visibility = View.VISIBLE
                }

                if (count == 2) {
                    secondCN = newName
                    secondRed = newRed
                    secondGreen = newGreen
                    secondBlue = newBlue
                    second_color_button.setTextColor(Color.argb(255, newRed, newGreen, newBlue))
                    second_color_button.setBackgroundColor(second_color_button.currentTextColor)
                }

                if (count == 3) {
                    thirdCN = newName
                    thirdRed = newRed
                    thirdGreen = newGreen
                    thirdBlue = newBlue
                    third_color_button.setTextColor(Color.argb(255, newRed, newGreen, newBlue))
                    third_color_button.setBackgroundColor(third_color_button.currentTextColor)
                }
                first_color_button.setOnClickListener{
                    finishedN!!.text = firstCN
                    redbits!!.text = firstRed.toString()
                    greenbits!!.text = firstGreen.toString()
                    bluebits!!.text = firstBlue.toString()

                    seekBar.progress = firstRed
                    seekBar2.progress = firstGreen
                    seekBar3.progress = firstBlue
                    surfaceView!!.setBackgroundColor(first_color_button.currentTextColor)
                }
                second_color_button.setOnClickListener{
                    finishedN!!.text = secondCN
                    redbits!!.text = secondRed.toString()
                    greenbits!!.text = secondGreen.toString()
                    bluebits!!.text = secondBlue.toString()
                    seekBar.progress = secondRed
                    seekBar2.progress = secondGreen
                    seekBar3.progress = secondBlue
                    surfaceView!!.setBackgroundColor(second_color_button.currentTextColor)
                }
                third_color_button.setOnClickListener{
                    finishedN!!.text = thirdCN
                    redbits!!.text = thirdBlue.toString()
                    greenbits!!.text = thirdGreen.toString()
                    bluebits!!.text = thirdBlue.toString()
                    seekBar.progress = thirdRed
                    seekBar2.progress = thirdGreen
                    seekBar3.progress = thirdBlue
                    surfaceView!!.setBackgroundColor(third_color_button.currentTextColor)
                }

                true
            }

            else -> super.onOptionsItemSelected(item)
        }



    }
}



