package com.bigthinkapps.walletwesend.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.bigthinkapps.walletwesend.model.EthereumData
import com.bigthinkapps.walletwesend.util.Constants.BASE_URL
import org.web3j.exceptions.MessageDecodingException
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.response.EthGetBalance
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Convert
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class EthereumBalanceViewModel(context: Application) : AndroidViewModel(context) {

    val ethereumLiveData = MutableLiveData<EthereumData>()

    fun loadEthereumAddress(ethAddress: String) {
        val web3j = Web3jFactory.build(HttpService(BASE_URL))
        web3j.ethGetBalance(ethAddress, DefaultBlockParameterName.LATEST)
                .observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<EthGetBalance> {
                    override fun onCompleted() {}

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                    override fun onNext(ethGetBalance: EthGetBalance) {
                        try {
                            val wei = ethGetBalance.balance
                            val tokenValue = Convert.fromWei(wei.toString(), Convert.Unit.ETHER)
                            val strTokenAmount = tokenValue.toString()
                            val ethData = EthereumData(ethAddress, strTokenAmount, Date())
                            ethereumLiveData.value = ethData
                        } catch (e: MessageDecodingException) {
                            e.printStackTrace()
                            ethereumLiveData.value = null
                        }
                    }
                })
    }
}