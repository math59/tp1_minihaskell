package br.unb.cic.tp1.mh.ast

import org.scalatest._

class TesteExpSub extends FlatSpec with Matchers  {

  //Matematica
  "An integer value 9 - an integer value 3" should "be evaluated to Valor(6)" in {
    val val9  = ValorInteiro(9)
    val val3 = ValorInteiro(3)

    val sub = ExpSub(val9, val3)

    sub.avaliar() should be (ValorInteiro(6))
  }

  //Tipo correto
  "An integer value 7 - an integer value 2" should "return type TInt()" in {
    val val7  = ValorInteiro(7)
    val val2 = ValorInteiro(2)

    val sub = ExpSub(val7, val2)

    sub.verificaTipo should be (TInt())
  }

  //Tipo incorreto
  "An integer value 7 - a boolean" should "return type TErro()" in {
    val val7  = ValorInteiro(7)
    val bool = ValorBooleano(false)

    val sub = ExpSub(val7, bool)

    sub.verificaTipo should be (TErro())
  }
}