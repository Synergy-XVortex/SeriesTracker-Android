package com.example.seriestracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Hilt needs this custom Application class to generate the base dependency container
@HiltAndroidApp
class TrackerApplication : Application()