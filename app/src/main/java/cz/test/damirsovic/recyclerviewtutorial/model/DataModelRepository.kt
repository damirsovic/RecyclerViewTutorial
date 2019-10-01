package cz.test.damirsovic.recyclerviewtutorial.model


import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*


class DataModelRepository {
    val items = arrayOf(
        "Mango",
        "Apple",
        "Orange",
        "Banana",
        "Grapes"
    ).asList()

    val itemList : MutableLiveData<List<String>> = MutableLiveData<List<String>>()

    val number : MutableLiveData<Int> = MutableLiveData<Int>()

    constructor() {
        number.value =0
        loadItems().subscribe(
            { a -> System.out.println(a)},
            { err -> System.out.println(err.localizedMessage) },
            { System.out.println("Finished") }
        )

    }

    fun increase() {
        number.value = (number.value)?.plus(1)
        loadItems().subscribe(
            { a -> System.out.println(a)},
            { err -> System.out.println(err.localizedMessage) },
            { System.out.println("Finished increase") }
        )
    }

    fun decrease() {
        number.value = (number.value)?.minus(1)
        loadItems().subscribe(
            { a -> System.out.println(a)},
            { err -> System.out.println(err.localizedMessage) },
            { System.out.println("Finished decrease") }
        )
    }

    fun loadItems() : Observable<List<String>> {
        val newList = Observable.fromArray(items)
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.computation())
            .flatMapIterable{list -> list}
            .map { s -> String.format("%s %d", s, number.value) }
            .toList()

        // do async operation to fetch users
        return newList.toObservable()
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.computation())
            .doOnNext{ item ->
                System.out.println(item)
                itemList.postValue(item)
            }

    }

    fun getItems() = itemList
}