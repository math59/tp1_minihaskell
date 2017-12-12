package br.unb.cic.tp1.mh.ast

import br.unb.cic.tp1.mh.memoria.Ambiente
import org.scalatest._

class TesteAplicacaoNomeada  extends FlatSpec with Matchers {

  behavior of "a named function def inc2(x,y) = x + y + 1"

  it should "be evaluated to 7 when inc2(2,4) " in {
    val inc = new DecFuncao("inc2", Array("x","y"), ExpSoma(ExpSoma(ExpRef("x"), ExpRef("y")), ValorInteiro(1)))

    Ambiente.declararFuncao(inc)

    val app = new ExpAplicacaoNomeada("inc2", Array(ValorInteiro(2), ValorInteiro(4)))

    app.avaliar() should be (ValorInteiro(7))

  }
}
