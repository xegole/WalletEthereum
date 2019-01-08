package com.bigthinkapps.walletwesend.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bigthinkapps.walletwesend.R
import com.bigthinkapps.walletwesend.util.ExtraParamsConstants.EXTRA_ETHEREUM_ADDRESS
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QrCodeActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    lateinit var scannerView: ZXingScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadUI()
    }

    private fun loadUI() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        title = getString(R.string.title_ethereum_qr_code)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun handleResult(data: Result?) {
        data?.let {
            openEthereumBalance(it.text)
        }
    }

    private fun openEthereumBalance(ethAddress: String) {
        val intent = Intent(this, EthereumBalanceActivity::class.java)
        intent.putExtra(EXTRA_ETHEREUM_ADDRESS, ethAddress)
        startActivity(intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
