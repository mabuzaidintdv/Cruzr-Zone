package com.intdv.cruzrzone

import android.app.Application
import com.ubtechinc.cruzr.sys.cruzrleisure.leisure.LeisureManager
import com.ubtrobot.Robot
import timber.log.Timber

class MainApplication : Application() {

    private val TAG = MainApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        initApi()
    }

    private fun initApi() {
        Timber.tag(TAG).e("initApi: started")

        Robot.initialize(this)
        LeisureManager.get().init(this)

        Timber.tag(TAG).e("initApi: ended")
    }
}
