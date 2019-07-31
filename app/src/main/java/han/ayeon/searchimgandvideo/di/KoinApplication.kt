package han.ayeon.searchimgandvideo.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by HanAYeon on 2019-07-30.
 */

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)
            modules(mutableListOf(
                    repositoryModule,
                    viewModelModule,
                    usecaseModule)
            )
        }
    }
}