package com.store.inventory

import com.store.inventory.Item._

class Inventory[T <: Item](var entries: List[InventoryElement[T]] = List[InventoryElement[T]]()) {
  type IE = InventoryElement[T]

  def addItem(item: T, _inventory: List[IE] = entries): List[IE] = {
    entries = _inventory match {
      case InventoryElement(iv, quant) :: xs if item.matches(iv)  => InventoryElement(item, quant + 1) :: xs
      case x :: xs    => x :: addItem(item, xs)
      case _          => List(InventoryElement(item, 1))
    }

    entries
  }

  def itemList: List[T] = entries.map{case InventoryElement(item, quant) => item}
  def inventoryList: List[IE] = for (entry <- entries) yield entry

  def getItem(item: T, _inventory: List[IE] = entries): Option[IE] = {
    val possibleItem = for(elem <- _inventory if item.matches(elem.item) && elem.quantity > 0) yield elem
    possibleItem.headOption
  }

  def removeItem(item: T, _inventory: List[IE] = entries): List[IE] = {
    entries = _inventory match {
      case InventoryElement(iv, quant) :: xs if item.matches(iv) && quant > 0 =>  InventoryElement[T](item, quant - 1) :: xs
      case x :: xs  => x :: removeItem(item, xs)
      case _        => List()
    }

    entries
  }

  // TODO: Add a filter function for things like getting all of the shirts, or shoes, etc
  // TODO: Fix the toString
  // override def toString(): String = "Quantity\t\tShoe\n" + entries.map(elem => s"${elem.item.quantity}\t${elem.item.shoe}\n").foldLeft("")(_+_)
}