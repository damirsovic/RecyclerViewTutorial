package cz.test.damirsovic.recyclerviewtutorial.model


import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class DataModelRepository {
    val items: List<DataModel> = arrayOf(
        DataModel("Mango", 0),
        DataModel("Apple", 0),
        DataModel("Orange", 0),
        DataModel("Banana", 0),
        DataModel("Grapes", 0)
    ).asList()

    val newItems: List<DataModel> = arrayOf(
        DataModel("Berry", 0),
        DataModel("Peach", 0),
        DataModel("Lemon", 0),
        DataModel("Kiwi", 0),
        DataModel("Cherry", 0)
    ).asList()

    // mutable list of items
    val itemsList: MutableLiveData<List<DataModel>> = MutableLiveData<List<DataModel>>()
    var stream : Observable<DataModel>? = null

    // keep last value
    val number: MutableLiveData<Int> = MutableLiveData<Int>(0)
    // check if data's changed
    val changed: MutableLiveData<Boolean> = MutableLiveData(false)

    constructor() {
        loadItems()
            .toList()
            .toObservable()
            .doOnNext { item -> itemsList.postValue(item) }
            .subscribe(
                { a -> System.out.println(a) },
                { err -> System.out.println(err.localizedMessage) },
                { System.out.println("Finished") }
            )
    }

    fun getItems() = itemsList

    // Data operations
    //

    //Increase number on existing list
    fun increase() {
        // save last value
        number.value = (number.value)?.plus(1)
        // change number in the list
        stream = Observable.fromArray(itemsList.value)
            .flatMapIterable { list -> list }
            .map {dm -> DataModel(dm.name, number.value!!) }
        update()
    }

    // decrease number on existing list
    fun decrease() {
        // save last value
        number.value = (number.value)?.minus(1)
        // change value in the list
        stream = Observable.fromArray(itemsList.value)
            .flatMapIterable { list -> list }
            .map {dm -> DataModel(dm.name, number.value!!) }
        update()
    }

    // Combine both lists
    fun combine() {
        // merge items and newItems to one list
        stream = Observable.concat(loadItems(), loadNewItems())
        update()
    }

    // load newItems to itemsList
    fun changeList() {
        if(changed.value!!)
            stream = loadItems()
        else
            stream = loadNewItems()
        changed.value = !changed.value!!
        update()
    }

    fun update() {
        stream!!
            .toList()
            .toObservable()
            .doOnNext { item -> itemsList.postValue(item) }
            .subscribe(
                { a ->
                    System.out.println("Update ".plus(a))
                },
                { err -> System.out.println("Error in update: ".plus(err.localizedMessage)) },
                { System.out.println("Finished update") }
            )
    }

    // Load items to stream of DataModels
    fun loadItems(): Observable<DataModel> =
        Observable.fromIterable(items)
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.computation())
            .map { dm -> DataModel(dm.name, number.value!!) }


    // Load newItems to stream of DataModels
    fun loadNewItems(): Observable<DataModel> =
        Observable.fromIterable(newItems)
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.computation())
            .map { dm -> DataModel(dm.name, number.value!!) }
}