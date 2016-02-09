package com.store

object Items {
// A good way to collect by type: i.inventory.collect { case InventoryElement(item:Shoe, quant) => (item, quant) }
// TODO: Possibly add sale val? for when something goes on sale?

  trait Item {
    // Will be used to check if two items match based on some criteria
    val matches:Item => Boolean
  }
  case class Shoe(brand: String, name: String, size: Float, price: Float) extends Item {
    val matches: (Item) => Boolean = (item: Item) => item match {
      case shoe:Shoe => (shoe.brand == brand) && (shoe.name == name) && (shoe.size == size)
      case _         => false
    }
  }
  case class Shirt(brand: String, name: String, size: String, price: Float) extends Item {
    val matches: (Item) => Boolean = (item: Item) => item match {
      case shirt:Shirt => (shirt.brand == brand) && (shirt.name == name) && (shirt.size == size)
      case _           => false
    }
  }
}
