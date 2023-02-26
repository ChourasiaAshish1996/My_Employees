package com.example.myemployees.UI

import android.app.Application
import androidx.lifecycle.*
import com.example.myemployees.Data.Employee
import com.example.myemployees.Data.EmployeeDetailRepo
import kotlinx.coroutines.launch

class EmployeeDetailViewmodel(application: Application) : AndroidViewModel(application) {
    private val DetailsRepo : EmployeeDetailRepo = EmployeeDetailRepo(application)

    private val _employeeId = MutableLiveData<Long>(0)
    val employeeId : LiveData<Long>
        get() = _employeeId

    val employee: LiveData<Employee> = Transformations.switchMap(_employeeId){ id ->
        DetailsRepo.getEmployee(id)
    }

    fun setEmployeeId(id: Long){
        if(_employeeId.value != id){
            _employeeId.value = id
        }
    }
    fun saveEmployee(employee: Employee){
        viewModelScope.launch {
            if(_employeeId.value == 0L){
                _employeeId.value = DetailsRepo.insertEmployee(employee)
            } else{
                DetailsRepo.updateEmployee(employee)
            }
        }
    }

    suspend fun deleteEmployee(){
        viewModelScope.apply {
            employee.value?.let { DetailsRepo.deleteEmployee(it) }
        }
    }

}