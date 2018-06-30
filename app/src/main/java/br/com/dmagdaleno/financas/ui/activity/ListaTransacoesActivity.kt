package br.com.dmagdaleno.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.dmagdaleno.financas.R
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<String> = listOf("Gasto - R$100", "Lucro - R$1025,10")
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, transacoes)

        lista_transacoes_listview.adapter = adapter
    }
}