package com.example.myemployees.Data

import android.app.Application
import androidx.lifecycle.LiveData

class MainActivityRepo(context : Application) {

    private val mainActivityDao: MainActivityDao =
        EmployeeDatabase.getDatabase(context).employeeListDao()

    fun getEmployees(): LiveData<List<Employee>> = mainActivityDao.getEmployee()
}