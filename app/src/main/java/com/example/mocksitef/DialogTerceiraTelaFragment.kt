package com.example.mocksitef

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.mocksitef.databinding.FragmentDialogTerceiraTelaBinding

class DialogTerceiraTelaFragment(
    private var cartao: String
) : DialogFragment() {
    private var _binding: FragmentDialogTerceiraTelaBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogTerceiraTelaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartaoSelecionado = cartao

        binding.magnetico.setOnClickListener {
            dismiss()
        }

        binding.digitado.setOnClickListener {
            dismiss()
        }

        binding.btnCancelar.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}