package br.com.dmagdaleno.financas.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.extension.formatada
import br.com.dmagdaleno.financas.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(context: Context,
                             transacoes: List<Transacao>): BaseAdapter() {

    private val transacoes = transacoes
    private val context = context

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val viewGerada = LayoutInflater
                .from(context)
                .inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[posicao]
        viewGerada.transacao_valor.setText("R$ " + transacao.valor.toString())
        viewGerada.transacao_categoria.setText(transacao.categoria)
        viewGerada.transacao_data.setText(transacao.data.formatada())

        return viewGerada
    }

    override fun getItem(posicao: Int): Transacao {
        return transacoes[posicao]
    }

    override fun getItemId(posicao: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}