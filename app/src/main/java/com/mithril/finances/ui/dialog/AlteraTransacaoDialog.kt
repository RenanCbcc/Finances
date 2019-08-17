package com.mithril.finances.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.mithril.finances.R
import com.mithril.finances.delegate.TransacaoDelegate
import com.mithril.finances.extentions.formataParaBrasileiro
import com.mithril.finances.models.Tipo
import com.mithril.finances.models.Transacao

class AlteraTransacaoDialog(viewGroup: ViewGroup, private val context: Context) :
    FormularioTransacaoDialog(context, viewGroup) {
    override val tituloBotaoPositivo: String
        get() = "Alterar"

    override fun tituloPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) {
            R.string.alterar_receita
        } else {
            R.string.alterar_despesa
        }
    }

    fun chama(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {
        val tipo = transacao.tipo
        super.chama(tipo, transacaoDelegate)
        inicializaCampos(transacao)
    }

    private fun inicializaCampos(transacao: Transacao) {
        val tipo = transacao.tipo
        campoValor.setText(transacao.valor.toString())
        campoData.setText(transacao.data.formataParaBrasileiro())
        val categoriasRetornadas = context.resources.getStringArray(categoriasPor(tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

}