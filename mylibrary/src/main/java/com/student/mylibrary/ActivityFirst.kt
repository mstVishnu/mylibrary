package com.student.mylibrary

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.student.mylibrary.api.callbacks.RetrofitCallback
import com.student.mylibrary.constant.ApplicationConstants
import com.student.mylibrary.constant.HomeViewModel
import com.student.mylibrary.databinding.ActivityFirstBinding
import retrofit2.Response

class ActivityFirst : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()

    var binding:ActivityFirstBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()
    }

    fun init() {
        viewModel.getLeads(getLeadsRetrofitCallback)
    }

    private val getLeadsRetrofitCallback = object : RetrofitCallback<HomeResponseModel> {
        override fun onSuccessfulResponse(response: Response<HomeResponseModel>) {

            println("leads list response = ${Gson().toJson(response.body())}")
            response.body()?.let { body ->
                if (body.status == ApplicationConstants.ApiStatus.SUCCESS) {

                    Log.e("dhhd","dl");
                    val contactListAdapter = ContactAdapter(this@ActivityFirst, response.body()!!.result.UserList)
                    val linearLayoutManager = LinearLayoutManager(this@ActivityFirst)
                    binding!!.list.layoutManager = linearLayoutManager
                    binding!!.list.adapter = contactListAdapter
                }
            } ?: run {

            }

        }

        override fun onBadRequest(response: Response<HomeResponseModel>) {



        }

        override fun onServerError(response: Response<*>) {



        }

        override fun onUnAuthorized() {

            AlertDialog.Builder(this@ActivityFirst)
                .setTitle(ApplicationConstants.ApiConstants.UNAUTHORIZED)
                .setMessage("This is a single button alert.")
                .setPositiveButton("OK") { dialog, _ ->
                    // Handle the OK button click
                    dialog.dismiss()
                }
                .create()
                .show()

        }

        override fun onForbidden() {



        }

        override fun onFailure(failure: String) {


        }

        override fun onEverytime() {



        }
    }
}