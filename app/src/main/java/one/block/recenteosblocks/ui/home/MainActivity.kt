package one.block.recenteosblocks.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import one.block.recenteosblocks.R
import one.block.recenteosblocks.databinding.ActivityMainBinding
import one.block.recenteosblocks.ui.list.ListActivity
import one.block.recenteosblocks.util.toast

class MainActivity : AppCompatActivity(), OnButtonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProvider(this@MainActivity).get(HomeViewModel::class.java)
        binding.homeviewmodel = viewModel
        viewModel.buttonClickListener = this
    }

    override fun onClick() {
        goToListActivity()
        toast("Button pressed...")
    }

    private fun goToListActivity() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}
