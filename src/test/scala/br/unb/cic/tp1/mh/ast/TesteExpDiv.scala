package br.unb.cic.tp1.mh.ast

import br.unb.cic.tp1.exceptions.DividendoZero
import org.scalatest._

class TesteExpDiv extends FlatSpec with Matchers  {

  //Matematica
  "An integer value 12 / an integer value 3" should "be evaluated to Valor(4)" in {
    val val12  = ValorInteiro(12)
    val val3 = ValorInteiro(3)

    val div = ExpDiv(val12, val3)

    div.avaliar() should be (ValorInteiro(4))
  }

  //Tipo correto
  "An integer value 8 / an integer value 2" should "return type TInt()" in {
    val val8  = ValorInteiro(8)
    val val2 = ValorInteiro(2)

    val div = ExpSub(val8, val2)

    div.verificaTipo should be (TInt())
  }

  //Tipo incorreto
  "An integer value 10 / a boolean" should "return type TErro()" in {
    val val10  = ValorInteiro(10)
    val bool = ValorBooleano(false)

    val div = ExpSub(val10, bool)

    div.verificaTipo should be (TErro())
  }

  //Divisao por zero
  "An integer value 5 / an integer value 0" should "throw DividendoZero error" in {
    val val5  = ValorInteiro(5)
    val val0 = ValorInteiro(0)

    val div = ExpDiv(val5, val0)

    intercept[DividendoZero] {
      div.avaliar()
    }
  }
}
