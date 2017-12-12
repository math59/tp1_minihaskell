package br.unb.cic.tp1.mh.ast

import org.scalatest._

class TesteExpMult extends FlatSpec with Matchers  {

  //Matematica
  "An integer value 5 * an integer value 4" should "be evaluated to Valor(20)" in {
    val val5  = ValorInteiro(5)
    val val4 = ValorInteiro(4)

    val mult = ExpMult(val5, val4)

    mult.avaliar() should be (ValorInteiro(20))
  }

  //Tipo correto
  "An integer value 8 * an integer value 4" should "return type TInt()" in {
    val val8  = ValorInteiro(8)
    val val4 = ValorInteiro(4)

    val mult = ExpMult(val8, val4)

    mult.verificaTipo should be (TInt())
  }

  //Tipo incorreto
  "An integer value 5 * a boolean" should "return type TErro()" in {
    val val5  = ValorInteiro(5)
    val bool = ValorBooleano(false)

    val mult = ExpMult(val5, bool)

    mult.verificaTipo should be (TErro())
  }
}