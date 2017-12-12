package br.unb.cic.tp1.mh.ast

import br.unb.cic.tp1.mh.memoria.Ambiente
import br.unb.cic.tp1.mh.visitors.ContadorVisitor
import org.scalatest._

class TesteContadorVisitor extends  FlatSpec with Matchers{

  behavior of "an application of our visitor"

  it should "be evaluated to 5 when (3+4)+5" in {
    val soma = ExpSoma(ExpSoma(ValorInteiro(3), ValorInteiro(4)),
      ValorInteiro(5))

    val c = new ContadorVisitor()

    soma.aceitar(c)

    c.contador should be (5)
  }

  //Contador com multiplicacao e divisao
  it should "be evaluated to 5 when (6*3)/9" in {
    val exp1 = ExpDiv(ExpMult(ValorInteiro(6), ValorInteiro(3)),
      ValorInteiro(9))

    val c = new ContadorVisitor()

    exp1.aceitar(c)

    c.contador should be (5)
  }

  //Contador com os operadores logicos
  it should "be evaluated to 6 when NOT((true AND false)OR true)" in {
    val exp1 = ExpNOT(ExpOR(ExpAND(ValorBooleano(true), ValorBooleano(false)),
      ValorBooleano(true)))

    val c = new ContadorVisitor()

    exp1.aceitar(c)

    c.contador should be (6)
  }

  //Contador IfThenElse
  it should "be evaluated to 8 when If true Then x+1 Else x-1" in {
    Ambiente.iniciar()
    Ambiente.atualiza("x",ValorInteiro(4))

    val exp1 = new IfThenElse(ValorBooleano(true), ExpSoma(ExpRef("x"),ValorInteiro(1)),
      ExpSub(ExpRef("x"),ValorInteiro(1)))

    val c = new ContadorVisitor()

    exp1.aceitar(c)

    c.contador should be (8)
  }

  //Contador Let
  it should "be evaluated to Valor(20) when let x = 10 in x + x" in {
    Ambiente.iniciar()
    val let  = new ExpLet("x", ValorInteiro(10),
      new ExpSoma(new ExpRef("x"), ExpRef("x")))

    val c = new ContadorVisitor()

    let.aceitar(c)

    c.contador should be (5)
  }

  //Contador ExpLambda
  it should "be evaluated to 4 when (x) -> x + 1" in {
    val inc  = new ExpLambda("x", TInt(),
      new ExpSoma(new ExpRef("x"), ValorInteiro(1)))

    val c = new ContadorVisitor()

    inc.aceitar(c)

    c.contador should be (4)
  }

  //Contador AppNomeada
  it should "be evaluated to 7 when inc2(2,4) " in {
    val inc = new DecFuncao("inc2", Array("x","y"), ExpSoma(ExpSoma(ExpRef("x"), ExpRef("y")), ValorInteiro(1)))

    Ambiente.declararFuncao(inc)

    val app = new ExpAplicacaoNomeada("inc2", Array(ValorInteiro(2), ValorInteiro(4)))

    val c = new ContadorVisitor()

    app.aceitar(c)

    c.contador should be (3)
  }

  //Contador AppLambda
  it should "be evaluated to Valor(6) when ((x) -> x + 1) 5)" in {
    Ambiente.iniciar()
    val inc = new ExpLambda("x", TInt(),new ExpSoma(new ExpRef("x"), ValorInteiro(1)))
    val app = new ExpAplicacaoLambda(inc, ValorInteiro(5))

    val c = new ContadorVisitor()

    app.aceitar(c)

    c.contador should be (6)
  }

}