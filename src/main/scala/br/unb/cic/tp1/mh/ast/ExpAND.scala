package br.unb.cic.tp1.mh.ast

import br.unb.cic.tp1.mh.visitors.Visitor


//Expressão booleana AND
case class ExpAND(lhs:Expressao, rhs:Expressao) extends Expressao{
  override def avaliar(): Valor = {
    val v1 = lhs.avaliar().asInstanceOf[ValorBooleano]
    val v2 = rhs.avaliar().asInstanceOf[ValorBooleano]

    return ValorBooleano(v1.valor && v2.valor)
  }

  override def verificaTipo: Tipo = {
    val t1 = lhs.verificaTipo
    val t2 = rhs.verificaTipo

    if(t1 == TBool() && t2 == TBool())
      return TBool()

    return TErro()
  }

  override def aceitar(v: Visitor): Unit = v.visitar(this)
}