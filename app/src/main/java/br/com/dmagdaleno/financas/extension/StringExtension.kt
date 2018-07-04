package br.com.dmagdaleno.financas.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.limitaEm(tamanho: Int): String {
    if(this.length > tamanho)
        return "${this.substring(0, tamanho-3)}..."
    return this
}

fun String.converteEmCalendar(): Calendar {
    val data = Calendar.getInstance()
    data.time = SimpleDateFormat("dd/MM/yyyy").parse(this)
    return data
}