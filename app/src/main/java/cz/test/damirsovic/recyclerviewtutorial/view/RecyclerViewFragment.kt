package cz.test.damirsovic.recyclerviewtutorial.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import cz.test.damirsovic.recyclerviewtutorial.R
import cz.test.damirsovic.recyclerviewtutorial.events.IFragmentEvent
import cz.test.damirsovic.recyclerviewtutorial.viewmodel.RecyclerViewViewModel
import kotlinx.android.synthetic.main.recycler_view_fragment.*

class RecyclerViewFragment : Fragment() {

    private lateinit var viewModel: RecyclerViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecyclerViewViewModel::class.java)
        // TODO: Use the ViewModel

        dataView.layoutManager = LinearLayoutManager(context)
        viewModel
            .dataList.observe(this, Observer { items ->
            dataView.adapter = RecyclerViewAdapter(items)
        })
        setVisibility()

        bInc.setOnClickListener {
            viewModel.increaseData()
            setVisibility()
        }

        bDec.setOnClickListener {
            viewModel.decreaseData()
            setVisibility()
        }
        bCombine.setOnClickListener {
            viewModel.combineData()
            setVisibility()
        }
        bAlter.setOnClickListener {
            viewModel.changeData()
            setVisibility()
        }
    }

    private fun setVisibility(){
        if(viewModel.getCurrentNumber()>0)
            bDec.visibility = View.VISIBLE
        else
            bDec.visibility = View.INVISIBLE
    }

}
