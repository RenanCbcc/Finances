package com.mithril.finances.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.mithril.finances.R
import com.mithril.finances.delegate.TransacaoDelegate
import com.mithril.finances.models.Tipo
import com.mithril.finances.models.Transacao
import com.mithril.finances.ui.ResumoView
import com.mithril.finances.ui.adapters.ListaTrasacoesAdapter
import com.mithril.finances.ui.dialog.AdicionaTransacaoDialog
import com.mithril.finances.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()
    private val viewDaActivity by lazy { window.decorView as ViewGroup }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)


        configuraResumo()
        configuraLista()
        configuraFAB()
    }

    private fun configuraFAB() {
        lista_transacoes_adiciona_receita
            .setOnClickListener {
                chamaDialogDeAdicao(Tipo.RECEITA)
            }

        lista_transacoes_adiciona_despesa
            .setOnClickListener {
                chamaDialogDeAdicao(Tipo.DESPESA)
            }
    }


    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraLista() {
        with(lista_transacoes_listview) {
            adapter = ListaTrasacoesAdapter(transacoes, this@ListaTransacoesActivity)
            setOnItemClickListener { _, _, posicao, _ ->
                val transacao = transacoes[posicao]
                chamaDialogDeAlteracao(transacao, posicao)

            }
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.itemId == 1) {
            val adapterMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val position = adapterMenuInfo.position
            transacoes.removeAt(position)
            atualizaTransacoes()
        }
        return super.onContextItemSelected(item)

    }

    private fun chamaDialogDeAlteracao(transacao: Transacao, posicao: Int) {
        AlteraTransacaoDialog(viewDaActivity, this)
            .chama(transacao, TransacaoDelegate { transacao ->
                transacoes[posicao] = transacao
                atualizaTransacoes()
            })
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewDaActivity, this)
            .chama(tipo, TransacaoDelegate { transacao ->
                transacoes.add(transacao)
                atualizaTransacoes()
                lista_transacoes_adiciona_menu.close(true)

            })
    }


    private fun configuraResumo() {
        var resumoView = ResumoView(transacoes, viewDaActivity, this)
        resumoView.atualiza()
    }


}