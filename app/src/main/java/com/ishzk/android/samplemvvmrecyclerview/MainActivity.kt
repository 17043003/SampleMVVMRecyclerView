package com.ishzk.android.samplemvvmrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ishzk.android.samplemvvmrecyclerview.databinding.ActivityMainBinding
import com.ishzk.android.samplemvvmrecyclerview.databinding.MenuRowBinding
import com.ishzk.android.samplemvvmrecyclerview.model.User

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val listAdapter by lazy { UserListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get view binding.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set RecyclerView Adapter.
        binding.userRecyclerView.adapter = listAdapter
        binding.userRecyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        listAdapter.submitList(
            listOf(
                User(1, "aaa", 1),
                User(2, "bbb", 2),
            )
        )
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
        binding.editTextTextAge.setText(user.age.toString())
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