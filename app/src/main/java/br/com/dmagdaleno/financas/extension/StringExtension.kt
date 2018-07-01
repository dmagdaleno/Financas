package br.com.dmagdaleno.financas.extension

fun String.limitaEm(tamanho: Int): String {
    if(this.length > tamanho)
        return "${this.substring(0, tamanho-3)}..."
    return this
}