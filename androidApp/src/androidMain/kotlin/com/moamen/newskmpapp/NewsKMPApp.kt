package com.moamen.newskmpapp
import android.app.Application
import com.moamen.newskmpapp.di.databaseModule
import com.moamen.newskmpapp.di.sharedKoinModules
import com.moamen.newskmpapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class NewsKMPApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelsModule + databaseModule

        startKoin {
            androidContext(this@NewsKMPApp)
            modules(modules)
        }
    }
}