package com.example.agenda.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.agenda.R
import com.example.agenda.databinding.ActivityMainBinding
import com.example.agenda.fragments.AgendamentoFragment
import com.example.agenda.fragments.HomeFragment
import com.example.agenda.fragments.PerfilFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mHomeFragment: HomeFragment
    private lateinit var mAgendamentoFragment: AgendamentoFragment
    private lateinit var mPerfilFragment: PerfilFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mHomeFragment = HomeFragment.newInstance()
        mAgendamentoFragment = AgendamentoFragment.newInstance()
        mPerfilFragment = PerfilFragment.newInstance()

        mBinding.bottomNavigationView.setOnItemSelectedListener(::onItemselecionado)

        trocarFragmento(mHomeFragment)
    }

    private fun onItemselecionado(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.action_home) {
            trocarFragmento(mHomeFragment)
        } else if(menuItem.itemId == R.id.action_add) {
            trocarFragmento(mAgendamentoFragment)
        } else {
            trocarFragmento(mPerfilFragment)
        }
        return true
    }

    private fun trocarFragmento(fragmento: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.replace(R.id.container, fragmento)
        fragmentTransaction.commit()
    }
}