package com.example.a2231361j104867

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.a2231361j104867.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }

    private fun RetrieveCharacter(characterName: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://rickandmortyapi.com/api/character?name=" + characterName

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    if (!response.isNull("results")) {
                        val results = response.getJSONArray("results")

                        if (results.length() > 0) {
                            val character = results.getJSONObject(0)
                            val characterName = character.getString("name")
                            val characterStatus = character.getString("status")
                            val characterSpecies = character.getString("species")

                            // You can access other character attributes here

                            val characterInfo = "Name: $characterName\nStatus: $characterStatus\nSpecies: $characterSpecies"
                            binding.textView.text = characterInfo
                            // Process character information as needed
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    e.message?.let { Log.d("response error", it) }
                }
            },
            {
                // Handle the case where the request fails
            }
        )

        queue.add(jsonObjectRequest)
    }

}