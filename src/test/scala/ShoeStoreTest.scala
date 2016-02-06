package com.test

import org.scalatest.FunSuite
import com.store._
import com.store.Items._

class ShoeStoreTest extends FunSuite {

    test("Two of the same shoes") {
        val store = new Inventory

        val shoe1 = Shoe("Nike", "Air Jordan 1", 11, 160)
        val shoe2 = Shoe("Nike", "Air Jordan 1", 11, 160)
        
        store addItem shoe1
        store addItem shoe2

        assert(store.inventory.size == 1)
        assert(store.inventory(0).quantity == 2)
    }

    test("3 shoes (2 identical)") {
        val store = new Inventory

        val shoe1 = Shoe("Nike", "Air Jordan 2", 11, 160)
        val shoe2 = Shoe("Nike", "Air Jordan 2", 11, 160)
        val shoe3 = Shoe("Adidas", "Ultraboost", 11, 180)

        store addItem shoe1
        store addItem shoe2
        store addItem shoe3

        assert(store.inventory.size == 2)
        assert(store.inventory(0).quantity == 2)
        assert(store.inventory(1).quantity == 1)
    }

    test("2 Shirts, 1 Shoe") {
        val store = new Inventory

        val shirt1 = Shirt("Supreme", "WhiteShirt", "M", 30)
        val shirt2 = Shirt("Supreme", "WhiteShirt", "L", 30)
        val shoe1 = Shoe("Adidas", "Tubular X", 11, 120)

        store addItem shirt1
        store addItem shirt2
        store addItem shoe1

        assert(store.inventory.size == 3)
    }
}