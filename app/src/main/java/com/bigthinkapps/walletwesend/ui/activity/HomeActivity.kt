package com.bigthinkapps.walletwesend.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bigthinkapps.walletwesend.R
import com.bigthinkapps.walletwesend.model.EthereumData
import com.bigthinkapps.walletwesend.redux.actions.InitializeEthereumList
import com.bigthinkapps.walletwesend.redux.states.EthereumBalanceListState
import com.bigthinkapps.walletwesend.store
import com.bigthinkapps.walletwesend.ui.adapter.EthereumDataAdapter
import kotlinx.android.synthetic.main.activity_home.*
import org.rekotlin.StoreSubscriber

class HomeActivity : AppCompatActivity(), StoreSubscriber<EthereumBalanceListState?> {

    private val adapter = EthereumDataAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadUI()
    }

    private fun loadUI() {
        title = getString(R.string.title_ethereum_history)
        recyclerEthereumData.adapter = adapter
        fabQrCode.setOnClickListener { openQrCode() }
        store.dispatch(InitializeEthereumList())
    }

    private fun openQrCode() {
        val intent = Intent(this, QrCodeActivity::class.java)
        startActivity(intent)
    }

    override fun newState(state: EthereumBalanceListState?) {
        state?.ethereumObjects?.let {
            initializeAdapter(it)
        }
    }

    private fun initializeAdapter(ethereumList: List<EthereumData>) {
        adapter.addData(ethereumList)
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) {
            it.select { appState ->
                appState.ethereumBalanceListState
            }
        }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }
}
