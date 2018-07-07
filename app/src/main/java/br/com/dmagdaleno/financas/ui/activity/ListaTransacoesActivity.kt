package br.com.dmagdaleno.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.model.Tipo
import br.com.dmagdaleno.financas.model.Transacao
import br.com.dmagdaleno.financas.ui.ResumoView
import br.com.dmagdaleno.financas.ui.adapter.ListaTransacoesAdapter
import br.com.dmagdaleno.financas.ui.dialog.AdicionaTransacaoDialog
import br.com.dmagdaleno.financas.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity: AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    private val view: View by lazy { window.decorView }
    private val parent: ViewGroup by lazy { view as ViewGroup }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        atualizaTransacoes()

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
        AdicionaTransacaoDialog(this, parent)
            .exibe(tipo) { novaTransacao ->
                adiciona(novaTransacao)
                lista_transacoes_adiciona_menu.close(true)
            }
    }

    private fun adiciona(transacao: Transacao) {
        transacoes.add(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraResumo()
        configuraListaTransacoes()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraListaTransacoes() {
        val listaTransacoesAdapter = ListaTransacoesAdapter(this, transacoes)
        with(lista_transacoes_listview) {
            adapter = listaTransacoesAdapter

            setOnItemClickListener { parent, view, posicao, id ->
                val transacao = transacoes[posicao]
                chamaDialogAlteraTransacao(transacao, posicao)
            }

            setOnCreateContextMenuListener { contextMenu, view, contextMenuInfo ->
                contextMenu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    private fun chamaDialogAlteraTransacao(transacao: Transacao, posicao: Int) {
        AlteraTransacaoDialog(this, parent)
            .exibe(transacao) { transacaoAlterada -> altera(transacaoAlterada, posicao) }
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        transacoes[posicao] = transacao
        atualizaTransacoes()
    }

}