package one.block.recenteosblocks.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_list.progress_bar
import one.block.recenteosblocks.R
import one.block.recenteosblocks.data.db.entities.BlockchainInfo
import one.block.recenteosblocks.data.network.EosAPI
import one.block.recenteosblocks.data.repositories.BlockRepository
import one.block.recenteosblocks.databinding.ActivityListBinding
import one.block.recenteosblocks.util.hide
import one.block.recenteosblocks.util.show
import one.block.recenteosblocks.util.toast

class ListActivity : AppCompatActivity(), GetBlockchainInfoListener {

    private lateinit var factory: ListViewModelFactory
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        val eosAPI = EosAPI()
        val blockRepository = BlockRepository(eosAPI)
        factory = ListViewModelFactory(blockRepository)
        viewModel = ViewModelProvider(this@ListActivity, factory).get(ListViewModel::class.java)
        binding.listviewmodel = viewModel

        viewModel.getBlockchainInfoListener = this
        viewModel.getBlockchainInfo()
    }

    override fun onGetInfoStart() {
        progress_bar.show()
    }

    override fun onGetInfoSuccess(blockchainInfo: BlockchainInfo) {
        progress_bar.hide()
        toast(blockchainInfo.toString())
    }

    override fun onGetInfoFailure() {
        TODO("Not yet implemented")
    }
}
