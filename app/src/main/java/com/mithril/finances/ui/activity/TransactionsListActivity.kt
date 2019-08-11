package com.mithril.finances.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mithril.finances.R
import com.mithril.finances.models.Tipo
import com.mithril.finances.models.Transacao
import com.mithril.finances.ui.ResumoView
import com.mithril.finances.ui.adapters.ListaTrasacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class TransactionsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transacoes: List<Transacao> = transacoesDeExemplo()

        configuraResumo(transacoes)
        lista_transacoes_listview.adapter = ListaTrasacoesAdapter(transacoes, this)
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        //var textView = findViewById<TextView>(R.id.resumo_card_receita)
        var view: View = window.decorView
        var resumoView = ResumoView(transacoes, view, this)
        resumoView.atualiza()
    }


    private fun transacoesDeExemplo(): List<Transacao> {
        return listOf(
            Transacao(BigDecimal(20.5), "Comida", Tipo.DESPESA)
            , Transacao(tipo = Tipo.DESPESA, data = Calendar.getInstance(), valor = BigDecimal(20.5))
            , Transacao(valor = BigDecimal(100.0), categoria = "Economia", tipo = Tipo.RECEITA)
        )
    }
}