package org.mipt.planetshop.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import org.mipt.planetshop.R
import org.mipt.planetshop.presentation.common.BaseActivity
import org.mipt.planetshop.presentation.landingPage.LandingPageFragment
import org.mipt.planetshop.presentation.purchaseFragment.PurchaseFragment

//import kotlinx.android.synthetic.main.main_activity.*


@AndroidEntryPoint
class MainActivity: BaseActivity() {
    // first string
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val firstFragment =  LandingPageFragment()
        val secondFragment = PurchaseFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView);


        if (savedInstanceState == null) {
            setCurrentFragment(firstFragment)
        }



        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.search -> setCurrentFragment(firstFragment)
                R.id.trash -> setCurrentFragment(secondFragment)
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


