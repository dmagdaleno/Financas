package br.com.dmagdaleno.financas.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.dmagdaleno.financas.extension.converteEmCalendar
import br.com.dmagdaleno.financas.extension.formatada
import br.com.dmagdaleno.financas.model.Objetivo
import br.com.dmagdaleno.financas.model.Transacao
import java.math.BigDecimal

class DAO(context: Context):
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val DB_NAME = "financas_db"
        private val DB_VERSION = 1
        private val TBL_OBJETIVO = "objetivo"
        private val TBL_TRANSACAO = "transacao"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val tabelaObjetivo =
            "CREATE TABLE $TBL_OBJETIVO (" +
                "id INTEGER PRIMARY KEY NOT NULL, " +
                "valor_total REAL NOT NULL, " +
                "valor_atual REAL NOT NULL, " +
                "valor_inicial REAL NOT NULL, " +
                "data_final TEXT NOT NULL, " +
                "descricao TEXT NOT NULL);"
        db.execSQL(tabelaObjetivo)

        val tabelaTransacao =
            "CREATE TABLE $TBL_TRANSACAO (" +
                "id INTEGER PRIMARY KEY NOT NULL, " +
                "valor REAL NOT NULL, " +
                "categoria TEXT NOT NULL, " +
                "tipo TEXT NOT NULL, " +
                "data TEXT NOT NULL, " +
                "id_objetivo INTEGER, " +
                "FOREIGN KEY (id_objetivo) REFERENCES objetivo(id));"
        db.execSQL(tabelaTransacao)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Sem migrações por enquanto
    }

    fun salva(objetivo: Objetivo) {
        val db = writableDatabase
        val dados = extraiDados(objetivo)
        db.insert(TBL_OBJETIVO, null, dados)
        db.close()

    }

    private fun extraiDados(objetivo: Objetivo): ContentValues {
        val dados = ContentValues()

        if(objetivo.id != null)
            dados.put("id", objetivo.id)

        dados.put("valor_total", objetivo.valorTotal.toDouble())
        dados.put("valor_atual", objetivo.valorAtual.toDouble())
        dados.put("valor_inicial", objetivo.valorInicial.toDouble())
        dados.put("data_final", objetivo.dataFinal.formatada())
        dados.put("descricao", objetivo.descricao)
        return dados
    }

    fun objetivos(): List<Objetivo> {
        val sql = "SELECT * FROM $TBL_OBJETIVO;"
        val db = readableDatabase
        val cursor = db.rawQuery(sql, null)
        val objetivos = populaObjetivos(cursor)
        cursor.close()

        return objetivos

    }

    private fun populaObjetivos(c: Cursor): List<Objetivo> {
        val objetivos = ArrayList<Objetivo>()
        while (c.moveToNext()) {
            val id = c.getLong(c.getColumnIndex("id"))
            val valorTotal = BigDecimal(c.getDouble(c.getColumnIndex("valor_total")))
            val valorAtual = BigDecimal(c.getDouble(c.getColumnIndex("valor_atual")))
            val valorInicial = BigDecimal(c.getDouble(c.getColumnIndex("valor_inicial")))
            val dataFinal = c.getString(c.getColumnIndex("data_final")).converteEmCalendar()
            val descricao = c.getString(c.getColumnIndex("descricao"))
            val objetivo = Objetivo(id, valorTotal, valorAtual, valorInicial, dataFinal, descricao)
            objetivos.add(objetivo)
        }
        return objetivos
    }

    fun salva(transacao: Transacao) {

    }
}