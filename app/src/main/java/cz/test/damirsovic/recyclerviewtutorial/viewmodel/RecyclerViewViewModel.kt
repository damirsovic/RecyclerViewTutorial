package cz.test.damirsovic.recyclerviewtutorial.viewmodel

import androidx.lifecycle.ViewModel
import cz.test.damirsovic.recyclerviewtutorial.model.DataModelRepository

class RecyclerViewViewModel : ViewModel() {
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
