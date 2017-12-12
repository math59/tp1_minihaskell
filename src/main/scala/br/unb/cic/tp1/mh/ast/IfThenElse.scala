package br.unb.cic.tp1.mh.ast
import br.unb.cic.tp1.mh.visitors.Visitor

//Expressão IfThenElse
class IfThenElse(val If: Expressao, val Then: Expressao, val Else: Expressao) extends Expressao {
  override def avaliar(): Valor = {//Se condição do If for verdadeiro ele avalia o Then, caso contrário avalia o Else
    if (If.avaliar().asInstanceOf[ValorBooleano].valor)
      Then.avaliar()
    else
      Else.avaliar()
  }

  override def verificaTipo: Tipo = {//Retorna o tipo da expressão Then, que deve ser igual ao tipo do Else
    val type1 = If.verificaTipo         //e If deve ser booleano, caso contrário há erro no tipo.
    val type2 = Then.verificaTipo
    val type3 = Else.verificaTipo

    if (type1 == TBool() && type2 == type3) {
      return type2
    }
    TErro()
  }

  override def aceitar(v: Visitor): Unit = v.visitar(this)

}