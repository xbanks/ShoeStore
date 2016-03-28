import com.store._
import com.store.inventory.Item._
import org.scalatest.FunSuite

class ShoeStoreTest extends FunSuite {

  test("Two of the same shoes, different sizes") {
    val shoeStore = new Store[Shoe](name = "Test Shoe Store")

    val shoe1 = Shoe("Nike", "Air Jordan 1", 11, 160)
    val shoe2 = Shoe("Nike", "Air Jordan 1", 12, 160)
    
    shoeStore.addToStock(shoe1)
    val checkStock1 = shoeStore.checkStock(shoe1)
    assert(checkStock1.isDefined && checkStock1.get.quantity == 1)

    shoeStore.addToStock(shoe2, 2)
    val checkStock2 = shoeStore.checkStock(shoe2)

    assert(checkStock2.get.equals(checkStock2.get))
    assert(checkStock1.get.quantity == 1)
  }

  test("3 shoes (2 identical)") {

    val shoeStore = new Store[Shoe](name = "Test Shoe Store")

    val shoe1 = Shoe("Nike", "Air Jordan 2", 11, 160)
    val shoe2 = Shoe("Nike", "Air Jordan 2", 11, 160)
    val shoe3 = Shoe("Adidas", "Ultraboost", 11, 180)

    shoeStore.addToStock(shoe1)
    shoeStore.addToStock(shoe2)
    shoeStore.addToStock(shoe3)

    val checkStock1 = shoeStore.checkStock(shoe1)
    val checkStock2 = shoeStore.checkStock(shoe2)
    val checkStock3 = shoeStore.checkStock(shoe3)

    assert(checkStock1.isDefined && checkStock2.isDefined && checkStock3.isDefined)
    assert(checkStock1.get.equals(checkStock2.get))
    assert(shoeStore.getInventory.size == 2)
  }

  test("2 Shirts, 1 Shoe") {

    val shirt1 = Shirt("Supreme", "WhiteShirt", "M", 30)
    val shirt2 = Shirt("Supreme", "WhiteShirt", "L", 30)
    val shoe1 = Shoe("Adidas", "Tubular X", 11, 120)
  }
}