package cz.test.damirsovic.recyclerviewtutorial.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import cz.test.damirsovic.recyclerviewtutorial.R
import cz.test.damirsovic.recyclerviewtutorial.model.DataModelRepository
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivityViewModel() : ViewModel() {

    val repo = DataModelRepository()
    val dataList = repo.getItems()


    fun increaseData(){
        repo.increase()
    }

    fun decreaseData(){
        repo.decrease()
    }

    fun combineData(){
        repo.combine()
    }

    fun changeData(){
        repo.changeList()
    }

    fun getCurrentNumber() : Int{
        return repo.number.value!!
    }
}