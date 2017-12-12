package br.unb.cic.tp1.mh.ast

import br.unb.cic.tp1.mh.visitors.Visitor
import br.unb.cic.tp1.exceptions.DividendoZero

//Expressão de divisão, criação de nova exceção caso divida por 0
case class ExpDiv(lhs:Expressao, rhs:Expressao) extends Expressao{

  override def avaliar(): Valor = {
    val v1 = lhs.avaliar().asInstanceOf[ValorInteiro]
    val v2 = rhs.avaliar().asInstanceOf[ValorInteiro]

    try ValorInteiro(v1.valor / v2.valor)

    catch {
      case ex: ArithmeticException => throw DividendoZero()
    }
  }

  override def verificaTipo: Tipo = {
    val t1 = lhs.verificaTipo
    val t2 = rhs.verificaTipo

    if(t1 == TInt() && t2 == TInt())
      return TInt()

    return TErro()
  }

  override def aceitar(v: Visitor): Unit = v.visitar(this)
}