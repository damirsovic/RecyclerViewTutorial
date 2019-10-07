package cz.test.damirsovic.recyclerviewtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import cz.test.damirsovic.recyclerviewtutorial.events.IFragmentEvent
import cz.test.damirsovic.recyclerviewtutorial.view.InitFragment
import cz.test.damirsovic.recyclerviewtutorial.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IFragmentEvent {


    private lateinit var dataViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        SetFragment(InitFragment())
    }

    override fun SetFragment(fragment: Fragment) {
        if (GlobalHolder.childCount == 0)
            addFragment(fragment)
        else
            replaceFragment(fragment)
    }

    override fun onBackPressed() {
        replaceFragment(InitFragment())
    }

    fun addFragment(fragment: Fragment) {
        System.out.println(fragment::class.java.name)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.GlobalHolder, fragment, fragment::class.java.name)
            .commit()
    }

    fun replaceFragment(fragment: Fragment) {
        System.out.println(fragment::class.java.name)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.GlobalHolder, fragment, fragment::class.java.name)
            .commit()
    }
}