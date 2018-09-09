package br.com.dmagdaleno.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.dmagdaleno.financas.R
import kotlinx.android.synthetic.main.activity_formulario_objetivo.*

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
        val objetivo = descricao.text.toString()
        Toast.makeText(this,
                "Salvando objetivo $objetivo", Toast.LENGTH_SHORT).show()
    }
}
