package cz.test.damirsovic.recyclerviewtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cz.test.damirsovic.recyclerviewtutorial.view.RecyclerViewAdapter
import cz.test.damirsovic.recyclerviewtutorial.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var dataViewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        dataView.layoutManager = LinearLayoutManager(this)
        dataViewModel
            .dataList.observe(this, Observer { items ->
            dataView.adapter = RecyclerViewAdapter(items)
        })
        setVisibility()

        bInc.setOnClickListener {
            dataViewModel.increaseData()
            setVisibility()
        }

        bDec.setOnClickListener {
            dataViewModel.decreaseData()
            setVisibility()
        }
        bCombine.setOnClickListener {
            dataViewModel.combineData()
            setVisibility()
        }
        bAlter.setOnClickListener {
            dataViewModel.changeData()
            setVisibility()
        }
    }

    private fun setVisibility(){
        if(dataViewModel.getCurrentNumber()>0)
            bDec.visibility = View.VISIBLE
        else
            bDec.visibility = View.GONE
    }

}