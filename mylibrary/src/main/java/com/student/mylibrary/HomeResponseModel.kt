package com.student.mylibrary

data class HomeResponseModel(
    val status: Boolean,
    val response_code: Int,
    val message: String,
    val result: Result
){
    data class Result(
        val UserList: ArrayList<User>
    ){
        data class User(
            val UserUniqueId: String,
            val UserCompanyName: String,
            val UserFullName: String,
            val UserType: String,
        )
    }
}