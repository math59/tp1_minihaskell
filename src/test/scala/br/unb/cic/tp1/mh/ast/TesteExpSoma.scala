package br.unb.cic.tp1.mh.ast

import org.scalatest._

class TesteExpSoma extends FlatSpec with Matchers  {

  //Matematica
  "An integer value 5 + an integer value 10" should "be evaluated to Valor(15)" in {
    val val5  = ValorInteiro(5)
    val val10 = ValorInteiro(10)

    val soma = ExpSoma(val5, val10)

    soma.avaliar() should be (ValorInteiro(15))
  }

  //Tipo correto
  "An integer value 8 + an integer value 2" should "return TInt()" in {
    val val8  = ValorInteiro(8)
    val val2 = ValorInteiro(2)

    val soma = ExpSoma(val8, val2)

    soma.verificaTipo should be (TInt())
  }

  //Tipo incorreto
  "An integer value 3 + a boolean" should "return TErro()" in {
    val val3  = ValorInteiro(3)
    val bool = ValorBooleano(true)

    val soma = ExpSoma(val3, bool)

    soma.verificaTipo should be (TErro())
  }

}
