package com.moondev.press.fragmentsui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.moondev.press.FlagViewModel
import com.moondev.press.R
import com.moondev.press.adapters.AdapterArticles
import com.moondev.press.adapters.AdapterCategories
import com.moondev.press.animateAndGo
import com.moondev.press.models.Icons
import com.moondev.press.models.ListOfArticle
import kotlinx.android.synthetic.main.fragment_healines.*
import kotlinx.coroutines.cancel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HeadlinesFragment : Fragment(){

    private val TAG = "HeadLinesFragment"
    private var viewModel : FlagViewModel?  = null
    private var recycler_cat : RecyclerView? = null
    private var layout : LinearLayoutManager? = null
    private var adapter : AdapterCategories? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_healines, container, false)

         viewModel = ViewModelProvider(requireActivity()).get(FlagViewModel::class.java)

        viewModel!!.codeLanguage.observe(requireActivity(), Observer {
            setRecyclerViewForCategories(view,it)

        })


        return  view
    }




    override fun onStart() {
        super.onStart()

        viewModel!!.categoryMutable.observe(requireActivity(), Observer {category->
            Log.e("HEADLINES", "CATEGORY : $category")
            getHeadLines( category ?: "general")

        })
    }


    private fun setRecyclerViewForCategories(view: View, countryCode : String){
        val icons = Icons(context!!,countryCode)
         layout =  LinearLayoutManager(context!!)
        layout?.orientation = RecyclerView.HORIZONTAL
        recycler_cat = view.findViewById(R.id.recycler_categories) as RecyclerView
        recycler_cat?.layoutManager = layout
         adapter =  AdapterCategories(context!!,icons)

        recycler_cat?.adapter =  adapter

        val back  = view.findViewById(R.id.back_category) as Button
        val next = view.findViewById(R.id.next_category) as Button


        val smoothScroller: SmoothScroller = object : LinearSmoothScroller(context) {
            override fun getHorizontalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }

        var mCategory = icons.listOfCategoriesForApi[smoothScroller.targetPosition +1]
        viewModel!!.setCategorym(mCategory)

        val max = adapter!!.itemCount


        if(smoothScroller.targetPosition  < max)  next.isEnabled else !next.isEnabled
        if(smoothScroller.targetPosition  > -1 )   back.isEnabled  else !back.isEnabled


        next.setOnClickListener {
            if (layout!!.findLastCompletelyVisibleItemPosition() < (adapter!!.itemCount -1)) {
               smoothScroller.targetPosition  = layout!!.findLastCompletelyVisibleItemPosition() + 1
                mCategory = icons.listOfCategoriesForApi[smoothScroller.targetPosition]
                layout!!.startSmoothScroll(smoothScroller)
            }
            viewModel!!.setCategorym(mCategory)

        }


        back.setOnClickListener {
            if( layout!!.findLastVisibleItemPosition() > 0){
                smoothScroller.targetPosition  = layout!!.findLastCompletelyVisibleItemPosition() - 1
                mCategory = icons.listOfCategoriesForApi[smoothScroller.targetPosition]
                layout!!.startSmoothScroll(smoothScroller)
            }
            viewModel!!.setCategorym(mCategory)

        }








    }



    private fun getHeadLines(category :String){

        val listener =  object :  AdapterArticles.OnClickReadMore{
            override fun openLink(url: String, view: View) {
              animateAndGo(requireActivity(),url,view)
            }


        }


        lifecycleScope.launchWhenCreated {
            try {


                val callback = object :  Callback<ListOfArticle>{
                    override fun onFailure(call: Call<ListOfArticle>, t: Throwable) {
                        Log.e("HEADLINES", t.toString())
                    }

                    override fun onResponse(
                        call: Call<ListOfArticle>,
                        response: Response<ListOfArticle>
                    ) {
                        Log.e("HEADLINES", response.raw().toString())
                        val list =  response.body()!!.list
                        recycler_headlines.layoutManager = LinearLayoutManager(context!!)
                        val adapterArticles = AdapterArticles(list, requireContext(),listener )
                        recycler_headlines.adapter= adapterArticles



                    }

                }
                viewModel?.getHeadLinesArticle(category,callback)

            }catch (e : Exception){
                Log.e(TAG, e.toString())
            }
        }




    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.cancel()
    }

}
