package com.bigthinkapps.walletwesend.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bigthinkapps.walletwesend.R
import com.bigthinkapps.walletwesend.model.EthereumData
import com.bigthinkapps.walletwesend.ui.adapter.viewholder.EthereumDataVH

class EthereumDataAdapter : RecyclerView.Adapter<EthereumDataVH>() {

    private val ethereumDataList = ArrayList<EthereumData>()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): EthereumDataVH {
        val viewContainer = LayoutInflater.from(parent.context).inflate(R.layout.item_ethereum_data, parent, false)
        return EthereumDataVH(viewContainer)
    }

    override fun getItemCount(): Int = ethereumDataList.size

    override fun onBindViewHolder(holder: EthereumDataVH, position: Int) {
        holder.setData(ethereumDataList[position])
    }

    fun addData(ethereumDataList: List<EthereumData>) {
        this.ethereumDataList.clear()
        this.ethereumDataList.addAll(ethereumDataList)
        notifyDataSetChanged()
    }
}