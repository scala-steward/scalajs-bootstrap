package com.karasiq.bootstrap_text.table

import com.karasiq.bootstrap_text.BootstrapImplicits._
import com.karasiq.bootstrap_text.ModifierFactory

import scalatags.Text.all._

trait TableRow {
  def columns: Seq[Modifier]

  def modifiers: Modifier
}

sealed trait TableRowStyle extends ModifierFactory {
  def styleClass: Option[String]

  override final def createModifier: Modifier = styleClass.map(_.addClass)
}

//noinspection TypeAnnotation
object TableRow {
  def apply(data: Seq[Modifier], ms: Modifier*): TableRow = new TableRow {
    override def modifiers: Modifier = ms
    override def columns: Seq[Modifier] = data
  }

  def data(data: Modifier*): TableRow = {
    apply(data)
  }

  private def style(s: String): TableRowStyle = new TableRowStyle {
    override def styleClass: Option[String] = Some(s)
  }

  def default = new TableRowStyle {
    override def styleClass: Option[String] = None
  }
  def active = style("active")
  def success = style("success")
  def warning = style("warning")
  def danger = style("danger")
  def info = style("info")
}
