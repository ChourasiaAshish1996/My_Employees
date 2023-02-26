package com.example.myemployees.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.myemployees.Data.Employee
import com.example.myemployees.Data.Role
import com.example.myemployees.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EmployeeDetailFragment : DialogFragment() {

    private lateinit var viewmodel : EmployeeDetailViewmodel



    private var mContext : Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    fun checkIfFragmentAttached(operation: Context.() -> Unit) {
        if (isAdded && mContext != null) {
            operation(mContext!!)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        return inflater.inflate(R.layout.fragment_employee_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(isAdded){
            checkIfFragmentAttached {

                // adding all the value of role into a mutable list
                val roles = mutableListOf<String>()
                Role.values().forEach { roles.add(it.name) }

                val arrayAdapter = ArrayAdapter(requireActivity() ,android.R.layout.simple_spinner_item , roles)
                val Rolespinner: Spinner = view.findViewById(R.id.spinnerRole)
                Rolespinner.adapter = arrayAdapter

                //Adding age to Age Spinner
                val ages = mutableListOf<Int>()
                for (i in 18 until 81){
                    ages.add(i)
                }

                val ageSpinner: Spinner = view.findViewById(R.id.age_spinner)
                ageSpinner.adapter = ArrayAdapter(requireContext() , android.R.layout.simple_spinner_item , ages)

                val bundle = arguments
                val Id = bundle?.getLong("newEmployeeID" , 0L)
                viewmodel.setEmployeeId(Id!!)

                viewmodel.employee.observe(viewLifecycleOwner , Observer {
                    it?.let { setData(it) }
                })

                view.findViewById<Button>(R.id.save_btn).setOnClickListener{
                    saveEmployee()
                }
                view.findViewById<Button>(R.id.delete_btn).setOnClickListener{
                    deleteEmployee()
                }

//                view.findViewById<>()

//                savebtn.setoncli{
//                    save vaala kaam
//                    dismiss()
//                    lifecycleScope.launch(Dispatchers.IO) {
//
//                    }
//                }

            }
        }

    }

    private fun deleteEmployee() {
        TODO("Not yet implemented")
    }

    private fun saveEmployee() {
        TODO("Not yet implemented")
    }

    private fun setData(employee: Employee) {

    }

}