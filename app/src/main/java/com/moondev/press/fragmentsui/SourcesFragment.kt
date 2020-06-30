package com.moondev.press.fragmentsui


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moondev.press.FlagViewModel
import com.moondev.press.R
import com.moondev.press.adapters.AdapterSource
import com.moondev.press.animateAndGo
import com.moondev.press.models.ListOfSource
import kotlinx.coroutines.cancel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class SourcesFragment : Fragment() {


    private var viewModel : FlagViewModel? =null
    private var recycler :  RecyclerView? =null
    private var adapterSource : AdapterSource? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_sources, container, false)


        viewModel = ViewModelProvider(requireActivity()).get(FlagViewModel::class.java)
        recycler = v.findViewById(R.id.source_recycler)

        return v
    }



    override fun onStart() {
        super.onStart()
        viewModel!!.codeLanguage.observe(requireActivity(), Observer {
            fillSources(it)
        })

    }



    private fun fillSources(language : String){

        val listener =  object :  AdapterSource.OnSourceClick{
            override fun transferUrl(url: String, view: View) {
               animateAndGo(requireActivity(),url,view)
            }


        }



        lifecycleScope.launchWhenCreated {

            val  callback  = object : Callback<ListOfSource>{
                override fun onFailure(call: Call<ListOfSource>, t: Throwable) {
                    Log.e("SOURCES FRAGMENTs",t.toString())
                }

                override fun onResponse(call: Call<ListOfSource>, response: Response<ListOfSource>) {
                    val list =  response.body()?.list
                    Log.e("SOURCES FRAGMENTs",response.raw().toString())

                    recycler!!.layoutManager = LinearLayoutManager(context)
                     adapterSource = AdapterSource(context!!, list!!, listener)
                    recycler!!.adapter = adapterSource

                }

            }


            viewModel!!.getListOfSources(language, callback)


        }

    }


    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.cancel()
    }

}
