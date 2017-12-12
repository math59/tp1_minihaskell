package br.unb.cic.tp1.mh.ast

import org.scalatest._

class TesteExpNOT extends FlatSpec with Matchers{

  //Logica
  "A boolean value true when applied NOT" should "be evaluated to ValorBooleano(false)" in {
    val bool  = ValorBooleano(true)

    val not = ExpNOT(bool)

    not.avaliar() should be (ValorBooleano(false))
  }

  //Tipo correto
  "A boolean value true when applied NOT" should "return type TBool()" in {
    val bool  = ValorBooleano(true)

    val not = ExpNOT(bool)

    not.verificaTipo should be (TBool())
  }

  //Tipo incorreto
  "A integer value 6 when applied NOT" should "return type TErro()" in {
    val val6  = ValorInteiro(6)

    val not = ExpNOT(val6)

    not.verificaTipo should be (TErro())
  }
}
