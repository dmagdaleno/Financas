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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = listaTransacoesDeExemplo()

        configuraResumo(transacoes)

        configuraListaTransacoes(transacoes)

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
                    .setPositiveButton("Adicionar", null)
                    .setNegativeButton("Cancelar", null)
                    .show()
        }
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraListaTransacoes(transacoes: List<Transacao>) {
        val adapter = ListaTransacoesAdapter(this, transacoes)

        lista_transacoes_listview.adapter = adapter
    }

    private fun listaTransacoesDeExemplo(): List<Transacao> {
        return listOf (
                Transacao(
                    valor = BigDecimal(10),
                    categoria = "Despesa indefinida",
                    tipo = Tipo.DESPESA
                ),
                Transacao(
                    valor = BigDecimal(1000),
                    categoria = "Lazer",
                    tipo = Tipo.DESPESA
                ),
                Transacao(
                    valor = BigDecimal(9000),
                    categoria = "Salário",
                    tipo = Tipo.RECEITA
                ),
                Transacao(
                    valor = BigDecimal(20.5),
                    categoria = "Comida",
                    tipo = Tipo.DESPESA
                ),
                Transacao(
                    valor = BigDecimal(100),
                    categoria = "Economia",
                    tipo = Tipo.RECEITA
                )
        )
    }
}