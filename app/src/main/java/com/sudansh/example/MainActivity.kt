package com.sudansh.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sudansh.falldetection.FallDetectorLog
import com.sudansh.falldetection.startFallService
import com.sudansh.falldetection.stopFallService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: FallAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FallAdapter()
        recyclerView.adapter = adapter
        btn_start_falllog.setOnClickListener {
            startFallService()
        }

        btn_stop_falllog.setOnClickListener {
            stopFallService()
        }
        btn_show_results.setOnClickListener { fetchLogs() }

        fetchLogs()
    }

    private fun fetchLogs() {
        GlobalScope.launch(Dispatchers.Main) {
            val record = FallDetectorLog.getData(this@MainActivity)
            adapter.updateItems(record)

        }
    }
}
