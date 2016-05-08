package com.store.inventory

import com.store.inventory.Item.Item

/**
  * Created by Xavier on 3/26/2016.
  */
case class InventoryElement[T <: Item](item: T, quantity: Int)