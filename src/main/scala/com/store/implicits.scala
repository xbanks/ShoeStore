package com.store

import com.store.inventory.Item.Item
import com.store.inventory.implicits.inventoryToJson
import org.json4s.JsonAST.JValue
import org.json4s.JsonDSL._

/**
  * Created by Xavier on 4/3/2016.
  */
object implicits {
  implicit def storeToJson[T <: Item](store: Store[T]): JValue = {
    ("name" -> store.name) ~ ("storeInventory" -> store.storeInventory)
  }
}
