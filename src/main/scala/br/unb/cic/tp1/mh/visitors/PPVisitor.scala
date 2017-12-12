package br.unb.cic.tp1.mh.visitors
import br.unb.cic.tp1.mh.ast._
import br.unb.cic.tp1.mh.memoria.Ambiente

class PPVisitor extends Visitor {

  val sb = new StringBuilder("")

  override def visitar(exp: ValorInteiro): Unit = sb.append(exp.valor.toString)


  override def visitar(exp: ValorBooleano): Unit = sb.append(exp.valor.toString)


  override def visitar(exp: ExpSoma): Unit = {
    sb += '('
    exp.lhs.aceitar(this)
    sb.append(" + ")
    exp.rhs.aceitar(this)
    sb += ')'
  }

  override def visitar(exp: ExpSub): Unit = {
    sb += '('
    exp.lhs.aceitar(this)
    sb.append(" - ")
    exp.rhs.aceitar(this)
    sb += ')'
  }

  override def visitar(exp: ExpMult): Unit = {
    sb += '('
    exp.lhs.aceitar(this)
    sb.append(" * ")
    exp.rhs.aceitar(this)
    sb += ')'
  }

  override def visitar(exp: ExpDiv): Unit = {
    sb += '('
    exp.lhs.aceitar(this)
    sb.append(" / ")
    exp.rhs.aceitar(this)
    sb += ')'
  }

  override def visitar(exp: ExpAND): Unit = {
    sb += '('
    exp.lhs.aceitar(this)
    sb.append(" AND ")
    exp.rhs.aceitar(this)
    sb += ')'
  }

  override def visitar(exp: ExpOR): Unit = {
    sb += '('
    exp.lhs.aceitar(this)
    sb.append(" OR ")
    exp.rhs.aceitar(this)
    sb += ')'
  }

  override def visitar(exp: ExpNOT): Unit = {
    sb.append("NOT ")
    sb += '('
    exp.expB.aceitar(this)
    sb += ')'
  }

  override def visitar(exp: IfThenElse): Unit = {
    sb.append("If ")
    sb += '('
    exp.If.aceitar(this)
    sb += ')'
    sb.append(" Then ")
    sb += '('
    exp.Then.aceitar(this)
    sb += ')'
    sb.append(" Else ")
    sb += '('
    exp.Else.aceitar(this)
    sb += ')'
  }

  override def visitar(exp: ExpLet): Unit = {
    sb.append("Let ")
    sb.append(exp.id)
    sb.append(" = ")
    exp.expNomeada.aceitar(this)
    sb.append(" in ")
    exp.corpo.aceitar(this)
  }

  override def visitar(exp: ExpLambda): Unit = {
    sb.append("Closure")
    sb += '('
    sb.append(exp.id)
    sb += ','
    exp.corpo.aceitar(this)
    sb += ')'
  }

  override def visitar(exp: ExpAplicacaoLambda): Unit = {
    sb += '('
    exp.exp1.aceitar(this)
    sb += ')'
    exp.exp2.aceitar(this)
  }

  override def visitar(exp: ExpAplicacaoNomeada): Unit = {
    sb.append(exp.nome.toString)
    sb += '('
    val find = Ambiente.recuperarFuncao(exp.nome)
    find.argFormal.foreach(arg => sb.append(arg.toString) ++ sb.append(","))
    sb.deleteCharAt(sb.length - 1)
    sb += ')'
    sb.append(" = ")
    find.corpo.aceitar(this)
  }

  override def visitar(exp: ExpRef): Unit = {
    sb.append(exp.variavel.toString)
  }

  override def visitar(exp: Closure): Unit = { }
}
