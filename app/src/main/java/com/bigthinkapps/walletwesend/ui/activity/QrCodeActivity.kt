package com.bigthinkapps.walletwesend.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.bigthinkapps.walletwesend.R
import com.bigthinkapps.walletwesend.util.Constants.FIRST_ITEM
import com.bigthinkapps.walletwesend.util.Constants.MY_PERMISSIONS_REQUEST_CAMERA
import com.bigthinkapps.walletwesend.util.ExtraParamsConstants.EXTRA_ETHEREUM_ADDRESS
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class QrCodeActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val scannerView by lazy { ZXingScannerView(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadUI()
    }

    private fun loadUI() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        title = getString(R.string.title_ethereum_qr_code)
        setContentView(scannerView)
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        requestPermission()
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

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            scannerView.startCamera()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                //Show explain info
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), MY_PERMISSIONS_REQUEST_CAMERA)
            } else {

            }

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), MY_PERMISSIONS_REQUEST_CAMERA)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[FIRST_ITEM] == PackageManager.PERMISSION_GRANTED) {
                    scannerView.startCamera()
                }
                return
            }
        }
    }
}
