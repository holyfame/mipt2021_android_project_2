package org.mipt.planetshop.presentation

import android.os.Bundle
import org.mipt.planetshop.R
import org.mipt.planetshop.presentation.common.BaseActivity
import org.mipt.planetshop.presentation.landingPage.LandingPageFragment

class MainActivity: BaseActivity() {
// first string
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_actvity)

        if (savedInstanceState == null) {
            val fragment = LandingPageFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, fragment)
                .commitAllowingStateLoss()
        }
    }
}