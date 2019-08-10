package com.mithril.finances.ui

import android.view.View
import com.mithril.finances.extentions.formataParaBrasileiro
import com.mithril.finances.models.Resumo
import com.mithril.finances.models.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(transacoes: List<Transacao>, private val view: View) {

    private val resumo = Resumo(transacoes)

    fun adicionaReceita() {
        val totalReceita = resumo.receita()
        view.resumo_card_receita.text = totalReceita.formataParaBrasileiro()
    }

    fun adicionaDespesa() {
        val totalDespesa = resumo.despesa()
        view.resumo_card_despesa.text = totalDespesa.formataParaBrasileiro()
    }

    fun adicionaTotal() {
        var total = resumo.total()
        view.resumo_card_total.text = total.formataParaBrasileiro()
    }


}