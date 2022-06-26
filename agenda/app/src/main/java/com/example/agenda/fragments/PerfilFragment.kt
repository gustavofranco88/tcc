package com.example.agenda.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.agenda.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {


    private lateinit var mBinding: FragmentPerfilBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentPerfilBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    /**
     * Método estático para auxiliar na criação do Fragmento
     * Pertence a classe e não aos objetos
     */
    companion object {
        @JvmStatic
        fun newInstance(): PerfilFragment = PerfilFragment()
    }


}