package br.unb.cic.tp1.mh.ast

import org.scalatest._

class TesteExpAND extends FlatSpec with Matchers{

  //Logica
  "A boolean value true AND a boolean value true" should "be evaluated to ValorBooleano(true)" in {
    val bool1  = ValorBooleano(true)
    val bool2 = ValorBooleano(true)

    val and = ExpAND(bool1, bool2)

    and.avaliar() should be (ValorBooleano(true))
  }

  "A boolean value true AND a boolean value false" should "be evaluated to ValorBooleano(false)" in {
    val bool1  = ValorBooleano(true)
    val bool2 = ValorBooleano(false)

    val and = ExpAND(bool1, bool2)

    and.avaliar() should be (ValorBooleano(false))
  }

  //Tipo correto
  "A boolean value true AND a boolean value false" should "return type TBool()" in {
    val bool1  = ValorBooleano(true)
    val bool2 = ValorBooleano(false)

    val and = ExpAND(bool1, bool2)

    and.verificaTipo should be (TBool())
  }

  //Tipo incorreto
  "A boolean value true AND an integer value 3" should "return type TErro()" in {
    val bool1  = ValorBooleano(true)
    val val3 = ValorInteiro(3)

    val and = ExpAND(bool1, val3)

    and.verificaTipo should be (TErro())
  }
}
