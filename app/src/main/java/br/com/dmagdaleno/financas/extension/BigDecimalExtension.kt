package br.com.dmagdaleno.financas.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formatado(): String {
    val formatador = DecimalFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formatador.format(this).replace("R$", "R$ ")
}