package com.mithril.finances.models

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {
    val receita get() = somaPor(Tipo.RECEITA)


    val despesa get() = somaPor(Tipo.DESPESA)


    val total get() = receita.subtract(despesa)

    fun somaPor(tipo: Tipo): BigDecimal {
        val somaporTipo = transacoes
            .filter { it.tipo == tipo }
            .sumByDouble { it.valor.toDouble() }

        return BigDecimal(somaporTipo)
    }


}