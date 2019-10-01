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

    val newItems = arrayOf(
        "Berry",
        "Peach",
        "Lemon",
        "Kiwi",
        "Cherry"
    ).asList()

    val itemList : MutableLiveData<List<String>> = MutableLiveData<List<String>>()

    val number : MutableLiveData<Int> = MutableLiveData<Int>()

    constructor() {
        number.value =0
        loadItems()
            .toList()
            .toObservable()
            .doOnNext{item -> itemList.postValue(item)}
            .subscribe(
            { a -> System.out.println(a)},
            { err -> System.out.println(err.localizedMessage) },
            { System.out.println("Finished") }
        )

    }

    fun increase() {
        number.value = (number.value)?.plus(1)
        loadItems()
            .toList()
            .toObservable()
            .subscribe(
            { a ->
                itemList.postValue(a)
                System.out.println("Decrease ".plus(a))
            },
            { err -> System.out.println(err.localizedMessage) },
            { System.out.println("Finished increase") }
        )
    }

    fun decrease() {
        number.value = (number.value)?.minus(1)
        loadItems()
            .toList()
            .toObservable()
            .doOnNext{item -> itemList.postValue(item)}
            .subscribe(
            { a ->
                System.out.println("Decrease ".plus(a))
            },
            { err -> System.out.println(err.localizedMessage) },
            { System.out.println("Finished decrease") }
        )
    }
    fun combine(){
        Observable.merge(loadItems(), alterItems())
            .toList()
            .toObservable()
            .doOnNext{item ->
                System.out.println("Combination onNext ".plus(item))
                itemList.postValue(item)}
            .subscribe(
            { a ->
                System.out.println("Combination ".plus(a))
            },
            { err -> System.out.println(err.localizedMessage) },
            { System.out.println("Finished combination") }
        )
    }

    fun alter(){
        alterItems()
            .toList()
            .toObservable()
            .doOnNext{item -> itemList.postValue(item)}
            .subscribe(
                { a ->
                    System.out.println("Alter ".plus(a))
                },
                { err -> System.out.println(err.localizedMessage) },
                { System.out.println("Finished alter") }
            )
    }

    fun loadItems() : Observable<String> {
        return Observable.fromArray(items)
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.computation())
            .flatMapIterable { list -> list }
            .map { s -> String.format("%s %d", s, number.value) }
            .doOnNext{ item -> System.out.println("LoadItems ".plus(item))
            }
    }

    fun alterItems() : Observable<String> {
        return Observable.fromArray(newItems)
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.computation())
            .flatMapIterable{list -> list}
            .map { s -> String.format("%s %d", s, number.value) }
            .doOnNext{item -> System.out.println("alterItems ".plus(item))}
    }

    fun getItems() = itemList
}