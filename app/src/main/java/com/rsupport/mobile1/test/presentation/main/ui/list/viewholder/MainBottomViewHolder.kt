package com.rsupport.mobile1.test.presentation.main.ui.list.viewholder

import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ItemMainBottomBinding
import com.rsupport.mobile1.test.domain.model.MainRecyclerViewItem
import com.rsupport.mobile1.test.presentation.main.ui.list.MainRecyclerViewAdapter

class MainBottomViewHolder(
    private val binding: ItemMainBottomBinding,
    private val mainBottomListener: MainRecyclerViewAdapter.MainBottomListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MainRecyclerViewItem.PageNumber) {
        binding.item = item
        binding.listener = mainBottomListener
        setClickListener()
    }

    private fun setClickListener() {
        binding.etPageNumber.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                mainBottomListener.goToSetPage(binding.etPageNumber.text.toString().toInt())
                true
            } else {
                false
            }
        }
    }
}