package com.gauravbora.batterymanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gauravbora.batterymanager.databinding.ActivityUsageBatteryBinding
import com.mjcoder.batterymanager.adapter.BatteryUsageAdapter
import com.gauravbora.batterymanager.model.BatteryModel
import com.gauravbora.batterymanager.utils.BatteryUsage

class UsageBatteryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsageBatteryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsageBatteryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val batteryUsage = BatteryUsage(this)
        val batteryPercentArray: MutableList<BatteryModel> = ArrayList()
        for (item in batteryUsage.getUsageStateList()) {
            if (item.totalTimeInForeground > 0) {
                val bm = BatteryModel()
                bm.packageName = item.packageName
                bm.percentUsage =
                    (item.totalTimeInForeground.toFloat() / batteryUsage.getTotalTime()
                        .toFloat() * 100).toInt()
                batteryPercentArray += bm
            }
        }



        var adapter = BatteryUsageAdapter(this,batteryPercentArray,batteryUsage.getTotalTime())
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

    }
}