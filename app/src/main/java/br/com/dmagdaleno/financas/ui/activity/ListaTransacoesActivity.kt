package br.com.dmagdaleno.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.AdapterView
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.dao.TransacaoDAO
import br.com.dmagdaleno.financas.model.Tipo
import br.com.dmagdaleno.financas.model.Transacao
import br.com.dmagdaleno.financas.ui.ResumoView
import br.com.dmagdaleno.financas.ui.adapter.ListaTransacoesAdapter
import br.com.dmagdaleno.financas.ui.dialog.AdicionaTransacaoDialog
import br.com.dmagdaleno.financas.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity: AppCompatActivity() {

    private val dao = TransacaoDAO()
    private val transacoes = dao.transacoes

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

            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
                menu.add(Menu.NONE, 2, Menu.NONE, "Alterar")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        val posicao = posicaoDoItemClicado(item.menuInfo)
        if(itemId == 1) {
            removeTransacaoNa(posicao)
        } else if (itemId == 2) {
            chamaDialogAlteraTransacao(transacoes[posicao], posicao)
        }
        return super.onContextItemSelected(item)
    }

    private fun posicaoDoItemClicado(menuInfo: ContextMenu.ContextMenuInfo) =
            (menuInfo as AdapterView.AdapterContextMenuInfo).position

    private fun chamaDialogAlteraTransacao(transacao: Transacao, posicao: Int) {
        AlteraTransacaoDialog(this, parent)
            .exibe(transacao) { transacaoAlterada -> altera(transacaoAlterada, posicao) }
    }

    private fun adiciona(transacao: Transacao) {
        dao.adiciona(transacao)
        atualizaTransacoes()
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        dao.altera(transacao, posicao)
        atualizaTransacoes()
    }

    private fun removeTransacaoNa(posicao: Int) {
        dao.remove(posicao)
        atualizaTransacoes()
    }

}
