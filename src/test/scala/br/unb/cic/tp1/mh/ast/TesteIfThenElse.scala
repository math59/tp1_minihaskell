package br.unb.cic.tp1.mh.ast

import org.scalatest.{FlatSpec, Matchers}

class TesteIfThenElse extends FlatSpec with Matchers {
  behavior of "Expression IfThenElse"

  it should "be evaluated to Valor(12) on the expression If (condition) Then 12 Else 3 when condition is true" in {
    val ITE = new IfThenElse(ValorBooleano(true), ValorInteiro(12), ValorInteiro(3))

    ITE.avaliar() should be(ValorInteiro(12))
  }

  it should "be evaluated to Valor(3) on the expression If (condition) Then 12 Else 3 when condition is false" in {
    val ITE = new IfThenElse(ValorBooleano(false), ValorInteiro(12), ValorInteiro(3))

    ITE.avaliar() should be(ValorInteiro(3))
  }

  //Verificando tipos
  it should "return the expression type TInt() when both Then and Else share same type" in {
    val ITE = new IfThenElse(ValorBooleano(true), ValorInteiro(12), ValorInteiro(3))

    ITE.verificaTipo should be(TInt())
  }

  it should "return the expression type TBool() when both Then and Else share same type" in {
    val ITE = new IfThenElse(ValorBooleano(true), ValorBooleano(false), ValorBooleano(true))

    ITE.verificaTipo should be(TBool())
  }

  it should "return the expression type TErro() when Then and Else have different types" in {
    val ITE = new IfThenElse(ValorBooleano(true), ValorInteiro(3), ValorBooleano(true))

    ITE.verificaTipo should be(TErro())
  }

  it should "return the expression type TErro() when If condition is not a boolean" in {
    val ITE = new IfThenElse(ValorInteiro(3), ValorBooleano(true), ValorBooleano(false))

    ITE.verificaTipo should be(TErro())
  }
}