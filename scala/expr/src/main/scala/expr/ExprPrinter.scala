package expr

import Operator._

object ExprPrinter {
  private def parenthesize(expr: Expr, enclosingPrecendence: Int): String = {
    val str = asString(expr)
    expr match {
      case binOp @ BinOp(operator, _, _) =>
        if (operator.precendance > enclosingPrecendence) "(" + str + ")"
        else str
      case _ => str
    }
  }

  def asString(expr: Expr): String = {
    expr match {
      case Num(number) => number.toString.replaceAll(".0$", "")
      case BinOp(operator, left, right) =>
        val prec = operator.precendance
        parenthesize(left, prec) + " " + operator + " " + parenthesize(right, prec)
      case Name(name) => name
    }
  }
}