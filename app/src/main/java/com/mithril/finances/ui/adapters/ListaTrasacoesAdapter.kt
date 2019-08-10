package com.mithril.finances.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.mithril.finances.R
import com.mithril.finances.extentions.formataParaBrasileiro
import com.mithril.finances.extentions.limitaEmAte
import com.mithril.finances.models.Tipo
import com.mithril.finances.models.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*
import java.text.DecimalFormat
import java.util.*

class ListaTrasacoesAdapter(private val transacoes: List<Transacao>, private val context: Context) : BaseAdapter() {

    private val limiteDaCategoria = 14

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context)
            .inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[posicao]

        adicionaValor(transacao, viewCriada)
        adicionaIcone(transacao, viewCriada)
        adicionaCategoria(viewCriada, transacao)
        adicionaData(viewCriada, transacao)

        return viewCriada
    }

    private fun adicionaData(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_data.text = transacao.data
            .formataParaBrasileiro()
    }

    private fun adicionaCategoria(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_categoria.text = transacao.categoria
            .limitaEmAte(limiteDaCategoria)
    }

    private fun adicionaIcone(transacao: Transacao, viewCriada: View) {
        if (transacao.tipo == Tipo.RECEITA) {
            viewCriada.transacao_icone
                .setBackgroundResource(R.drawable.icone_transacao_item_receita)
        } else {
            viewCriada.transacao_icone
                .setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        }
    }

    private fun adicionaValor(transacao: Transacao, viewCriada: View) {
        if (transacao.tipo == Tipo.RECEITA) {
            viewCriada.transacao_valor
                .setTextColor(ContextCompat.getColor(context, R.color.receita))
        } else {
            viewCriada.transacao_valor
                .setTextColor(ContextCompat.getColor(context, R.color.despesa))
        }
        viewCriada.transacao_valor.text = transacao.valor
            .formataParaBrasileiro()
    }

    override fun getItem(p0: Int): Transacao {
        return transacoes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }


}

