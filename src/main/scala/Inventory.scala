package com.store

import com.store.Items._

case class InventoryElement(item: Item, quantity: Int)
class Inventory(var entries: List[InventoryElement] = List()) {
  def addItem(item: Item, _inventory: List[InventoryElement] = entries): List[InventoryElement] = {
	
    entries = _inventory match {
      case InventoryElement(iv, quant) :: xs if item.matches(iv)  => InventoryElement(item, quant + 1) :: xs
      case x :: xs    => x :: addItem(item, xs)
      case _          => List(InventoryElement(item, 1))
    }

    entries
  }

  def itemList: List[Item] = entries.map{case InventoryElement(item, quant) => item}

  // def getItem(item: Item, _inventory: List[InventoryElement] = entries): 

  // TODO: Add a filter function for things like getting all of the shirts, or shoes, etc

  // TODO: Fix the toString
  // override def toString(): String = "Quantity\t\tShoe\n" + entries.map(elem => s"${elem.item.quantity}\t${elem.item.shoe}\n").foldLeft("")(_+_)
}