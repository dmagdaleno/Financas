package br.com.dmagdaleno.financas.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.extension.converteEmCalendar
import br.com.dmagdaleno.financas.extension.formatada
import br.com.dmagdaleno.financas.model.Tipo
import br.com.dmagdaleno.financas.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class AdicionaTransacaoDialog(private val context: Context,
                              private val parent: ViewGroup) {

    val layout: View = criaLayout()

    private fun configuraDialog() {

        configuraCampoData()

        configuraCampoCategoria()

        configuraFormulario()

    }

    private fun configuraFormulario() {
        AlertDialog.Builder(context)
            .setTitle(R.string.receita)
            .setView(layout)
            .setPositiveButton("Adicionar") { _, _ ->
                val valorTexto = layout.form_transacao_valor.text.toString()
                val data = layout.form_transacao_data.text.toString()
                val categoria = layout.form_transacao_categoria.selectedItem.toString()

                var valor = paraBigDecimal(valorTexto)

                val transacao = Transacao(valor = valor,
                        data = data.converteEmCalendar(),
                        categoria = categoria,
                        tipo = Tipo.RECEITA)

            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun paraBigDecimal(valorTexto: String): BigDecimal {
        return try {
            BigDecimal(valorTexto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na conversão de valor",
                    Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    private fun configuraCampoCategoria() {
        val adapter = ArrayAdapter.createFromResource(context,
                R.array.categorias_de_receita,
                android.R.layout.simple_spinner_dropdown_item)

        layout.form_transacao_categoria.adapter = adapter
    }

    private fun configuraCampoData() {
        val hoje = Calendar.getInstance()

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        layout.form_transacao_data.setText(hoje.formatada())
        layout.form_transacao_data.setOnClickListener {
            DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { _, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)
                    layout.form_transacao_data.setText(dataSelecionada.formatada())
                },
                ano, mes, dia
            )
            .show()
        }
    }


    private fun criaLayout(): View {
        return LayoutInflater.from(context)
                .inflate(R.layout.form_transacao, parent, false)
    }
}