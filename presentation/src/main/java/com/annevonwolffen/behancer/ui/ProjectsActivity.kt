package com.annevonwolffen.behancer.ui

import androidx.fragment.app.Fragment
import com.annevonwolffen.behancer.ui.common.SingleFragmentActivity

class ProjectsActivity : SingleFragmentActivity() {
    override val fragment: Fragment
        get() = ProjectsFragment.newInstance()
}