package br.com.dmagdaleno.financas.model

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    private var receita: BigDecimal = BigDecimal.ZERO
    private var despesa: BigDecimal = BigDecimal.ZERO

    fun receita(): BigDecimal {
        var total = BigDecimal.ZERO
        for(transacao in transacoes){
            if(transacao.tipo == Tipo.RECEITA) {
                total = total.plus(transacao.valor)
            }
        }
        receita = total
        return total
    }

    fun despesa(): BigDecimal {
        var total = BigDecimal.ZERO
        for(transacao in transacoes){
            if(transacao.tipo == Tipo.DESPESA) {
                total = total.plus(transacao.valor)
            }
        }
        despesa = total
        return total
    }

    fun total(): BigDecimal {
        return receita.subtract(despesa)
    }

}