package br.com.dmagdaleno.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.model.Tipo
import br.com.dmagdaleno.financas.model.Transacao
import br.com.dmagdaleno.financas.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = listaTransacoesDeExemplo()

        configuraListaTransacoes(transacoes)
    }

    private fun configuraListaTransacoes(transacoes: List<Transacao>) {
        val adapter = ListaTransacoesAdapter(this, transacoes)

        lista_transacoes_listview.adapter = adapter
    }

    private fun listaTransacoesDeExemplo(): List<Transacao> {
        return listOf (
                Transacao(
                    valor = BigDecimal(10),
                    categoria = "Despesa indefinida",
                    tipo = Tipo.DESPESA
                ),
                Transacao(
                    valor = BigDecimal(1000),
                    categoria = "Lazer",
                    tipo = Tipo.DESPESA
                ),
                Transacao(
                    valor = BigDecimal(9000),
                    categoria = "Salário",
                    tipo = Tipo.RECEITA
                ),
                Transacao(
                    valor = BigDecimal(20.5),
                    categoria = "Comida",
                    tipo = Tipo.DESPESA
                ),
                Transacao(
                    valor = BigDecimal(100),
                    categoria = "Economia",
                    tipo = Tipo.RECEITA
                )
        )
    }
}