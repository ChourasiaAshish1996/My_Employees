package com.example.myemployees.UI

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myemployees.Data.Employee
import com.example.myemployees.Data.MainActivityRepo

class MainActivityViewmodel(application: Application) : AndroidViewModel(application) {
    private val Repo:MainActivityRepo = MainActivityRepo(application)

    val employees : LiveData<List<Employee>> =Repo.getEmployees()
}