package com.android.androidlearning.kotlin

import com.android.androidlearning.utils.ALLog

interface IMediaPlayer {
    var path: String;
    fun play()
    fun stop();
    fun pause() {
        ALLog.d("IMediaPlayer", "pause")
    }
}