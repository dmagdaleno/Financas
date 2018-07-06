package br.com.dmagdaleno.financas.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.extension.formatado
import br.com.dmagdaleno.financas.extension.positivo
import br.com.dmagdaleno.financas.model.Resumo
import br.com.dmagdaleno.financas.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val context: Context,
                 private val view: View,
                 private val transacoes: List<Transacao>) {

    private val resumo: Resumo = Resumo(transacoes)

    fun atualiza(){
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaReceita() {
        with(view.resumo_card_receita){
            text = resumo.receita.formatado()
            setTextColor(pegaCor(R.color.receita))
        }
    }

    private fun adicionaDespesa() {
        with(view.resumo_card_despesa){
            text = resumo.despesa.formatado()
            setTextColor(pegaCor(R.color.despesa))
        }
    }

    private fun adicionaTotal(){
        val total = resumo.total()
        val cor = escolheCor(total)
        with(view.resumo_card_total){
            text = total.formatado()
            setTextColor(pegaCor(cor))
        }
    }

    private fun escolheCor(total: BigDecimal) =
            if (total.positivo()) R.color.receita
            else R.color.despesa

    private fun TextView.pegaCor(cor: Int) = ContextCompat.getColor(context, cor)
}
