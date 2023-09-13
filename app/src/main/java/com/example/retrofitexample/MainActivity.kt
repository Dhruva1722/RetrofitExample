package com.example.retrofitexample

import android.app.Dialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService
    private var progressDialog: ProgressDialog? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService ::class.java)

        findViewById<Button>(R.id.getID).setOnClickListener {
            getUserByID()
        }

        findViewById<Button>(R.id.putID).setOnClickListener {
            updateUser()
        }

        findViewById<Button>(R.id.deleteID).setOnClickListener {
            deleteUser()
        }
        findViewById<Button>(R.id.createUserID).setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        lifecycleScope.launch{
            showLoading("Please wait....")
            val body = JsonObject().apply {
                addProperty("name","vichit coding")
                addProperty("job","android developer")
            }
            val result = apiService.createUser(body)
            if (result.isSuccessful){
                Log.d("++++++++", "create user success : ${result.body()}")
            }else{
                Log.d("++++++++", "create user message : ${result.message()}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun deleteUser() {
        showLoading("Please wait....")
        lifecycleScope.launch {
            val result = apiService.deleteUser("2")
            if (result.isSuccessful) {
                Log.d("++++++++", "delete user success : ${result.body()}")
            } else {
                Log.e("++++++++", "delete field : ${result.message()}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun updateUser() {
        showLoading("Please wait....")
      lifecycleScope.launch {
          val body = JsonObject().apply {
              addProperty("name","vichit coding")
              addProperty("job","android developer")
          }
          val result = apiService.updateUser("2",body)
          if (result.isSuccessful){
              Log.d("-------","updateUser success : ${result.body()}")
          }else{
              Log.d("-------","updateUser field : ${result.message()}")
          }
          progressDialog?.dismiss()
      }
    }

    private fun getUserByID() {
        showLoading("Please wait....")
        lifecycleScope.launch {
            val result = apiService.getUserByID("2")
            if (result.isSuccessful){
                Log.d("oooooo","getUserById success : ${result.body()?.data}")
            }else{
                Log.e("oooooo","getUserById field : ${result.message()}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun showLoading(msg:String){
        progressDialog = ProgressDialog.show(this,null,msg,true)
    }

}