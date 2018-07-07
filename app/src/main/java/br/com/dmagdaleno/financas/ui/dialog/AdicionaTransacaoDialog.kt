package br.com.dmagdaleno.financas.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.model.Tipo

class AdicionaTransacaoDialog(
        context: Context,
        parent: ViewGroup) : FormularioTransacaoDialog(context, parent) {

    override val tituloBotaoConfirmar: String
        get() = "Adicionar"

    override fun escolheTituloPor(tipo: Tipo): Int {
        if(tipo == Tipo.RECEITA)
            return R.string.adiciona_receita
        return R.string.adiciona_despesa
    }
}