package com.store.inventory

object Item {
// A good way to collect by type: i.inventory.collect { case InventoryElement(item:Shoe, quant) => (item, quant) }
// TODO: Possibly add sale val? for when something goes on sale?

  trait Item {
    // Will be used to check if two items match based on some criteria
    val matches:Item => Boolean
  }

  trait Discounted {
    val discountPercent: Float
    val isDiscounted: Boolean = discountPercent > 0
  }

  case class Shoe(brand: String, name: String, size: Double, price: Double) extends Item {
    val matches: (Item) => Boolean = {
      case shoe: Shoe => (shoe.brand == brand) && (shoe.name == name) && (shoe.size == size)
      case _ => false
    }
  }
  case class Shirt(brand: String, name: String, size: String, price: Double) extends Item {
    val matches: (Item) => Boolean = {
      case shirt: Shirt => (shirt.brand == brand) && (shirt.name == name) && (shirt.size == size)
      case _ => false
    }
  }
}
