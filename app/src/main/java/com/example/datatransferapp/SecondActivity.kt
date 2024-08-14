package com.example.datatransferapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Safely get the data from the Intent, providing a default value if null
        val receivedData = intent.getStringExtra("EXTRA_DATA") ?: "No data received"

        val textViewReceivedData = findViewById<TextView>(R.id.textViewReceivedData)
        textViewReceivedData.text = receivedData

        createNotification(receivedData)
    }

    private fun createNotification(data: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "data_transfer_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Data Transfer Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel for Data Transfer notifications"
                enableLights(true)
                lightColor = getColor(R.color.purple_200)
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notificationIntent = Intent(this, SecondActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Add FLAG_IMMUTABLE here
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Data Received")
            .setContentText(data)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }
}
