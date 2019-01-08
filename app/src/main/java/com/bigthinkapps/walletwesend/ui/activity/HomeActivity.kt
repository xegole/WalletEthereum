package com.bigthinkapps.walletwesend.ui.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bigthinkapps.walletwesend.R
import com.bigthinkapps.walletwesend.ui.adapter.EthereumDataAdapter
import com.bigthinkapps.walletwesend.ui.viewmodel.HomeViewModel
import com.bigthinkapps.walletwesend.util.obtainViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadUI()
    }

    private fun loadUI() {
        title = getString(R.string.title_ethereum_history)
        val adapter = EthereumDataAdapter()
        recyclerEthereumData.adapter = adapter
        viewModel().ethereumDataList.observe(this, Observer {
            adapter.addData(it!!)
        })

        fabQrCode.setOnClickListener { openQrCode() }
    }

    private fun viewModel(): HomeViewModel = obtainViewModel(HomeViewModel::class.java)

    private fun openQrCode() {
        val intent = Intent(this, QrCodeActivity::class.java)
        startActivity(intent)
    }
}
