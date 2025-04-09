package com.example.quotes

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.quotes.models.Quote
import com.google.gson.Gson

object DataManager {

    var data = emptyArray<Quote>()
    var isLoaded = mutableStateOf(false)
    var currentPage = mutableStateOf(Pages.LIST)
    var  currentQuote: Quote? = null


    fun loadAssetsFromFile(context: Context){
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, charset = Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quote>::class.java)
        isLoaded.value = true
    }

    fun switchPages(quote: Quote?){
        currentQuote = quote
        if(currentPage.value == Pages.LIST) {
            currentPage.value = Pages.DETAIL
        } else {
            currentPage.value = Pages.LIST
        }
    }

}