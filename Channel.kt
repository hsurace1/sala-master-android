package com.salamaster.tv

/**
 * A single streaming signal. Add new entries to Channels.ALL below to make
 * them appear on the menu — no other code changes needed.
 */
data class Channel(
    val name: String,
    val type: String, // "TV" or "RADIO"
    val url: String,
    val source: String
)

object Channels {
    val ALL = listOf(
        Channel(
            name = "TVUBC",
            type = "TV",
            url = "https://6229fbd10be4d.streamlock.net:59443/monitor/origin_tvubc/playlist.m3u8",
            source = "Wowza · streamlock.net"
        ),
        Channel(
            name = "MixCineTV",
            type = "TV",
            url = "http://cdn1.cineclicknow.com:50009/monitor/origin_mixcinetv/playlist.m3u8",
            source = "Wowza · cineclicknow.com"
        )
    )
}
