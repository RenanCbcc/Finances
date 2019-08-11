package com.mithril.finances.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.mithril.finances.R
import com.mithril.finances.extentions.formataParaBrasileiro
import com.mithril.finances.models.Resumo
import com.mithril.finances.models.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(transacoes: List<Transacao>, private val view: View, context: Context) {

    private val resumo = Resumo(transacoes)

    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)
    private val corRecita = ContextCompat.getColor(context, R.color.receita)

    fun atualiza() {
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaReceita() {
        val totalReceita = resumo.receita
        with(view.resumo_card_receita) {
            setTextColor(corRecita)
            text = totalReceita.formataParaBrasileiro()
        }
    }


    private fun adicionaDespesa() {
        val totalDespesa = resumo.despesa

        with(view.resumo_card_despesa) {
            setTextColor(corDespesa)
            text = totalDespesa.formataParaBrasileiro()
        }
    }

    private fun adicionaTotal() {
        val total = resumo.total
        val cor = corPor(total)
        with(view.resumo_card_total) {
            setTextColor(cor)
            text = total.formataParaBrasileiro()
        }
    }

    private fun corPor(valor: BigDecimal): Int {
        if (valor >= BigDecimal(0.0)) {
            return corRecita
        }
        return corDespesa
    }


}