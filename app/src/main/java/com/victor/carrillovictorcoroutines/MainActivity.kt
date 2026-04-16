package com.victor.carrillovictorcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val btnDownload = findViewById<Button>(R.id.btnDownload)

        btnDownload.setOnClickListener {
            // Start the download process using lifecycleScope
            lifecycleScope.launch {
                // Update UI to show downloading state
                tvStatus.text = "Descarregant..."
                btnDownload.isEnabled = false

                // Perform fake download in IO dispatcher
                withContext(Dispatchers.IO) {
                    simulateDownload()
                }

                // Back on Main thread, update UI
                tvStatus.text = "Descarregant correctament!"
                btnDownload.isEnabled = true
            }
        }
    }

    private suspend fun simulateDownload() {
        // Simulates a 3 second download
        delay(3000)
    }
}
