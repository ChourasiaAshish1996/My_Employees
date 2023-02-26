package com.example.myemployees.Data

import android.app.Application
import androidx.lifecycle.LiveData


class EmployeeDetailRepo(context: Application) {

    //Here we are making an object of Dao and using database we are calling the abstract fun employeedatabase which further calls the functions of employeeDetailDao
    private val employeeDetailsDao : EmployeeDetailDao = EmployeeDatabase.getDatabase(context).employeeDetailDao()

    fun getEmployee(id : Long): LiveData<Employee> {
        return  employeeDetailsDao.getEmployee(id)
    }

    suspend fun insertEmployee(employee: Employee): Long {
        return employeeDetailsDao.insertEmployee(employee)
    }

    suspend fun updateEmployee(employee: Employee){
        employeeDetailsDao.updateEmployee(employee)
    }

    suspend fun deleteEmployee(employee: Employee){
        employeeDetailsDao.deleteEmployee(employee)
    }
}