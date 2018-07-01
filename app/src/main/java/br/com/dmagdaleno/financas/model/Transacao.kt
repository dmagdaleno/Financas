package br.com.dmagdaleno.financas.model

import java.math.BigDecimal
import java.util.*

class Transacao(val valor: BigDecimal,
                val categoria: String,
                val data: Calendar)