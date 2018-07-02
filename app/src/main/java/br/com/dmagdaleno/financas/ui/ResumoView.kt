package br.com.dmagdaleno.financas.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.extension.formatado
import br.com.dmagdaleno.financas.model.Resumo
import br.com.dmagdaleno.financas.model.Tipo
import br.com.dmagdaleno.financas.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import kotlinx.android.synthetic.main.transacao_item.view.*
import java.math.BigDecimal

class ResumoView(private val context: Context,
                 private val view: View,
                 private val transacoes: List<Transacao>) {

    private val resumo: Resumo = Resumo(transacoes)

    fun adicionaReceita() {
        view.resumo_card_receita.text = resumo.receita().formatado()
        view.resumo_card_receita.setTextColor(ContextCompat.getColor(context, R.color.receita))
    }

    fun adicionaDespesa() {
        view.resumo_card_despesa.text = resumo.despesa().formatado()
        view.resumo_card_despesa.setTextColor(ContextCompat.getColor(context, R.color.despesa))
    }

    fun adicionaTotal(){
        view.resumo_card_total.text = resumo.total().formatado()
    }
}