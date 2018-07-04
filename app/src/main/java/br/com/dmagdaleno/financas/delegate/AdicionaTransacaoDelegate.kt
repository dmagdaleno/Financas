package br.com.dmagdaleno.financas.delegate

import br.com.dmagdaleno.financas.model.Transacao

interface AdicionaTransacaoDelegate {
    fun finaliza(transacao: Transacao)
}