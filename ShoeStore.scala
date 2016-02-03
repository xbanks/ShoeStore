object ShoeStore {
    import scala.collection.{ mutable, immutable, generic }
    case class Shoe(brand: String, name: String, size: Float, price: Float)
    case class InventoryElement(shoe: Shoe, var quantity: Int)

    val jordan_1 = Shoe("Nike", "Air Jordan 1", 11, 160)

    class Inventory(var inventory: List[InventoryElement] = List()) {
        def addShoe(shoe: Shoe, _inventory :List[InventoryElement] = inventory): List[InventoryElement] = {
             inventory = _inventory match {
                case InventoryElement(shoe, quant) :: xs    => InventoryElement(shoe, quant + 1) :: xs
                case x :: xs                                => x :: addShoe(shoe, xs)
                case _                                      => List(InventoryElement(shoe, 1))
            }

            inventory
        }

        override def toString(): String = "\nShoe\t\t\tQuantity\n" + inventory.map(elem => s"${elem.shoe}\t${elem.quantity}\n").foldLeft("")(_+_)
        
    }

    def main(args: Array[String]) = {
        val jordan_1 = Shoe("Nike", "Air Jordan 1", 11, 160)
        println(jordan_1)
    }

}