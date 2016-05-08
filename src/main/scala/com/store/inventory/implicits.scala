package com.store.inventory

import com.store.inventory.Item.{Item, Shirt, Shoe}
import org.json4s.JsonDSL._
import org.json4s._

/**
  * Created by Xavier on 4/3/2016.
  */

// TODO: add json tests
object implicits {
  implicit def shoeToJson(shoe: Shoe): JValue = {
    ("brand" -> shoe.brand) ~ ("name" -> shoe.name) ~ ("size" -> shoe.size)~ ("price" -> shoe.price)
  }

  implicit def shirtToJson(shirt: Shirt): JValue = {
    ("brand" -> shirt.brand) ~ ("name" -> shirt.name) ~ ("size" -> shirt.size)~ ("price" -> shirt.price)
  }

  implicit def itemToJson[T <: Item](item: Item): JValue = item match {
    case shirt: Shirt => shirtToJson(shirt)
    case shoe: Shoe  => shoeToJson(shoe)
  }

  implicit def IEtoJson[T <: Item](ie: InventoryElement[T]): JValue = {
    val jItem: JValue = ie.item
    ("item" -> jItem) ~ ("quantity" -> ie.quantity)
  }

  implicit def inventoryToJson[T <: Item](inventory: Inventory[T]): JValue = {
    val jEntries: JValue = inventory.entries
    "entries" -> jEntries
  }
}
