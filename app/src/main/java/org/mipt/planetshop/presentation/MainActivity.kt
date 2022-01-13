package org.mipt.planetshop.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import org.mipt.planetshop.R
import org.mipt.planetshop.presentation.common.BaseActivity
import org.mipt.planetshop.presentation.landingPage.LandingPageFragment
import org.mipt.planetshop.presentation.marsWeather.MarsWeatherFragment
import org.mipt.planetshop.presentation.basket.BasketPageFragment

//import kotlinx.android.synthetic.main.main_activity.*


@AndroidEntryPoint
class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val firstFragment =  LandingPageFragment()
        val secondFragment = BasketPageFragment()
        val thirdFragment = MarsWeatherFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView);

        if (savedInstanceState == null) {
            setCurrentFragment(firstFragment)
        }

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.search -> setCurrentFragment(firstFragment)
                R.id.trash -> setCurrentFragment(secondFragment)
                R.id.weather -> setCurrentFragment(thirdFragment)
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_activity_container, fragment)
            commitAllowingStateLoss()
        }
}


