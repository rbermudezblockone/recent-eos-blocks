package one.block.recenteosblocks.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import one.block.recenteosblocks.R
import one.block.recenteosblocks.data.db.entities.Block
import one.block.recenteosblocks.data.db.entities.BlockchainInfo
import one.block.recenteosblocks.data.network.EosAPI
import one.block.recenteosblocks.data.repositories.BlockRepository
import one.block.recenteosblocks.databinding.ActivityListBinding
import one.block.recenteosblocks.ui.adapter.BlockAdapter
import one.block.recenteosblocks.util.hide
import one.block.recenteosblocks.util.show
import one.block.recenteosblocks.util.toast

class ListActivity : AppCompatActivity() {

    private lateinit var factory: ListViewModelFactory
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: BlockAdapter

    private var list: ArrayList<Block> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        getBlockchainInfo()
    }

    fun initViewModel() {
        val binding : ActivityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        val eosAPI = EosAPI()
        val blockRepository = BlockRepository(eosAPI)
        factory = ListViewModelFactory(blockRepository)
        viewModel = ViewModelProvider(this@ListActivity, factory).get(ListViewModel::class.java)
        binding.listviewmodel = viewModel
        binding.lifecycleOwner = this
        adapter = BlockAdapter(list)
        recycler_view.also {
            it.layoutManager = LinearLayoutManager(this)
            it.setHasFixedSize(true)
            it.adapter = adapter
        }
        initObservers()
    }

    fun getBlockchainInfo() {
        progress_bar.show()
        viewModel.getBlockchainInfo()
    }

    fun showInfo(blockchainInfo: BlockchainInfo) {
        progress_bar.hide()
        toast(blockchainInfo.toString())
    }

    fun initObservers() {
        viewModel.blockchainInfo.observe(this, Observer {

        })

        viewModel.block.observe(this, Observer {
            list.add(it)
            recycler_view.adapter?.notifyDataSetChanged()
        })
    }
}
