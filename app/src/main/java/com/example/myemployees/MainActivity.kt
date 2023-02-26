package com.example.myemployees

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myemployees.Data.Adapter_RV
import com.example.myemployees.Data.Employee
import com.example.myemployees.UI.EmployeeDetailFragment
import com.example.myemployees.UI.MainActivityViewmodel
import com.example.myemployees.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewmodel
    private var emp_list:ArrayList<Employee> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //now its time to get the recycler view

        binding.employeeRV.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter_RV(emp_list, this,supportFragmentManager)
        binding.employeeRV.adapter = adapter

        //we are attaching our main activity to this viewModel, that's why the owner will be this.
        //we can attach several activities or fragments to this viewModel.
        viewModel = ViewModelProvider(this@MainActivity)[MainActivityViewmodel:: class.java]

        viewModel.employees.observe(this) { list ->
            list?.let {
                adapter.submitList(emp_list)
            }
        }






        binding.addBtn.setOnClickListener{
            val bundle = Bundle()
            bundle.putLong("newEmployeeID" , 0L)

            val dialogFragment = EmployeeDetailFragment()
            dialogFragment.arguments = bundle
            dialogFragment.show(supportFragmentManager, "Id")


            val intent = Intent(this@MainActivity , EmployeeDetailFragment:: class.java)

            intent.putExtras(bundle)
            startActivity(intent)
        }


    }

}