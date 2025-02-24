package com.example.mocksitef

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.mocksitef.databinding.FragmentDialogPrimeiraTelaBinding

class DialogPrimeiraTelaFragment : DialogFragment() {
    private var _binding: FragmentDialogPrimeiraTelaBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogPrimeiraTelaBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)

        return dialog
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.let { window ->
            val params = window.attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            window.attributes = params
            //window.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pinpad1.setOnClickListener { chamaDialogSegundaTela("Pinpad 1") }
        binding.pinpad2.setOnClickListener { chamaDialogSegundaTela("Pinpad 2") }
        binding.pinpad3.setOnClickListener { chamaDialogSegundaTela("Pinpad 3") }

        binding.btnCancelar.setOnClickListener {
            dismiss()
        }

    }

    private fun chamaDialogSegundaTela(pinpadSelecionado: String) {
        dismiss()
        val dialog = DialogSegundaTelaFragment(pinpadSelecionado)
        dialog.show(parentFragmentManager, "DialogSegundaTelaFragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}