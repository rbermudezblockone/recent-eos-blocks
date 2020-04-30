package one.block.recenteosblocks.ui.home

import android.view.View
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    var buttonClickListener: OnButtonClickListener? = null

    fun goToListClick(view: View) {
        buttonClickListener?.onClick()
    }
}