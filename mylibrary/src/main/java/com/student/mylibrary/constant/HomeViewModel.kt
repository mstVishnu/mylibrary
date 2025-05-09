package com.student.mylibrary.constant

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.student.mylibrary.HomeResponseModel
import com.student.mylibrary.api.api_service.APIService
import com.student.mylibrary.api.callbacks.RetrofitCallback
import com.student.mylibrary.api.callbacks.RetrofitMainCallback


class HomeViewModel(application: Application) : AndroidViewModel(application) {


    fun getLeads(retrofitCallBack: RetrofitCallback<HomeResponseModel>) {
            val apiService = APIService.create();
        apiService.let {
            val fields = HashMap<String, String>()
            fields["page"] = "1"
            fields["search"] = ""
            fields["type"] = "Individual"
            fields["empId"] = "38"
            it.getContact(fields)?.enqueue(RetrofitMainCallback(retrofitCallBack))
        }

    }
}
