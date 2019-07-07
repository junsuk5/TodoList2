package com.exmaple.todolist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.exmaple.todolist.db.AppDatabase
import com.exmaple.todolist.models.Todo
import kotlinx.android.synthetic.main.fragment_create.*


class CreateFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doneFab.setOnClickListener {
            // 삽입
            db.todoDao().insert(Todo(title = todoEditText.text.toString()))

            // 이전 상태로 돌아감
            findNavController().popBackStack()
        }
    }

}
