package br.unb.cic.tp1.mh.ast

import org.scalatest._

class TestExpOR extends FlatSpec with Matchers{

  //Logica
  "A boolean value true OR a boolean value false" should "be evaluated to ValorBooleano(true)" in {
    val bool1  = ValorBooleano(true)
    val bool2 = ValorBooleano(false)

    val or = ExpOR(bool1, bool2)

    or.avaliar() should be (ValorBooleano(true))
  }

  "A boolean value false OR a boolean value false" should "be evaluated to ValorBooleano(false)" in {
    val bool1  = ValorBooleano(false)
    val bool2 = ValorBooleano(false)

    val or = ExpOR(bool1, bool2)

    or.avaliar() should be (ValorBooleano(false))
  }

  //Tipo correto
  "A boolean value true OR a boolean value false" should "return type TBool()" in {
    val bool1  = ValorBooleano(true)
    val bool2 = ValorBooleano(false)

    val or = ExpOR(bool1, bool2)

    or.verificaTipo should be (TBool())
  }

  //Tipo incorreto
  "A boolean value true OR an integer value 5" should "return type TErro()" in {
    val bool1  = ValorBooleano(true)
    val val5 = ValorInteiro(5)

    val or = ExpOR(bool1, val5)

    or.verificaTipo should be (TErro())
  }
}
