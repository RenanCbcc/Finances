package com.mithril.finances.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mithril.finances.R
import com.mithril.finances.delegate.TransacaoDelegate
import com.mithril.finances.extentions.converterParaCalendar
import com.mithril.finances.extentions.formataParaBrasileiro
import com.mithril.finances.models.Tipo
import com.mithril.finances.models.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

abstract class FormularioTransacaoDialog(private val context: Context, private val viewGroup: ViewGroup?) {
    private val viewCriada = criaLayout()
    protected val campoValor = viewCriada.form_transacao_valor
    protected val campoData = viewCriada.form_transacao_data
    protected val campoCategoria = viewCriada.form_transacao_categoria
    abstract val tituloBotaoPositivo: String

    protected abstract fun tituloPor(tipo: Tipo): Int


    fun chama(tipo: Tipo, transacaoDelegate: TransacaoDelegate) {

        configuraData()
        configuraCategoria(tipo)
        configuraFormulario(tipo, transacaoDelegate)
    }

    private fun configuraFormulario(tipo: Tipo, transacaoDelegate: TransacaoDelegate) {
        val titulo = tituloPor(tipo)
        AlertDialog.Builder(context)
            .setTitle(titulo)
            .setView(viewCriada)
            .setPositiveButton(tituloBotaoPositivo) { _, _ ->
                val valorEmTexto = campoValor.text.toString()
                val dataEmTexto = campoData.text.toString()
                val categoriaEmTexto = campoCategoria.selectedItem.toString()
                val valor = coverteValor(valorEmTexto)
                val data = dataEmTexto.converterParaCalendar()

                val transacao = Transacao(
                    tipo = tipo,
                    valor = valor,
                    data = data,
                    categoria = categoriaEmTexto
                )

                transacaoDelegate.delegate(transacao)

            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun configuraCategoria(tipo: Tipo) {
        val categorias = categoriasPor(tipo)
        val adapter = ArrayAdapter.createFromResource(
            context,
            categorias,
            android.R.layout.simple_dropdown_item_1line
        )
        campoCategoria.adapter = adapter
    }

    protected fun categoriasPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) {
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }
    }

    private fun configuraData() {
        val hoje = Calendar.getInstance()
        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        campoData
            .setText(hoje.formataParaBrasileiro())
        campoData
            .setOnClickListener {
                DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { _, ano, mes, dia ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(ano, mes, dia)
                        campoData
                            .setText(dataSelecionada.formataParaBrasileiro())
                    }
                    , ano, mes, dia)
                    .show()
            }
    }

    private fun criaLayout(): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.form_transacao, viewGroup, false)

    }

    private fun coverteValor(valorEmTexto: String): BigDecimal {
        return try {
            BigDecimal(valorEmTexto)
        } catch (exceptin: NumberFormatException) {
            Toast.makeText(context, "Erro no valor inserido.", Toast.LENGTH_LONG)
                .show()
            BigDecimal.ZERO
        }
    }
}