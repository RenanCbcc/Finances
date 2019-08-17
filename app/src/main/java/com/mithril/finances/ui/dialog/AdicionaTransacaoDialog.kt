package com.mithril.finances.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.mithril.finances.R
import com.mithril.finances.models.Tipo

class AdicionaTransacaoDialog(private val viewGroup: ViewGroup, private val context: Context) :
    FormularioTransacaoDialog(context, viewGroup) {

    override val tituloBotaoPositivo: String
        get() = "Adicionar"

    override fun tituloPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) {
            R.string.adicionar_receita
        } else {
            R.string.adicionar_despesa
        }
    }


}