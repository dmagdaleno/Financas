package br.com.dmagdaleno.financas.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.extension.formatada
import br.com.dmagdaleno.financas.extension.formatado
import br.com.dmagdaleno.financas.extension.limitaEm
import br.com.dmagdaleno.financas.model.Tipo
import br.com.dmagdaleno.financas.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(val context: Context,
                             val transacoes: List<Transacao>): BaseAdapter() {

    private val tamanhoPermitido = 15

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val viewGerada = LayoutInflater.from(context)
                .inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[posicao]

        adicionaIcone(viewGerada, transacao)
        adicionaValor(viewGerada, transacao)
        adicionaCategoria(viewGerada, transacao)
        adicionaData(viewGerada, transacao)

        return viewGerada
    }

    private fun adicionaIcone(view: View, transacao: Transacao) {
        view.transacao_icone.setBackgroundResource(iconePorTipoDe(transacao))
    }

    private fun iconePorTipoDe(transacao: Transacao): Int {
        if(transacao.tipo == Tipo.RECEITA)
            return R.drawable.icone_transacao_item_receita
        return R.drawable.icone_transacao_item_despesa
    }

    private fun adicionaData(view: View, transacao: Transacao) {
        view.transacao_data.text = transacao.data.formatada()
    }

    private fun adicionaCategoria(view: View, transacao: Transacao) {
        view.transacao_categoria.text = transacao.categoria.limitaEm(tamanhoPermitido)
    }

    private fun adicionaValor(view: View, transacao: Transacao) {
        view.transacao_valor.text = transacao.valor.formatado()
        view.transacao_valor.setTextColor(corPorTipoDe(transacao))
    }

    private fun corPorTipoDe(transacao: Transacao): Int {
        if(transacao.tipo == Tipo.RECEITA)
            return ContextCompat.getColor(context, R.color.receita)
        return ContextCompat.getColor(context, R.color.despesa)
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