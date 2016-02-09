package com.store
import scala.collection.{ mutable, immutable, generic }
import com.store.Store._
import com.store.Inventory._
import com.store.Items._

class ShoeStore(storeInventory: Inventory = new Inventory, 
                name: String = "Shoe Store") extends Store(storeInventory, name) {

  def purchase[T <: Item](item: T, quantity: Int = 1, discount: Float = 0.0f): Boolean = {
    // if(checkStock(item)._2 == 0) {
    //     println("This item is not in stock...")
    //     false
    // }
    // else {
    //     // removeFromStock
    //     true
    // }
    false
  }
  // def checkStock[T <: Item](item: T): (T, Int)
  // def addToStock[T <: Item](item: T, quantity: Int): (T, Int)
  // def removeFromStock[T <: Item](item: T, quantity: Int): (T, Int)
}
