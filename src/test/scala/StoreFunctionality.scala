import com.store.Store
import com.store.inventory.Item.{Item, Shirt, Shoe}
import org.scalatest.FunSuite

/**
  * Created by Xavier on 3/27/2016.
  */
class StoreFunctionality extends FunSuite {

  // TODO: Maybe add some variables controlling how many items are added/removed etc?

  val _store = new Store[Shoe](name = "Test Shoe Store")
  val _shoe1 = Shoe("Test", "Shoe1", 11, 100)
  val _shoe2 = Shoe("Test", "Shoe2", 9.5, 75.99)
  val _shoe3 = Shoe("Test", "Shoe3", 12, 90)

  test("addToStock") {
    val returnedStock1 =_store.addToStock(_shoe1)
    assert(returnedStock1.size == 1)
    assert(returnedStock1.head.item.equals(_shoe1))

    val returnedStock2 = _store.addToStock(_shoe1)
    assert(returnedStock2.size == 1)
    assert(returnedStock2.head.item.equals(_shoe1))
    assert(returnedStock2.head.quantity == 2)

    val returnedStock3 = _store.addToStock(_shoe2, 4)
    assert(returnedStock3.size == 2)
    assert(returnedStock3(1).item.equals(_shoe2))
    assert(returnedStock3(1).quantity == 4)
  }

  test("removeFromStock") {
    val returnedStock1 = _store.removeFromStock(_shoe1)
    assert(returnedStock1.size == 2)
    assert(returnedStock1.head.quantity == 1)

    val returnedStock2 = _store.removeFromStock(_shoe2, 2)
    assert(returnedStock2.size == 2)
    assert(returnedStock2(1).item.equals(_shoe2))
    assert(returnedStock2(1).quantity == 2)
  }

  test("checkStock") {
    val checkStock1 = _store.checkStock(_shoe1)
    val checkStock2 = _store.checkStock(_shoe2)

    assert(checkStock1.isDefined && checkStock2.isDefined)
    assert(checkStock1.get.item.equals(_shoe1))
    assert(checkStock1.get.quantity == 1)

    assert(checkStock2.get.item.equals(_shoe2))
    assert(checkStock2.get.quantity == 2)
  }

  test("purchase") {
    val (purchasable, item, err) = _store.purchase(_shoe3)
    assert(!purchasable)
    assert(item.isEmpty)
    assert(err.isDefined && err.get == "Item DNE")

    val (p2, item2, err2) = _store.purchase(_shoe1, 2)
    assert(!p2)
    assert(item2.isEmpty)
    assert(err2.isDefined && err2.get == s"Current Quantity: 1")

    val (p3, item3, err3) = _store.purchase(_shoe2, 2)
    assert(p3)
    assert(item3.isDefined)
    assert(item3.get.item.equals(_shoe2))
    assert(item3.get.quantity == 2)
    assert(err3 == None)
  }

  test("toString") {
    assert(_store.toString().equals("Store: Test Shoe Store"))

    val shirtStore = new Store[Shirt](name = "          A Shirt Store              ")
    intercept[IllegalArgumentException] {
      val nonameStore = new Store[Item](name = "             ")
    }

    intercept[IllegalArgumentException] {
      val nullStore = new Store[Item](name = null)
    }

    assert(shirtStore.toString().equals("Store: A Shirt Store"))
  }
}
