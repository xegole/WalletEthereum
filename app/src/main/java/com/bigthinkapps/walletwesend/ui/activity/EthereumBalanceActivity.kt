package com.bigthinkapps.walletwesend.ui.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.bigthinkapps.walletwesend.R
import com.bigthinkapps.walletwesend.ui.viewmodel.EthereumBalanceViewModel
import com.bigthinkapps.walletwesend.util.DateUtil
import com.bigthinkapps.walletwesend.util.ExtraParamsConstants.EXTRA_ETHEREUM_ADDRESS
import com.bigthinkapps.walletwesend.util.obtainViewModel
import kotlinx.android.synthetic.main.activity_ethereum_balance.*
import java.util.*

class EthereumBalanceActivity : AppCompatActivity() {

    private var ethAddress: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ethereum_balance)
        ethAddress = intent.getStringExtra(EXTRA_ETHEREUM_ADDRESS)
        loadUI()
    }

    private fun loadUI() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        title = getString(R.string.title_ethereum_balance)
        viewModel().ethereumLiveData.observe(this, Observer { ethData ->
            if (ethData == null) {
                showSnackError()
            } else {
                labelEthereumAddress.text = String.format(Locale.getDefault(), getString(R.string.label_ethereum_address), ethData.address)
                labelEthereumBalance.text = String.format(Locale.getDefault(), getString(R.string.label_ethereum_balance), ethData.balance)
                labelEthereumLastUpdate.text = String.format(Locale.getDefault(), getString(R.string.label_ethereum_last_update), DateUtil.getDateStringFromDate(ethData.lastUpdated))
            }
        })
        ethAddress?.let {
            viewModel().loadEthereumAddress(it)
        }
    }

    private fun showSnackError() {
        Snackbar.make(
                labelEthereumAddress,
                getString(R.string.message_error_decoding_ethereum_address),
                Snackbar.LENGTH_LONG)
                .show()
    }

    private fun viewModel(): EthereumBalanceViewModel = obtainViewModel(EthereumBalanceViewModel::class.java)

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
