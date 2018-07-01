package br.com.dmagdaleno.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.model.Transacao
import br.com.dmagdaleno.financas.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = listOf(
                Transacao(BigDecimal(20.5),  "gasto", Calendar.getInstance()),
                Transacao(BigDecimal(100),  "receita", Calendar.getInstance()))

        val adapter = ListaTransacoesAdapter(this, transacoes)

        lista_transacoes_listview.adapter = adapter
    }
}