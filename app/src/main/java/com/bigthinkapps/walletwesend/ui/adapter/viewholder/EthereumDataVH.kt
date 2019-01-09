package com.bigthinkapps.walletwesend.ui.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bigthinkapps.walletwesend.R
import com.bigthinkapps.walletwesend.model.EthereumData
import com.bigthinkapps.walletwesend.util.DateUtil
import kotlinx.android.synthetic.main.item_ethereum_data.view.*
import java.util.*

class EthereumDataVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setData(ethereumData: EthereumData) {
        val labelAddress = String.format(Locale.getDefault(), itemView.context.getString(R.string.label_ethereum_address), ethereumData.address)
        val labelBalance = String.format(Locale.getDefault(), itemView.context.getString(R.string.label_ethereum_balance), ethereumData.balance)
        val labelLastUpdate = String.format(Locale.getDefault(), itemView.context.getString(R.string.label_ethereum_last_update), DateUtil.getDateStringFromDate(ethereumData.lastUpdated))

        itemView.labelEthereumAddress.text = labelAddress
        itemView.labelEthereumBalance.text = labelBalance
        itemView.labelEthereumLastUpdate.text = labelLastUpdate
    }
}