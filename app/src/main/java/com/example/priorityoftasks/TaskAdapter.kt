package com.example.priorityoftasks

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter (private var taskList:List<Task> ,context: Context) : RecyclerView.Adapter<TaskAdapter.TaskView>() {

    private val db :TaskDataBaseHolder=TaskDataBaseHolder(context)
    class TaskView(itemView : View) :RecyclerView.ViewHolder(itemView) {

        val title : TextView =itemView.findViewById(R.id.TitleTask)
        val description : TextView =itemView.findViewById(R.id.DescriptionTask)
        val Priority : TextView =itemView.findViewById(R.id.SelectionPriority)
        val delete :ImageView=itemView.findViewById(R.id.done)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskView {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)
        return TaskView(view)
    }

    override fun getItemCount(): Int {
       return taskList.size
    }

    override fun onBindViewHolder(holder: TaskView, position: Int) {

        /*var layout :FrameLayout=holder.itemView.findViewById(R.id.POPwindow)
        var OKbtn:Button=holder.itemView.findViewById(R.id.OK)*/
        var view:CardView =holder.itemView.findViewById(R.id.CardTask)
        val Tasks=taskList[position]
        holder.apply {
            title.text=Tasks.title
            description.text=Tasks.description
            Priority.text=Tasks.priority
            if(Priority.text.equals("High")){
               view.setCardBackgroundColor(Color.parseColor("#C21010"))
            }
            if(Priority.text.equals("Medium")){
                view.setCardBackgroundColor(Color.parseColor("#FFDD5C"))
            }
            if(Priority.text.equals("Low")){
                view.setCardBackgroundColor(Color.parseColor("#76A665"))
            }
            delete.setOnClickListener{
                if(clickListener != null){
                    clickListener!!.onClick(holder.itemView,position)
                    db.deleteTask(Tasks.id)
                    refreshData(db.getAllTasks())
                }
            }


        }
    }
    fun refreshData(newTask :List<Task>){
        taskList=newTask
        notifyDataSetChanged()
    }
    private var clickListener: MyClickListener? = null
    interface MyClickListener {
        fun onClick(v: View?, position: Int)
    }
    fun setOnMyClickListener(clickListener: MyClickListener?) {
        this.clickListener = clickListener
    }
}


