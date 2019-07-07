package com.exmaple.todolist


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.exmaple.todolist.db.AppDatabase
import kotlinx.android.synthetic.main.fragment_todo_list.*

class TodoListFragment : Fragment() {
    val db by lazy {
        Room.databaseBuilder(requireContext(),
            AppDatabase::class.java, "todo")
            .allowMainThreadQueries()   // Main Thread에서 DB 작업 허용
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_todoListFragment_to_createFragment)
        }

        // 전체 데이터 가져오기
        val todos = db.todoDao().getAll()
        Log.d("TodoListFragment", todos.toString())
    }

}
