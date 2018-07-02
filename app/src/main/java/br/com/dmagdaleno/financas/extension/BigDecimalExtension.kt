package br.com.dmagdaleno.financas.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formatado(): String {
    return DecimalFormat.getCurrencyInstance(Locale("pt", "BR"))
            .format(this)
            .replace("R$", "R$ ")
            .replace("-R$ ", "R$ -")
}

fun BigDecimal.positivo(): Boolean {
    return this >= BigDecimal.ZERO
}