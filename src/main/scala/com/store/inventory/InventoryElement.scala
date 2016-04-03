package com.store.inventory

import com.store.inventory.Item.Item
import org.json4s.JsonDSL._

/**
  * Created by Xavier on 3/26/2016.
  */
case class InventoryElement[T <: Item](item: T, quantity: Int) {
  val toJsonable = {
    ("item" -> item.toJsonable) ~ ("quantity" -> quantity)
  }
}