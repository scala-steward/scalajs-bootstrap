package com.karasiq.bootstrap.modal

import scala.language.postfixOps
import scala.scalajs.js
import scala.scalajs.js.|

import org.scalajs.jquery.jQuery

import com.karasiq.bootstrap.components.BootstrapComponents
import com.karasiq.bootstrap.context.JSRenderingContext

trait JSModals { self: JSRenderingContext with Modals with BootstrapComponents ⇒
  implicit class JSModalOps(modal: Modal) {
    def show(backdrop: Boolean | String = true, keyboard: Boolean = true,
             show: Boolean = true, events: Map[String, js.Any] = Map.empty): Unit = {

      val dialog = jQuery(modal.renderTag().render)
      val options = js.Object().asInstanceOf[JSModalOptions]
      options.backdrop = backdrop
      options.keyboard = keyboard
      options.show = show
      dialog.modal(options)

      dialog.on("hidden.bs.modal", () ⇒ {
        // Remove from DOM
        dialog.remove()
      })

      events.foreach { case (event, handler) ⇒
        dialog.on(event, handler)
      }

      dialog.modal("show")
    }
  }
}
