package com.example.mocksitef

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.mocksitef.databinding.FragmentDialogSegundaTelaBinding

class DialogSegundaTelaFragment(
    private var pinpad: String
) : DialogFragment() {
    private var _binding: FragmentDialogSegundaTelaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogSegundaTelaBinding.inflate(inflater, container, false)
        return binding.root
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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pinpadSelecionado = pinpad

        binding.cartaoDebito.setOnClickListener { chamaDialogTerceiraTela("Cartão de débito") }
        binding.cartaoCredito.setOnClickListener { chamaDialogTerceiraTela("Cartão de crédito") }
        binding.cartaoPrivate.setOnClickListener { chamaDialogTerceiraTela("Cartão Private Label") }
        binding.cartaoCombustivel.setOnClickListener { chamaDialogTerceiraTela("Cartão Combustível") }
        binding.preAutorizacao.setOnClickListener { chamaDialogTerceiraTela("Cartão gift") }
        binding.refeicao.setOnClickListener { chamaDialogTerceiraTela("Refeição") }

        binding.cheque.setOnClickListener {
            Toast.makeText(requireContext(), "Forma de pagamento indisponivel", Toast.LENGTH_SHORT).show()
        }

        binding.carteiraDigital.setOnClickListener {
            dismiss()
        }

        binding.btnCancelar.setOnClickListener {
            dismiss()
        }

    }

    private fun chamaDialogTerceiraTela(cartaoSelecionado: String) {
        dismiss()
        val dialog = DialogTerceiraTelaFragment(cartaoSelecionado)
        dialog.show(parentFragmentManager, "DialogTerceiraTelaFragment")
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}