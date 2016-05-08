package com.store

import com.store.inventory.Item.Item
import com.store.inventory.implicits._
import com.store.inventory.{Inventory, InventoryElement}
import org.json4s.JsonAST.JValue

// TODO: maybe switch the parameters around since name is usually the only one used?
case class Store[T <: Item](val storeInventory: Inventory[T] = new Inventory[T], name: String) {
  type storeType = T
  // Require non empty names
  require(name != null, "Name cannot be null")
  require(name.trim != "", "Name must be Non-Empty")
  require(storeInventory != null)

  val trimmedName = name.trim

  def purchase(item: T, quantity: Int = 1, discount: Float = 0.0f): (Boolean, Option[InventoryElement[T]], Option[String]) = {
    val itemStock = checkStock(item)

    if(itemStock.isDefined) {
      val itemQuantity = itemStock.get.quantity

      // Make sure there is enough stock to cover the purchase quantity
      if(itemQuantity < quantity) {
        return (false, None, Some(s"Current Quantity: $itemQuantity"))
      }

      (1 to quantity).map{ i => storeInventory.removeItem(item) }
      return (true, Some(InventoryElement(item, quantity)), None)
    }

//    println("This item is not in stock...")
    (false, None, Some("Item DNE"))
  }

  def getInventory: List[InventoryElement[T]] = storeInventory.inventoryList
  def getInventoryJson: JValue = storeInventory //inventoryToJson(storeInventory)

  def checkStock(item: T): Option[InventoryElement[T]] = storeInventory.getItem(item)
  def addToStock(item: T, quantity: Int = 1): List[InventoryElement[T]] = {
    (1 to quantity).foreach{ i => storeInventory.addItem(item) }
    getInventory
  }

  def removeFromStock(item: T, quantity: Int = 1): List[InventoryElement[T]] = {
    (1 to quantity).map{i => storeInventory.removeItem(item) }
    getInventory
  }

  override def toString: String = s"Store: $trimmedName"
}
