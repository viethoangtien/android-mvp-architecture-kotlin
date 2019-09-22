package com.soict.hoangviet.baseproject.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class BaseFragmentPagerAdapter(
    mFragmentManager: FragmentManager,
    val mListFragment: ArrayList<Fragment>,
    val mListTitle: ArrayList<String>
) :
    FragmentPagerAdapter(mFragmentManager) {
    override fun getItem(position: Int): Fragment {
        return mListFragment[position]
    }

    override fun getCount(): Int {
        return mListFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mListTitle[position]
    }
}