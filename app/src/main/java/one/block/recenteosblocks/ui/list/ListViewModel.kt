package one.block.recenteosblocks.ui.list

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.block.recenteosblocks.data.db.entities.BlockchainInfo
import one.block.recenteosblocks.data.repositories.BlockRepository
import one.block.recenteosblocks.util.Coroutines

class ListViewModel(
    private val blockRepository: BlockRepository
) : ViewModel() {

    var getBlockchainInfoListener: GetBlockchainInfoListener? = null

    fun getBlockchainInfo() {
        getBlockchainInfoListener?.onGetInfoStart()

        Coroutines.main {
            val blockchainInfo = blockRepository.getBlockchainInfo()
            getBlockchainInfoListener?.onGetInfoSuccess(blockchainInfo)
        }
    }
}