package com.store

import com.store.Items._

case class InventoryElement(item: Item, quantity: Int)
class Inventory(var inventory: List[InventoryElement] = List()) {
    def addItem(item: Item, _inventory :List[InventoryElement] = inventory): List[InventoryElement] = {
    	
        inventory = _inventory match {
            case InventoryElement(iv, quant) :: xs if item.matches(iv)  => InventoryElement(item, quant + 1) :: xs
            case x :: xs    => x :: addItem(item, xs)
            case _          => List(InventoryElement(item, 1))
        }

        inventory
    }

    // TODO: Add a filter function for things like getting all of the shirts, or shoes, etc

    // TODO: Fix the toString
    // override def toString(): String = "Quantity\t\tShoe\n" + inventory.map(elem => s"${elem.item.quantity}\t${elem.item.shoe}\n").foldLeft("")(_+_)
}