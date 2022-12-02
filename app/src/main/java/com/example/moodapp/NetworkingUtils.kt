package com.example.moodapp

import com.squareup.moshi.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.io.StringReader

var moodId = "1"
fun getMovie( mood: String, callback : (List<Movie>) -> Unit) {
    if (mood == "whimsical"){
        moodId = "1"
    }
    else if (mood == "adventurous"){
        moodId = "2"
    }
    else if (mood == "romantic"){
        moodId = "3"
    }
    else {
        moodId = "4"
    }
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://143.198.115.54:8080/posts/"+ moodId)
        .get()
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()
                val listType = Types.newParameterizedType(List::class.java, Movie::class.java)
                val adapter2: JsonAdapter<List<Movie>> = moshi.adapter(listType)
                val result = adapter2.fromJson(body!!.source())
                if (result != null) {
                    callback(result)
                }
            }
        }
    })
}