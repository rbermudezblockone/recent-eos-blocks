package one.block.recenteosblocks.ui.list

import one.block.recenteosblocks.data.db.entities.BlockchainInfo

interface GetBlockchainInfoListener {
    fun onGetInfoStart()
    fun onGetInfoSuccess(blockchainInfo: BlockchainInfo)
    fun onGetInfoFailure()
}