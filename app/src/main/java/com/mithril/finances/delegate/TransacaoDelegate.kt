package com.mithril.finances.delegate

import com.mithril.finances.models.Transacao

interface TransacaoDelegate {

    fun delegate(transacao: Transacao)

}