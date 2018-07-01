package br.com.dmagdaleno.financas.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formatada(): String {
    return SimpleDateFormat("dd/MM/yyyy").format(this.time)
}