package br.unb.cic.tp1.mh.visitors

import br.unb.cic.tp1.mh.ast._

trait Visitor {

  def visitar(exp: ValorInteiro) : Unit
  def visitar(exp: ValorBooleano) : Unit
  def visitar(exp: ExpSoma) : Unit
  def visitar(exp: ExpSub) : Unit
  def visitar(exp: ExpDiv) : Unit
  def visitar(exp: ExpMult) : Unit
  def visitar(exp: ExpAND) : Unit
  def visitar(exp: ExpOR) : Unit
  def visitar(exp: ExpNOT) :Unit
  def visitar(exp: IfThenElse) : Unit
  def visitar(exp: ExpLet) : Unit
  def visitar(exp: ExpLambda) : Unit
  def visitar(exp: ExpAplicacaoLambda) : Unit
  def visitar(exp: ExpAplicacaoNomeada) : Unit
  def visitar(exp: ExpRef) : Unit
  def visitar(exp: Closure) : Unit

}
