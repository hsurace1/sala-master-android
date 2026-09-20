package com.salamaster.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.salamaster.tv.databinding.ActivityMainBinding
import com.salamaster.tv.databinding.ItemChannelBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.channelCount.text = getString(
            R.string.channel_count_format,
            Channels.ALL.size
        )

        val inflater = LayoutInflater.from(this)
        for (channel in Channels.ALL) {
            val item = ItemChannelBinding.inflate(inflater, binding.channelContainer, false)
            item.channelName.text = channel.name
            item.channelType.text = channel.type
            item.channelSource.text = channel.source
            item.root.setOnClickListener {
                val intent = Intent(this, PlayerActivity::class.java)
                intent.putExtra(PlayerActivity.EXTRA_NAME, channel.name)
                intent.putExtra(PlayerActivity.EXTRA_URL, channel.url)
                startActivity(intent)
            }
            binding.channelContainer.addView(item.root)
        }
    }
}
