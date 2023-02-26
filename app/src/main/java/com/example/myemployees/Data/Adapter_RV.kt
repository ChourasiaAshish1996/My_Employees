package com.example.myemployees.Data

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myemployees.R
import com.example.myemployees.UI.EmployeeDetailFragment

class Adapter_RV(
    val list: ArrayList<Employee>,
    val mContext: Context,
    val supportFragmentManager: FragmentManager
) : RecyclerView.Adapter<Adapter_RV.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(mContext).inflate(R.layout.list_item , parent , false)
        val emp_viewholder = MyViewHolder(inflater)
        return  emp_viewholder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val current_item = list[position]
        holder.ProfilePicture.setImageResource(current_item.photo)
        holder.Name.text = current_item.name
        holder.Role.text = current_item.role
        holder.Age.text  = current_item.age.toString()
        holder.Gender.text = current_item.gender.toString()

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("clickedItemId", current_item.Id)
//            it.findNavController().navigate(
//                //R.id.action_employee_list_to_employee_detail,bundle
//            )


            val confirmationDialog = EmployeeDetailFragment()
//            val bundle = Bundle()
//            bundle.putString("address", address?.get(0)?.getAddressLine(0)?:"null")
//            bundle.putString("name", doc_name)
//            bundle.putString("id", doc_uid)
//            bundle.putString("timing", doc_Timing)
//            bundle.putParcelable("doc_sign_bitmap",doc_signature_btimap)
            confirmationDialog.arguments = bundle
            confirmationDialog.show(supportFragmentManager,"confirmationDialog" )
        }

    }

    fun submitList(newlist : ArrayList<Employee>){
        list.clear()
        list.addAll(newlist)

        notifyDataSetChanged()
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ProfilePicture : ImageView = itemView.findViewById(R.id.profile_image)
        val Name           : TextView = itemView.findViewById(R.id.Emp_Name)
        val Role           : TextView = itemView.findViewById(R.id.Emp_Designation)
        val Age            : TextView = itemView.findViewById(R.id.Emp_Age)
        val Gender         : TextView = itemView.findViewById(R.id.Emp_Gender)
    }
}