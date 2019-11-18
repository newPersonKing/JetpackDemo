package com.gy.jetpack_node.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gy.jetpack_node.R
import com.gy.jetpack_node.data.bean.Component
import com.gy.jetpack_node.databinding.ListItemHomeBinding

class HomeListAdapter : ListAdapter<Component,HomeListAdapter.ViewHolder> (ComponentDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ListItemHomeBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = getItem(position)
        holder.apply {
            bind(createOnClickListener(item.link, item.title), item)
            itemView.tag = item
        }
    }

      private fun createOnClickListener(link: String, title: String): View.OnClickListener {
        return View.OnClickListener {
            if (link.isEmpty()) {
                Toast.makeText(it.context, "敬请期待...", Toast.LENGTH_SHORT).show()
            } else {
                var bundle = Bundle()
                bundle.putString("link",link)
                bundle.putString("title",title)
                it.findNavController().navigate(R.id.action_navigationFragment_to_webFragment,bundle)
            }
        }
    }


    class ViewHolder(private val listItemDateBing: ListItemHomeBinding) : RecyclerView.ViewHolder(listItemDateBing.root){

        fun bind(listener: View.OnClickListener, item: Component) {

            listItemDateBing.apply {
                clickListener =listener
                component = item

                /*字面理解意思是说 在设置之前先进行数据的计算处理 等设置的时候可以直接使用 省事*/
                executePendingBindings()
            }

        }

    }

}

private class ComponentDiffCallback : DiffUtil.ItemCallback<Component>() {

    override fun areItemsTheSame(oldItem: Component, newItem: Component): Boolean {
        return oldItem.link == newItem.link
    }

    override fun areContentsTheSame(oldItem: Component, newItem: Component): Boolean {
        return oldItem == newItem
    }
}