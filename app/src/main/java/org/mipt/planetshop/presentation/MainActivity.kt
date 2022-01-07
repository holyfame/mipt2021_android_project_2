package org.mipt.planetshop.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.MainActivityBinding
import org.mipt.planetshop.presentation.common.BaseActivity
import org.mipt.planetshop.presentation.landingPage.LandingPageFragment
import org.mipt.planetshop.presentation.marsWeather.MarsWeatherFragment
import org.mipt.planetshop.presentation.purchaseFragment.PurchaseFragment

//import kotlinx.android.synthetic.main.main_activity.*


@AndroidEntryPoint
class MainActivity: BaseActivity() {

//    private val viewModel by viewModels<MainActivityViewModel>()
//    private val viewBinding by viewBinding(MainActivityBinding::bind)

    // first string
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val firstFragment =  LandingPageFragment()
        val secondFragment = PurchaseFragment()
        val thirdFragment = MarsWeatherFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView);

        if (savedInstanceState == null) {
            setCurrentFragment(firstFragment)
        }


//        viewBinding.bottomNavigationView.setOnClickListener {
//            when (itemId) {
//                R.id.search -> setCurrentFragment(firstFragment)
//                R.id.trash -> setCurrentFragment(secondFragment)
//            }
//            true
//        }


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


