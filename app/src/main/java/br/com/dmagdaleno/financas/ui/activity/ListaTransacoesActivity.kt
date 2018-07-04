package br.com.dmagdaleno.financas.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.extension.converteEmCalendar
import br.com.dmagdaleno.financas.extension.formatada
import br.com.dmagdaleno.financas.model.Tipo
import br.com.dmagdaleno.financas.model.Transacao
import br.com.dmagdaleno.financas.ui.ResumoView
import br.com.dmagdaleno.financas.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity: AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        atualizaTransacoes()

        lista_transacoes_adiciona_receita.setOnClickListener{
            val parent = window.decorView as ViewGroup
            val view = LayoutInflater.from(this)
                    .inflate(R.layout.form_transacao, parent, false)

            val ano = 2018
            val mes = 6
            val dia = 2

            val hoje = Calendar.getInstance().formatada()
            view.form_transacao_data.setText(hoje)
            view.form_transacao_data.setOnClickListener {
                DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { datePicker, ano, mes, dia ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(ano, mes, dia)
                        view.form_transacao_data.setText(dataSelecionada.formatada())
                    },
                    ano, mes, dia
                )
                .show()
            }

            val adapter = ArrayAdapter.createFromResource(this,
                    R.array.categorias_de_receita,
                    android.R.layout.simple_spinner_dropdown_item)
            view.form_transacao_categoria.adapter = adapter

            AlertDialog.Builder(this)
                    .setTitle(R.string.receita)
                    .setView(view)
                    .setPositiveButton("Adicionar", { dialog, i ->
                        val valorTexto = view.form_transacao_valor.text.toString()
                        val data = view.form_transacao_data.text.toString()
                        val categoria = view.form_transacao_categoria.selectedItem.toString()

                        var valor = try{
                            BigDecimal(valorTexto)
                        } catch(exception: NumberFormatException) {
                            Toast.makeText(this, "Falha na conversão de valor",
                                Toast.LENGTH_LONG).show()
                            BigDecimal.ZERO
                        }

                        val transacao = Transacao(valor = valor,
                                data = data.converteEmCalendar(),
                                categoria = categoria,
                                tipo = Tipo.RECEITA)

                        transacoes.add(transacao)

                        atualizaTransacoes()
                        lista_transacoes_adiciona_menu.close(true)
                    })
                    .setNegativeButton("Cancelar", null)
                    .show()
        }
    }

    private fun atualizaTransacoes() {
        configuraResumo()

        configuraListaTransacoes()
    }

    private fun configuraResumo() {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraListaTransacoes() {
        val adapter = ListaTransacoesAdapter(this, transacoes)

        lista_transacoes_listview.adapter = adapter
    }

}