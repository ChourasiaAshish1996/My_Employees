package com.example.myemployees.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Gender{
    Male,
    Female,
    Other
}

enum class Role{
    Manager,
    Staff,
    Worker
}


@Entity(tableName = "Employee")
data class Employee(
    @PrimaryKey(autoGenerate = true) val Id : Int,
    val name : String,
    val role : String,
    val age : Int,
    val gender :Int,
    val photo : Int
)