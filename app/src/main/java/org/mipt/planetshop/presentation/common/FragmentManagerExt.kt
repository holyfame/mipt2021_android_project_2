package org.mipt.planetshop.presentation.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import org.mipt.planetshop.R.anim
import org.mipt.planetshop.R.id

fun FragmentManager.navigate(fragment: Fragment) {
  commit(allowStateLoss = true) {
    setCustomAnimations(
      anim.slide_from_right,
      anim.slide_to_left,
      anim.slide_from_left,
      anim.slide_to_right
    )
    replace(id.main_activity_container, fragment)
    addToBackStack(null)
  }
}