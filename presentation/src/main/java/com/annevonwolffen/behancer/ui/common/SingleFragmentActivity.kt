package com.annevonwolffen.behancer.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.annevonwolffen.behancer.R

abstract class SingleFragmentActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    RefreshOwner {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    protected abstract val fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_swipe_container)
        swipeRefreshLayout = findViewById(R.id.refresh_layout)
        swipeRefreshLayout.setOnRefreshListener(this)

        if (savedInstanceState == null) {
            changeFragment()
        }
    }

    private fun changeFragment() {
        supportFragmentManager.apply {
            if (findFragmentById(R.id.fragment_container) == null) { //TODO: check if this check is needed
                beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
            }
        }
    }

    override fun onRefresh() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment is Refreshable) {
            (fragment as Refreshable).onRefreshData()
        } else {
            setRefreshState(false)
        }
    }

    override fun setRefreshState(isRefreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = isRefreshing
    }
}