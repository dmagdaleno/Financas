package br.com.dmagdaleno.financas.model

import java.math.BigDecimal
import java.util.Calendar

class Transacao(
        val id: Long? = null,
        val valor: BigDecimal,
        val categoria: String = "Indefinida",
        val tipo: Tipo,
        val data: Calendar = Calendar.getInstance(),
        val objetivo: Objetivo? = null)