package cz.test.damirsovic.recyclerviewtutorial.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import cz.test.damirsovic.recyclerviewtutorial.R
import cz.test.damirsovic.recyclerviewtutorial.events.IFragmentEvent
import cz.test.damirsovic.recyclerviewtutorial.viewmodel.InitFragmentViewModel
import kotlinx.android.synthetic.main.fragment_init.*

class InitFragment : Fragment() {

    private lateinit var viewModel: InitFragmentViewModel
    private lateinit var fragmentEvent : IFragmentEvent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_init, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(InitFragmentViewModel::class.java)
        runRecyclerDemo.setOnClickListener {
            fragmentEvent.SetFragment(RecyclerViewFragment())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IFragmentEvent) {
            fragmentEvent = context
        } else {
            throw ClassCastException(
                context.toString() + " must implement IFragmentEvent.")
        }
    }

    override fun onDetach() {
        super.onDetach()
    }


}
