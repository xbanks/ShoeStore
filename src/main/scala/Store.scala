package com.store

import com.store.Items._

object Store {
    
  abstract class Store(storeInventory: Inventory, name: String) {
    def purchase[T <: Item](item: T, quantity: Int = 1, discount: Float = 0.0f): Boolean
    // def checkStock[T <: Item](item: T): (T, Int)
    // def addToStock[T <: Item](item: T, quantity: Int): (T, Int)
    // def removeFromStock[T <: Item](item: T, quantity: Int): (T, Int)
  }
}