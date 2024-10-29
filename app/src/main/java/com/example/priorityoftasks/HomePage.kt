package com.example.priorityoftasks

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.priorityoftasks.databinding.ActivityMainBinding

class HomePage : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var db:TaskDataBaseHolder
    private lateinit var taskAdpter:TaskAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = TaskDataBaseHolder(this)
        val task=db.getAllTasks()
        taskAdpter = TaskAdapter(task,this)
        binding.AllTasks.adapter = taskAdpter

        binding.AddTask.setOnClickListener{
            val intent=Intent(this,TaskPage::class.java)
            startActivity(intent)
        }

        taskAdpter.setOnMyClickListener(object : TaskAdapter.MyClickListener {
            override fun onClick(v: View?, position: Int) {
                binding.POPwindow.setVisibility(View.VISIBLE) //or gone
                binding.OK.setOnClickListener{
                    binding.POPwindow.setVisibility(View.GONE) //
                }
            }
        })
    }
    override fun onResume() {
        super.onResume()
        taskAdpter.refreshData(db.getAllTasks())
    }

}