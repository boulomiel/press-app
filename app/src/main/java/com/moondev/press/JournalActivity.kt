package com.moondev.press

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.moondev.press.adapters.JournalPagerAdapter
import com.moondev.press.fragmentsui.AllNewsFragment
import com.moondev.press.fragmentsui.HeadlinesFragment
import com.moondev.press.fragmentsui.SourcesFragment
import kotlinx.android.synthetic.main.activity_journal2.*
import kotlinx.android.synthetic.main.content_journal.*

class JournalActivity : AppCompatActivity() {

    var viewModel : FlagViewModel? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val codecountry = intent?.getStringExtra("id")
        val codelanguage =  intent?.getStringExtra("language")
        Log.e("journal activity", "langugae : $codelanguage")
        Log.e("journal activity", "country : $codecountry")

        viewModel = ViewModelProvider(this).get(FlagViewModel::class.java)
        viewModel!!.codeLanguage.value =codelanguage
        viewModel!!.countryId.value =codecountry


        val adapter = JournalPagerAdapter(supportFragmentManager)
        adapter.addFragment(HeadlinesFragment(),"Headlines")
        adapter.addFragment(AllNewsFragment(),"International")
        adapter.addFragment(SourcesFragment(),"Sources")
        view_pager.adapter = adapter


        tabs.setupWithViewPager(view_pager)

        tabs.getTabAt(0)!!.icon = resources.getDrawable(R.drawable.headlines)
        tabs.getTabAt(1)!!.icon = resources.getDrawable(R.drawable.international)
        tabs.getTabAt(2)!!.icon = resources.getDrawable(R.drawable.source)





    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            startActivity(Intent(this@JournalActivity,MainActivity::class.java))
            finish()

        }
        return false
    }


    override fun onDestroy() {
        super.onDestroy()
        deleteCache(this)
    }


}
