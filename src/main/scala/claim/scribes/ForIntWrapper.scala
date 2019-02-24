package claimant
package scribes

import scala.reflect.macros.blackbox.Context

object ForIntWrapper extends Scribe {
  def annotate(c: Context)(input: c.Tree, sys: System): Option[c.Tree] = {
    import c.universe._
    input match {

      // 2.12.x
      case q"scala.Predef.intWrapper($x).max($y)" =>
        Some(Format.str2(c)(x, "max", y, Some(input)))
      case q"scala.Predef.intWrapper($x).min($y)" =>
        Some(Format.str2(c)(x, "min", y, Some(input)))
      case q"scala.Predef.intWrapper($x).signum" =>
        Some(Format.str1(c)(x, "signum", Some(input)))

      // 2.11.x
      case q"scala.this.Predef.intWrapper($x).max($y)" =>
        Some(Format.str2(c)(x, "max", y, Some(input)))
      case q"scala.this.Predef.intWrapper($x).min($y)" =>
        Some(Format.str2(c)(x, "min", y, Some(input)))
      case q"scala.this.Predef.intWrapper($x).signum" =>
        Some(Format.str1(c)(x, "signum", Some(input)))

      case _ =>
        None
    }
  }
}
