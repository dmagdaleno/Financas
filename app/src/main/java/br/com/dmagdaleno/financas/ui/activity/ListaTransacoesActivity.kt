package br.com.dmagdaleno.financas.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.delegate.AdicionaTransacaoDelegate
import br.com.dmagdaleno.financas.extension.formatada
import br.com.dmagdaleno.financas.model.Tipo
import br.com.dmagdaleno.financas.model.Transacao
import br.com.dmagdaleno.financas.ui.ResumoView
import br.com.dmagdaleno.financas.ui.adapter.ListaTransacoesAdapter
import br.com.dmagdaleno.financas.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.util.*

class ListaTransacoesActivity: AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        atualizaTransacoes(null)

        lista_transacoes_adiciona_receita.setOnClickListener{
            AdicionaTransacaoDialog(this, window.decorView as ViewGroup)
                .configuraDialog(Tipo.RECEITA, object: AdicionaTransacaoDelegate {
                    override fun finaliza(transacao: Transacao) {
                        atualizaTransacoes(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
        }

        lista_transacoes_adiciona_despesa.setOnClickListener{
            AdicionaTransacaoDialog(this, window.decorView as ViewGroup)
                .configuraDialog(Tipo.DESPESA, object: AdicionaTransacaoDelegate {
                    override fun finaliza(transacao: Transacao) {
                        atualizaTransacoes(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
        }
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