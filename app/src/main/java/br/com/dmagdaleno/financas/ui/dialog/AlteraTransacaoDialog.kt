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
import br.com.dmagdaleno.financas.delegate.AdicionaTransacaoDelegate
import br.com.dmagdaleno.financas.extension.converteEmCalendar
import br.com.dmagdaleno.financas.extension.formatada
import br.com.dmagdaleno.financas.model.Tipo
import br.com.dmagdaleno.financas.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class AlteraTransacaoDialog(private val context: Context,
                            private val parent: ViewGroup) {

    val layout: View = criaLayout()
    private val campoValor = layout.form_transacao_valor
    private val campoCategorias = layout.form_transacao_categoria
    private val campoData = layout.form_transacao_data

    fun exibe(transacao: Transacao, delegate: AdicionaTransacaoDelegate) {
        configuraCampoData()
        configuraCampoCategoria(transacao.tipo)
        configuraFormulario(transacao.tipo, delegate)

        campoValor.setText(transacao.valor.toString())
        campoData.setText(transacao.data.formatada())
        campoCategorias.setSelection(posicaoDaCategoria(transacao))
    }

    private fun posicaoDaCategoria(transacao: Transacao) =
            context.resources.getStringArray(categoriasPor(transacao.tipo))
                    .indexOf(transacao.categoria)

    private fun configuraFormulario(tipo: Tipo, delegate: AdicionaTransacaoDelegate) {
        AlertDialog.Builder(context)
            .setTitle(escolheTituloPor(tipo))
            .setView(layout)
            .setPositiveButton("Alterar") { _, _ ->
                val valorTexto = campoValor.text.toString()
                val data = campoData.text.toString()
                val categoria = campoCategorias.selectedItem.toString()

                val transacao = Transacao(valor = paraBigDecimal(valorTexto),
                        data = data.converteEmCalendar(),
                        categoria = categoria,
                        tipo = tipo)

                delegate.finaliza(transacao)

            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun escolheTituloPor(tipo: Tipo): Int {
        if(tipo == Tipo.RECEITA)
            return R.string.altera_receita
        return R.string.altera_despesa
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

    private fun configuraCampoCategoria(tipo: Tipo) {
        val categorias = categoriasPor(tipo)
        val adapter = ArrayAdapter.createFromResource(context,
                categorias,
                android.R.layout.simple_spinner_dropdown_item)

        campoCategorias.adapter = adapter
    }

    private fun categoriasPor(tipo: Tipo) : Int {
        if(tipo == Tipo.RECEITA)
            return R.array.categorias_de_receita
        return R.array.categorias_de_despesa
    }

    private fun configuraCampoData() {
        val hoje = Calendar.getInstance()

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        campoData.setOnClickListener {
            DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { _, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)
                    campoData.setText(dataSelecionada.formatada())
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