package com.mithril.finances.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.mithril.finances.R
import com.mithril.finances.extentions.formataParaBrasileiro
import com.mithril.finances.models.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTrasacoesAdapter(private val transacoes: List<Transacao>, private val context: Context) : BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, p2, false)
        val transacao = transacoes[p0]
        view.transacao_valor.text = transacao.valor.toString()
        view.transacao_categoria.text = transacao.categoria
        view.transacao_data.text = transacao.data.formataParaBrasileiro()
        return view
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

