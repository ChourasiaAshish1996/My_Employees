package com.example.myemployees.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee :: class] , version = 1)
abstract class EmployeeDatabase: RoomDatabase() {

    //Abstract- a method that has just the method definition but does not contain implementation
    abstract fun employeeListDao(): MainActivityDao
    abstract fun employeeDetailDao(): EmployeeDetailDao

    companion object {
        private var Instance: EmployeeDatabase? = null

        fun getDatabase(context: Context): EmployeeDatabase {
            @Volatile //Volatile annotation  :--  whenever any value is assigned to "var Instance" , then it will be available to each thread automatically
            if (Instance == null){
                synchronized(this){ //to be asked about synchronized lock
                    Room.databaseBuilder(context.applicationContext , EmployeeDatabase ::class.java, "Employee_Database").build().also { Instance = it }

                }
            }
            return Instance!!

        }


    }


}