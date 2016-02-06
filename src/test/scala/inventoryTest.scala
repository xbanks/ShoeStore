import com.store.ShoeStore._

object InventoryTest {
	val jordan_1 = Shoe("Nike", "Air Jordan 1", 11, 160)
	val jordan_2 = Shoe("Nike", "Air Jordan 2", 11, 160)

    def main(args: Array[String]): Unit = {
      println(jordan_1)
      println(jordan_2)
    }
    
}