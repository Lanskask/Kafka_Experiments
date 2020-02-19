import org.scalatest.FunSuite

class packageTest extends FunSuite {

//  test("Basic - should return 20") {
//    val num: Array[Int]  = Array(20, 11, 9, 3, 15, 14)
//    assert(FindMax.getMaxInArr(num) == 20)
//  }
//
//  test("One el arr - should have size 20") {
//    val num: Array[Int]  = Array(20)
//    assert(FindMax.getMaxInArr(num) == 20)
//  }
//
//  test("The same els") {
//    val num: Array[Int]  = Array(20, 20)
//    assert(FindMax.getMaxInArr(num) == 20)
//  }
//
//  test("2 the same els") {
//    val num: Array[Int]  = Array(20, 10, 20)
//    assert(FindMax.getMaxInArr(num) == 20)
//  }
//
//  test("Get exception if empty") {
//    assertThrows[UnsupportedOperationException] {
//      FindMax.getMaxInArr(Array())
//    }
//  }


//  // for findMax2
  test("Basic - should return 20") {
    val num: Array[Int]  = Array(20, 11, 9, 3, 15, 14)
    assert(FindMax.getMaxInArray(num) == 20)
  }

  test("One el arr - should have size 20") {
    val num: Array[Int]  = Array(20)
    assert(FindMax.getMaxInArray(num) == 20)
  }

  test("The same els") {
    val num: Array[Int]  = Array(20, 20)
    assert(FindMax.getMaxInArray(num) == 20)
  }

  test("2 the same els") {
    val num: Array[Int]  = Array(20, 10, 20)
    assert(FindMax.getMaxInArray(num) == 20)
  }

  test("Get exception if empty") {
    assertThrows[UnsupportedOperationException] {
      FindMax.getMaxInArray(Array())
    }
  }

}
