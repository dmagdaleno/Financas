package br.com.dmagdaleno.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.dmagdaleno.financas.R
import br.com.dmagdaleno.financas.dao.DAO
import br.com.dmagdaleno.financas.extension.converteEmCalendar
import br.com.dmagdaleno.financas.model.Objetivo
import kotlinx.android.synthetic.main.activity_formulario_objetivo.*
import java.math.BigDecimal

class FormularioObjetivoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_objetivo)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_objetivo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_salvar_objetivo -> salvaObjetivo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun salvaObjetivo() {
        val objetivo = constroiObjetivo()
        Toast.makeText(this,
                "Salvando objetivo ${objetivo.descricao}", Toast.LENGTH_SHORT).show()

        val dao = DAO(this)
        dao.salva(objetivo)
    }

    private fun constroiObjetivo() =
            Objetivo(
                valorTotal = BigDecimal(valor_total.text.toString()),
                valorAtual = BigDecimal(valor_total.text.toString()),
                valorInicial = BigDecimal(valor_total.text.toString()),
                dataFinal = data_final.text.toString().converteEmCalendar(),
                descricao = descricao.text.toString())
}
