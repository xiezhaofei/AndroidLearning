package com.android.androidlearning.kotlin

import com.android.androidlearning.utils.ALLog

class MediaPlayerImpl : IMediaPlayer {
    override var path: String = ""
    override fun play() {
        ALLog.d("MediaPlayerImpl", "play")
    }

    override fun stop() {
        ALLog.d("MediaPlayerImpl", "stop")
    }

    override fun pause() {
        super.pause()
    }
}