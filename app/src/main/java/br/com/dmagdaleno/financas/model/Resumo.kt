package br.com.dmagdaleno.financas.model

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    val receita: BigDecimal
        get() = somatorioPor(Tipo.RECEITA)

    val despesa: BigDecimal
        get() = somatorioPor(Tipo.DESPESA)

    fun total(): BigDecimal = receita.subtract(despesa)

    fun somatorioPor(tipo: Tipo): BigDecimal =
        BigDecimal(transacoes
                .filter { it.tipo == tipo }
                .sumByDouble { it.valor.toDouble() })

}