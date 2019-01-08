package com.bigthinkapps.walletwesend.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.bigthinkapps.walletwesend.model.EthereumData

class HomeViewModel(val context: Application) : AndroidViewModel(context) {

    val ethereumDataList = MutableLiveData<List<EthereumData>>()
}