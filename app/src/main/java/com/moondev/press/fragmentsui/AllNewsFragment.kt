package com.moondev.press.fragmentsui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.moondev.press.FlagViewModel
import com.moondev.press.R
import com.moondev.press.adapters.AdapterArticles
import com.moondev.press.animateAndGo
import com.moondev.press.models.ListOfArticle
import kotlinx.android.synthetic.main.fragment_all_news.*
import kotlinx.coroutines.cancel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class AllNewsFragment : Fragment(){

    val TAG : String = "AllNewsFragment"
    var viewModel : FlagViewModel? = null
    var adapterArticles : AdapterArticles? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_all_news, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(FlagViewModel::class.java)


        return view
    }


    override fun onStart() {
        super.onStart()

        fillArticles("")
        setupSearchView()

    }




    private fun setupSearchView(){

        val searchEdit = search_bar.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchEdit.hint = "Search"
        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                fillArticles(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        search_bar.setOnCloseListener { false }



    }

    fun fillArticles(query : String){
        val listener =  object :  AdapterArticles.OnClickReadMore{
            override fun openLink(url: String,view: View) {
                animateAndGo(requireActivity(),url,view)
            }


        }

        lifecycleScope.launchWhenCreated {
            try {


                val callback = object :  Callback<ListOfArticle>{
                    override fun onFailure(call: Call<ListOfArticle>, t: Throwable) {
                        Log.e("ALLNEWS", t.toString())
                    }

                    override fun onResponse(
                        call: Call<ListOfArticle>,
                        response: Response<ListOfArticle>
                    ) {
                        Log.e("ALLNEW", response.raw().toString())
                        val list =  response.body()!!.list
                        all_news_recycler.layoutManager = LinearLayoutManager(context)
                        adapterArticles = AdapterArticles(list, requireContext(), listener)
                        all_news_recycler.adapter= adapterArticles


                    }

                }
                viewModel?.getListOfArticles(if(query == "") "all" else query,"en",callback)

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
