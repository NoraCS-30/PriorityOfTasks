package com.example.priorityoftasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.priorityoftasks.databinding.ActivityTaskPageBinding

class TaskPage : AppCompatActivity() {
    private lateinit var binding: ActivityTaskPageBinding
    private lateinit var db:TaskDataBaseHolder
    var  priority="null"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTaskPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db=TaskDataBaseHolder(this)
        binding.SaveBtn.setOnClickListener{
            val title=binding.editTitle.text.toString()
            val des=binding.editDescription.text.toString()
            val task=Task(0,title,des,priority)
            db.insertTask(task)
            finish()
            Toast.makeText(this,"DONE", Toast.LENGTH_LONG).show()
        }
    }
    fun onClickRadioButton(view: View) {
        if (view is RadioButton) {
            when (view.id) {
                R.id.High ->
                    if (view.isChecked) {
                        priority = "High"
                    }
                R.id.Medium ->
                    if (view.isChecked) {
                        priority = "Medium"
                    }
                R.id.Low ->
                    if (view.isChecked) {
                        priority = "Low"
                    }
            }
        }
    }
}