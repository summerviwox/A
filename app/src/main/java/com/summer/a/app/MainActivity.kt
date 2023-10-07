package com.summer.a.app

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.summer.a.app.databinding.MainActivityBinding
import com.summer.a.provider.RouteAnno
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@RouteAnno("MainActivity")
class MainActivity : FragmentActivity() {

    private val mainActivityBinding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainActivityBinding.root)
        mainActivityBinding.c.setOnClickListener {
            // ProviderManager.getProvider(LoginProvider::class.java)?.login(this,"CustomWebActivity","code")

        }
        //startActivity(Intent(this,LoginActivity::class.java))


        GlobalScope.launch(Dispatchers.Unconfined) {
            println("Thread: ${Thread.currentThread().name}")
            mainActivityBinding.c.setText("1212")
            println("Thread: ${Thread.currentThread().name}")
            repeat(100) {
                var a = it * it
            }
            println("Thread: ${Thread.currentThread().name}")
        }

    }


}