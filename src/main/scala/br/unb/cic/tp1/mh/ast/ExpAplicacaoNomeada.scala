package br.unb.cic.tp1.mh.ast

import br.unb.cic.tp1.mh.memoria.Ambiente
import br.unb.cic.tp1.mh.visitors.Visitor

class ExpAplicacaoNomeada(val nome: String, val argumentoAtual : Array[Expressao]) extends Expressao {
/*Alterei argumentoAtual para um Array de expressões para suporte a múltiplos parâmetros que resultam em
   múltiplas expressões*/

  override def avaliar(): Valor = {
    val decFuncao = Ambiente.recuperarFuncao(nome)

    var index = 0

    Ambiente.novoAmbiente()

      decFuncao.argFormal.foreach(formal => Ambiente.atualiza(formal, argumentoAtual(index).avaliar()))
        index += 1


    val res = decFuncao.corpo.avaliar()

    Ambiente.removeAmbiente()

    return res
  }

  override def verificaTipo: Tipo =
    /*val decFuncao = Ambiente.recuperarFuncao(nome)
    val type1 = decFuncao.corpo.verificaTipo

    var index = 0

    decFuncao.argFormal.foreach(formal => index+=1)
      for(index <- 0){
        if(type1.equals(argumentoAtual(index).verificaTipo)){

        }
      }*/ TErro()


  override def aceitar(v: Visitor): Unit = v.visitar(this)

}
