package cz.test.damirsovic.recyclerviewtutorial.viewmodel

import androidx.lifecycle.ViewModel

import cz.test.damirsovic.recyclerviewtutorial.model.DataModelRepository
import androidx.lifecycle.MediatorLiveData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivityViewModel : ViewModel() {
    val repo = DataModelRepository()
    val dataList = repo.getItems()


    fun increaseData(){
        repo.increase()
    }

    fun decreaseData(){
        repo.decrease()
    }

    fun getCurrentNumber() : Int{
        return repo.number.value!!
    }
}