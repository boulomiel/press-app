package com.moondev.press.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class JournalPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
)  {

    val fragmentList  = mutableListOf<Fragment>()
    val fragmentTitle = mutableListOf<String>()


    fun addFragment(fragment: Fragment, title :  String){
        fragmentList.add(fragment)
        fragmentTitle.add(title)
    }




    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int  = fragmentList.size



    override fun getPageTitle(position: Int) : CharSequence? = fragmentTitle[position]



}