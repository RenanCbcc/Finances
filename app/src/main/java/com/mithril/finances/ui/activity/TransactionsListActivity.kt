package com.mithril.finances.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mithril.finances.R
import com.mithril.finances.models.Transacao
import com.mithril.finances.ui.adapters.ListaTrasacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class TransactionsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transacoes = listOf(
            Transacao(
                BigDecimal(20.5), "Comida", Calendar.getInstance()
            ), Transacao(BigDecimal(100.0), "Economia", Calendar.getInstance())
        )

        lista_transacoes_listview.setAdapter(ListaTrasacoesAdapter(transacoes, this))
    }
}