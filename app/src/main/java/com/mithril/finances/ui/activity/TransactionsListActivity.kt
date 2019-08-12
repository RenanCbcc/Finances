package com.mithril.finances.ui.activity

import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mithril.finances.R
import com.mithril.finances.extentions.formataParaBrasileiro
import com.mithril.finances.models.Tipo
import com.mithril.finances.models.Transacao
import com.mithril.finances.ui.ResumoView
import com.mithril.finances.ui.adapters.ListaTrasacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import kotlinx.android.synthetic.main.transacao_item.view.*
import java.math.BigDecimal
import java.util.*

class TransactionsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transacoes: List<Transacao> = transacoesDeExemplo()

        configuraResumo(transacoes)
        lista_transacoes_listview.adapter = ListaTrasacoesAdapter(transacoes, this)
        lista_transacoes_adiciona_receita
            .setOnClickListener {
                val view: View = window.decorView
                val viewCriada = LayoutInflater.from(this)
                    .inflate(R.layout.form_transacao, view as ViewGroup, false)

                val ano = 2017
                val mes = 9
                val dia = 18

                val hoje = Calendar.getInstance()
                viewCriada.form_transacao_data
                    .setText(hoje.formataParaBrasileiro())
                viewCriada.form_transacao_data
                    .setOnClickListener {
                        DatePickerDialog(this,
                            DatePickerDialog.OnDateSetListener { view, ano, mes, dia ->
                                val dataSelecionada = Calendar.getInstance()
                                dataSelecionada.set(ano, mes, dia)
                                viewCriada.form_transacao_data
                                    .setText(dataSelecionada.formataParaBrasileiro())
                            }
                            , ano, mes, dia)
                            .show()
                    }
                val adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.categorias_de_receita,
                    android.R.layout.simple_dropdown_item_1line
                )
                viewCriada.form_transacao_categoria.adapter = adapter
                AlertDialog.Builder(this)
                    .setView(viewCriada)
                    .setPositiveButton("Adicionar", null)
                    .setNegativeButton("Cancelar", null)
                    .show()

            }
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