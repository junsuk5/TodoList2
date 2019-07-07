package com.exmaple.todolist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.exmaple.todolist.db.AppDatabase
import kotlinx.android.synthetic.main.fragment_update.*


class UpdateFragment : Fragment() {
    val args: UpdateFragmentArgs by navArgs()

    val db by lazy {
        Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "todo"
        )
            .allowMainThreadQueries()   // Main Thread에서 DB 작업 허용
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db.todoDao().getTodo(args.id)?.let { todo ->
            todoEditText.setText(todo.title)

            // 수정
            doneFab.setOnClickListener {
                todo.title = todoEditText.text.toString()
                db.todoDao().update(todo)

                findNavController().popBackStack()
            }

            // 버리기
            delete_button.setOnClickListener {
                db.todoDao().delete(todo)

                findNavController().popBackStack()
            }
        }

    }


}
