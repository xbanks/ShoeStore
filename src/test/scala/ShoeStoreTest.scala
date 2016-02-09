package com.test

import org.scalatest.FunSuite
import com.store._
import com.store.Items._

class ShoeStoreTest extends FunSuite {

  test("Two of the same shoes") {
    val inventory = new Inventory

    val shoe1 = Shoe("Nike", "Air Jordan 1", 11, 160)
    val shoe2 = Shoe("Nike", "Air Jordan 1", 11, 160)
    
    inventory.addItem(shoe1)
    inventory.addItem(shoe2)

    assert(inventory.entries.size == 1)
    assert(inventory.entries(0).quantity == 2)
  }

  test("3 shoes (2 identical)") {
    val inventory = new Inventory

    val shoe1 = Shoe("Nike", "Air Jordan 2", 11, 160)
    val shoe2 = Shoe("Nike", "Air Jordan 2", 11, 160)
    val shoe3 = Shoe("Adidas", "Ultraboost", 11, 180)

    inventory.addItem(shoe1)
    inventory.addItem(shoe2)
    inventory.addItem(shoe3)

    assert(inventory.entries.size == 2)
    assert(inventory.entries(0).quantity == 2)
    assert(inventory.entries(1).quantity == 1)
  }

  test("2 Shirts, 1 Shoe") {
    val inventory = new Inventory

    val shirt1 = Shirt("Supreme", "WhiteShirt", "M", 30)
    val shirt2 = Shirt("Supreme", "WhiteShirt", "L", 30)
    val shoe1 = Shoe("Adidas", "Tubular X", 11, 120)

    inventory.addItem(shirt1)
    inventory.addItem(shirt2)
    inventory.addItem(shoe1)

    assert(inventory.entries.size == 3)
  }
}