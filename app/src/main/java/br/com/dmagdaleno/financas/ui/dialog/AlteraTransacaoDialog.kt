package br.com.dmagdaleno.financas.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.extension.formatada
import br.com.dmagdaleno.financas.model.Tipo
import br.com.dmagdaleno.financas.model.Transacao

class AlteraTransacaoDialog(
        context: Context,
        parent: ViewGroup) : FormularioTransacaoDialog(context, parent) {

    override val tituloBotaoConfirmar: String
        get() = "Alterar"


    fun exibe(transacao: Transacao, finaliza: (transacao: Transacao) -> Unit) {
        super.exibe(transacao.tipo, finaliza)

        campoValor.setText(transacao.valor.toString())
        campoData.setText(transacao.data.formatada())
        campoCategorias.setSelection(posicaoDaCategoria(transacao))
    }

    override fun escolheTituloPor(tipo: Tipo): Int {
        if(tipo == Tipo.RECEITA)
            return R.string.altera_receita
        return R.string.altera_despesa
    }

}
