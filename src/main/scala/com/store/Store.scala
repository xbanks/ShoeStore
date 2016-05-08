package com.store

import com.store.inventory.Item.Item
import com.store.inventory.{Inventory, InventoryElement}


class Store[T <: Item](name: String, storeInventory: Inventory[T] = new Inventory[T])  {
  // Require non empty names
  require(name != null, "Name cannot be null")
  require(name.trim != "", "Name must be Non-Empty")
  require(storeInventory != null)

  val trimmedName = name.trim

  def purchase(item: T, quantity: Int = 1, discount: Float = 0.0f): (Boolean, Option[InventoryElement[T]], Option[String]) = {
    val itemStock = checkStock(item)
    
    val retVal = itemStock.map{ i => i match {
      case x if i.quantity < quantity =>  No(s"Current Quantity: ${i.quantity}")
      case _ => { (1 to quantity).map{ i => storeInventory.removeItem(item) }
                  Yes(InventoryElement(item, quantity))}
    }}

      //println("This item is not in stock...")
    retVal.getOrElse(No("Item DNE"))
  }

  private val No  = (reason: String) => (false, None, Some(reason))
  private val Yes = (item: InventoryElement[T]) => (true, Some(item), None)

  def getInventory: List[InventoryElement[T]] = storeInventory.inventoryList
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
