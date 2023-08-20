package ru.skillbranch.gameofthrones

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.skillbranch.gameofthrones.databinding.ActivityMainBinding
import ru.skillbranch.gameofthrones.network.Character
import ru.skillbranch.gameofthrones.network.House
import ru.skillbranch.gameofthrones.network.NetworkService


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val textView = ""

        binding.helloTextView.text = "Hello <3"

        getDataAboutHousesByID(362)

        setContentView(binding.root)
    }

    private fun getDataAboutHousesByID (id: Int) {
        NetworkService.instance
            ?.jSONApi
            ?.getHouseWithID(id)
            ?.enqueue(object : Callback<House?> {
                override fun onResponse(call: Call<House?>, response: Response<House?>) {
                    val house = response.body()
                    Log.i("House INFO", "House name = ${house?.name}, members ${house?.swornMembers}")
                    getCharacterByURL(house?.swornMembers?.get(0))
                }

                override fun onFailure(call: Call<House?>, t: Throwable) {
                    Log.i("House INFO", "Error occurred while getting request!")
                    t.printStackTrace()
                }
            })
    }

    private fun getCharacterByURL (url: String?) {
        if (url == null) return

        val id: Int = url.replace("https://anapioficeandfire.com/api/characters/", "").toInt()
        Log.i("Character INFO", "id = $id")

        NetworkService.instance
            ?.jSONApi
            ?.getCharacterByID(id)
            ?.enqueue(object : Callback<Character?> {
                override fun onResponse(call: Call<Character?>, response: Response<Character?>) {
                    val character = response.body()
                    Log.i("Character INFO", "Character name = ${character?.name}")
                }

                override fun onFailure(call: Call<Character?>, t: Throwable) {
                    Log.i("Character INFO", "Error occurred while getting request!")
                    t.printStackTrace()
                }
            })
    }
}
