package ru.skillbranch.gameofthrones

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.skillbranch.gameofthrones.databinding.ActivityMainBinding
import ru.skillbranch.gameofthrones.network.NetworkService
import ru.skillbranch.gameofthrones.network.Post


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val textView = ""

        binding.helloTextView.text = "Hello <3"

        NetworkService.instance
            ?.jSONApi
            ?.getPostWithID(1)
            ?.enqueue(object : Callback<Post?> {
                override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                    val post = response.body()
                    Log.i("INFOINFO", "id = ${post?.id} title = ${post?.title}")
                }

                override fun onFailure(call: Call<Post?>, t: Throwable) {
                    Log.i("INFOINFO", "Error occurred while getting request!")
                    t.printStackTrace()
                }
            })

        setContentView(binding.root)
    }
}