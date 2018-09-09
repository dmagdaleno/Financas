package br.com.dmagdaleno.financas.model

import java.math.BigDecimal
import java.util.Calendar

data class Objetivo(
        val id: Long? = null,
        val valorTotal: BigDecimal,
        val valorAtual: BigDecimal,
        val valorInicial: BigDecimal,
        val dataFinal: Calendar,
        val descricao: String)