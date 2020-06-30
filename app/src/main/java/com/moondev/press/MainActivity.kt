package com.moondev.press

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.moondev.press.adapters.AdapterMain
import com.moondev.press.adapters.AdapterSmallFlags
import com.moondev.press.models.Flag
import com.moondev.press.models.FlagList
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AdapterMain.FlagListener, AdapterSmallFlags.OnClickMove {

    private var adapterMain : AdapterMain? = null
    private var adapterSmallFlags : AdapterSmallFlags? = null
    private var flagList =  arrayListOf<Flag>()
    private var viewModel :   FlagViewModel? = null
    private var flag  : FlagList? =null
    private var lm  : LinearLayoutManager? = null
    private var animator :  ObjectAnimator? = null
    private var searchEdit : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(FlagViewModel::class.java)

        flag =  FlagList(this)

        flagList =  flag!!.list


    }




    override fun onStart() {
        super.onStart()

        val snack = Snackbar.make(window.decorView,"Use the search bar to find a country",Snackbar.LENGTH_LONG)
        snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
        snack.setBackgroundTint(getColor(R.color.YellowGreen))
        snack.setTextColor(getColor(R.color.AntiqueWhite))
        snack.show()

        adapterMain =
            AdapterMain(this, flagList, this)
         lm = LinearLayoutManager(this)
        lm?.orientation = RecyclerView.HORIZONTAL
        recycler_flag.layoutManager = lm
        recycler_flag.adapter = adapterMain


        adapterSmallFlags = AdapterSmallFlags(this, flagList, this)
        search_flag_list.layoutManager = LinearLayoutManager(this)
        search_flag_list.adapter = adapterSmallFlags

        setupSearchBar()


        viewModel!!.indexWhileTyping.observe(this, Observer {
            Log.e("MAIN", "index observed on typing : $it")
            if(it > 0) displayCountry(it)
        })


        viewModel!!.indexOnClick.observe(this, Observer {
            Log.e("MAIN", "index observed on click :  $it")
            displayCountry(it)

        })

    }

    fun displayCountry(position: Int){
        val smoothScroller: RecyclerView.SmoothScroller = object : LinearSmoothScroller(this) {
            override fun getHorizontalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }


        if (lm!!.findLastCompletelyVisibleItemPosition() < (adapterMain!!.itemCount -1)) {
            smoothScroller.targetPosition  = position
            lm?.startSmoothScroll(smoothScroller)
        }

    }


    private fun setupSearchBar(){
         searchEdit = search_bar_flags.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchEdit!!.hint = "Search"
        searchEdit!!.inputType =  InputType.TYPE_TEXT_FLAG_CAP_SENTENCES

        search_bar_flags.setOnQueryTextListener(object : SearchView.OnQueryTextListener {



            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel!!.indexWhileTyping.value = flag!!.getIndexOfCountry(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel!!.indexWhileTyping.value = flag!!.getIndexWhileTyping(newText.toString())
                return false
            }

        })

        search_bar_flags.setOnCloseListener { false }
    }

    fun Activity.hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }


    override fun onFlagClick(id: String, view : View) {
        if(!search_bar_flags.isIconified)  search_bar_flags.isIconified = true
        hideSoftKeyboard()
        viewModel!!.setCountryId(id)


        val runnable  = Runnable {
            val intent  = Intent(this@MainActivity, JournalActivity::class.java)
            val codeLanguage = flag!!.fromCountryCodeToLanguage(id)
            intent.putExtra("id",id)
            intent.putExtra("language",codeLanguage)
            viewModel!!.codeLanguage.value = codeLanguage
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        view.animate().scaleX(2f).scaleY(2f).setDuration(250).withEndAction(runnable)

    }

    override fun onClickMoveTo(pos: Int) {
            viewModel!!.indexOnClick.value = pos
    }
}
