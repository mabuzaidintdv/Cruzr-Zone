package com.intdv.cruzrzone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.intdv.cruzrzone.adapters.HumansAdapter
import com.intdv.cruzrzone.databinding.ActivityMainBinding
import com.ubtrobot.Robot
import com.ubtrobot.sensor.SensorListener
import com.ubtrobot.sensor.SensorManager
import timber.log.Timber
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity(), HumansAdapter.IHumanListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var humansAdapter: HumansAdapter

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorListener: SensorListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        humansAdapter = HumansAdapter(this)
        binding.rvHumans.adapter = humansAdapter

        val robotContext = Robot.globalContext()
        sensorManager = robotContext.getSystemService(SensorManager.SERVICE)

        binding.btFind.setOnClickListener {
            //TODO: Implement
        }

        binding.btRecommendApproach.setOnClickListener {
            //TODO: Implement
        }

        binding.btRecommendEngage.setOnClickListener {
            //TODO: Implement
        }

        binding.btApproach.setOnClickListener {
            approachSelectedHuman()
        }

        binding.btEngage.setOnClickListener {
            engageSelectedHuman()
        }
    }

    override fun onHumanClicked(human: Int) {
        Timber.tag(TAG).i("Human Clicked")
        binding.llHumansData.isVisible = true
    }

    private fun approachSelectedHuman() {
        //TODO: Implement
    }

    private fun engageSelectedHuman() {
        //TODO: Implement
    }

    override fun onResume() {
        super.onResume()
        sensorListener = SensorListener { sensorDevice, sensorEvent ->
            try {
                when (sensorEvent.values[0].roundToInt()) {
                    HUMAN_CLOSER -> {
                        Timber.tag(TAG).i("Human Is Approaching")
                    }
                    HUMAN_AWAY -> {
                        Timber.tag(TAG).i("Human Is Moving Away")
                    }
                    else -> {
                        Timber.tag(TAG).i("Human Status Unknown")
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

        sensorManager.registerListener(SENSOR_HUMAN_DETECT, sensorListener)
    }

    override fun onPause() {
        sensorManager.unregisterListener(sensorListener)
        super.onPause()
    }

    companion object {
        private const val TAG: String = "MainActivity"
        private const val SENSOR_HUMAN_DETECT = "human_detect" //Human detection sensor ID
        private const val HUMAN_CLOSER = 1 // There are objects approaching
        private const val HUMAN_AWAY = 2 //There are objects leaving
    }
}