package com.karasiq.bootstrap.tooltip

import com.karasiq.bootstrap.BootstrapComponent
import com.karasiq.bootstrap.BootstrapImplicits._
import org.scalajs.dom.Element
import org.scalajs.jquery.jQuery

import scala.scalajs.js
import scalatags.JsDom.all._

class Tooltip(content: Modifier, placement: TooltipPlacement) extends BootstrapComponent {
  override def render(md: Modifier*): Modifier = new Modifier {
    override def applyTo(t: Element): Unit = {
      val options = js.Object().asInstanceOf[TooltipOptions]
      options.html = true
      options.title = div(content).render
      options.placement = placement.asString
      jQuery(t).tooltip(options)
    }
  }
}

object Tooltip {
  def apply(content: Modifier, placement: TooltipPlacement = TooltipPlacement.auto): Tooltip = {
    new Tooltip(content, placement)
  }
}
