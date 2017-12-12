package br.unb.cic.tp1.mh.ast

import br.unb.cic.tp1.mh.memoria.Ambiente
import br.unb.cic.tp1.mh.visitors.PPVisitor
import org.scalatest._


class TestePPVisitor extends  FlatSpec with Matchers{

  behavior of "an application of our visitor"

  it should "be evaluated to ((3 + 4) + 5) when (3 + 4) + 5" in {
    val soma = ExpSoma(ExpSoma(ValorInteiro(3), ValorInteiro(4)),
                                ValorInteiro(5))

    val c = new PPVisitor()

    soma.aceitar(c)

    println(c.sb.toString)
    
    c.sb.toString should be ("((3 + 4) + 5)")
  }

  //PP Mult, Div e Sub
  it should "be evaluated to (((6 - 4) * 3) / 6) when ((6 - 4) * 3) / 6" in {
    val exp1 = ExpDiv(ExpMult(ExpSub(ValorInteiro(6), ValorInteiro(4)), ValorInteiro(3)), ValorInteiro(6))

    val c = new PPVisitor()

    exp1.aceitar(c)

    println(c.sb.toString)

    c.sb.toString should be ("(((6 - 4) * 3) / 6)")
  }

  //PP expressoes logicas
  it should "be evaluated to NOT (((true AND false) OR true)) when NOT((true AND false)OR true)" in {
    val bool = ExpNOT(ExpOR(ExpAND(ValorBooleano(true), ValorBooleano(false)),
      ValorBooleano(true)))

    val c = new PPVisitor()

    bool.aceitar(c)

    println(c.sb.toString)

    c.sb.toString should be ("NOT (((true AND false) OR true))")
  }

  //PP Let
  it should "be evaluated to Let x = 2 in Let y = (x + 2) in (y + 1) when Let x = 2 in Let y = x + 2 in y + 1" in {
    val let = new ExpLet("x", ValorInteiro(2), new ExpLet("y", ExpSoma(ExpRef("x"), ValorInteiro(2)),
      ExpSoma(ExpRef("y"), ValorInteiro(1))))

    val c = new PPVisitor()

    let.aceitar(c)

    println(c.sb.toString)

    c.sb.toString should be ("Let x = 2 in Let y = (x + 2) in (y + 1)")
  }

  //PP IfThenElse
  it should "be evaluated to If (true) Then ((x + 1)) Else ((x - 1)) when If true Then x + 1 Else x - 1" in {
    Ambiente.iniciar()
    Ambiente.atualiza("x",ValorInteiro(4))

    val ITE = new IfThenElse(ValorBooleano(true), ExpSoma(ExpRef("x"),ValorInteiro(1)),
      ExpSub(ExpRef("x"),ValorInteiro(1)))

    val c = new PPVisitor()

    ITE.aceitar(c)

    println(c.sb.toString)

    c.sb.toString should be ("If (true) Then ((x + 1)) Else ((x - 1))")
  }

  //PP ExpLambda
  it should "be evaluated to Closure(x,(x + 3)) when (x) -> x + 3" in {
    val clos = new ExpLambda("x", TInt(), new ExpSoma(new ExpRef("x"), ValorInteiro(3)))

    val c = new PPVisitor()

    clos.aceitar(c)

    println(c.sb.toString)

    c.sb.toString should be ("Closure(x,(x + 3))")
  }

  //PP Aplicacao Lambda
  it should "be evaluated to (Closure(x,(x + 1)))5 when ((x) -> x + 1) 5)" in {
    val inc = new ExpLambda("x", TInt(),new ExpSoma(new ExpRef("x"), ValorInteiro(1)))
    val appL = new ExpAplicacaoLambda(inc, ValorInteiro(5))

    val c = new PPVisitor()

    appL.aceitar(c)

    println(c.sb.toString)

    c.sb.toString should be ("(Closure(x,(x + 1)))5")
  }

  //PP Aplicacao Nomeada
  it should "be evaluated to inc2(2,4) when inc2(2,4) " in {
    val inc = new DecFuncao("inc2", Array("x","y"), ExpSoma(ExpSoma(ExpRef("x"), ExpRef("y")), ValorInteiro(1)))

    Ambiente.declararFuncao(inc)

    val appN = new ExpAplicacaoNomeada("inc2", Array(ValorInteiro(2), ValorInteiro(4)))

    val c = new PPVisitor()

    appN.aceitar(c)

    println(c.sb.toString)

    c.sb.toString should be ("inc2(x,y) = ((x + y) + 1)")
  }

}
