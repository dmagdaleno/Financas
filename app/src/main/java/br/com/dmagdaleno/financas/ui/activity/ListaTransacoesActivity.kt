package br.com.dmagdaleno.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.delegate.AdicionaTransacaoDelegate
import br.com.dmagdaleno.financas.model.Tipo
import br.com.dmagdaleno.financas.model.Transacao
import br.com.dmagdaleno.financas.ui.ResumoView
import br.com.dmagdaleno.financas.ui.adapter.ListaTransacoesAdapter
import br.com.dmagdaleno.financas.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity: AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        atualizaTransacoes(null)

        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogAdicionaTransacao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogAdicionaTransacao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogAdicionaTransacao(tipo: Tipo) {
        AdicionaTransacaoDialog(this, window.decorView as ViewGroup)
                .configuraDialog(tipo, object : AdicionaTransacaoDelegate {
                    override fun finaliza(transacao: Transacao) {
                        atualizaTransacoes(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
    }

    private fun atualizaTransacoes(transacao: Transacao?) {
        if (transacao != null)
            transacoes.add(transacao)

        configuraResumo()

        configuraListaTransacoes()
    }

    private fun configuraResumo() {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraListaTransacoes() {
        val adapter = ListaTransacoesAdapter(this, transacoes)

        lista_transacoes_listview.adapter = adapter
    }

}