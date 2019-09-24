package com.soict.hoangviet.baseproject.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


object ActivityUtil {
    fun addFragmentToActivity(mFragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
        mFragmentManager.beginTransaction()
            .add(frameId, fragment)
            .commit()
    }

    fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    fun AppCompatActivity.addFragment(frameId: Int, fragment: Fragment) {
        supportFragmentManager.inTransaction {
            add(frameId, fragment)
        }
    }

    fun AppCompatActivity.addAndToBackStack(frameId: Int, fragment: Fragment) {
        supportFragmentManager.inTransaction {
            add(frameId, fragment)
            addToBackStack(null)
        }
    }

    fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment) {
        supportFragmentManager.inTransaction {
            replace(frameId, fragment)
        }
    }

    fun AppCompatActivity.replaceAndToBackStack(frameId: Int, fragment: Fragment) {
        supportFragmentManager.inTransaction {
            replace(frameId, fragment)
            addToBackStack(null)
        }
    }
}