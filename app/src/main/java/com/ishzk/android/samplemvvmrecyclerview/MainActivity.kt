package com.ishzk.android.samplemvvmrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ishzk.android.samplemvvmrecyclerview.databinding.MenuRowBinding
import com.ishzk.android.samplemvvmrecyclerview.model.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class UserListAdapter: ListAdapter<User, UserItemViewHolder>(DIFF_UTIL_ITEM_CALLBACK){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val view = MenuRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserItemViewHolder(private val binding: MenuRowBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(user: User){
        binding.editTextName.setText(user.name)
        binding.editTextTextAge.setText(user.age)
    }
}

val DIFF_UTIL_ITEM_CALLBACK = object : DiffUtil.ItemCallback<User>() {
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}