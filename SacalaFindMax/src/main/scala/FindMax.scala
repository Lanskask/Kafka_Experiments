
import scala.annotation.tailrec

object FindMax extends App {
  val num: Array[Int] = Array(20, 11, 9, 3, 15, 14)

  def getMaxInArr(arr: Array[Int]): Int = {
    @tailrec
    def inside(arr: Array[Int], comm: Int, indx: Int): Int = {
      try {
        if (arr(indx) > comm)
          inside(arr, arr(1), indx + 1)
        else
          inside(arr, comm, indx + 1)
      } catch {
        case ex: IndexOutOfBoundsException => comm
      }
    }

    try {
      arr(0)
    } catch {
      case ex: IndexOutOfBoundsException => throw new UnsupportedOperationException("empty array")
    }
    inside(arr, arr(0), 1)
  }


//  def getMaxInArr2(arr: Array[Int]): Int = {
//    def inside(arr: Array[Int], comm: Int, indx: Int): Int = {
//      if (indx > arr.length - 1) {
//        comm
//      } else if (arr(indx) > comm) {
//        inside(arr, arr(indx), indx + 1)
//      } else {
//        inside(arr, comm, indx + 1)
//      }
//    }
//
//    if (arr.length < 1) {
//      throw new UnsupportedOperationException("empty.max")
//    } else if (arr.length == 1) {
//      arr(0)
//    } else {
//      inside(arr, arr(0), 1)
//    }
//  }

//  def max[T <: Ordered[T]](list: List[T]): T = list match {
//
//    case Nil => throw new Error("maximum of empty list")
//    case head :: Nil => head
//    case list => {
//      val maxTail = max(list.tail)
//      if (list.head > maxTail) list.head else maxTail
//    }
//  }

//  def getMaxInArray(arr: Array[Int]): Int = arr match {
//    case Nil => throw new UnsupportedOperationException("empty array")
//    case head :: Nil => head
//    case head :: tail => {
//      if (head > getMaxInArray(tail)) head else getMaxInArray(tail)
//    }
//  }

  def getMaxInList(arr: List[Int]): Int = arr match {
    case Nil => throw new UnsupportedOperationException("empty array")
    case head :: Nil => head
    case head :: tail => if (head > getMaxInList(tail)) head else getMaxInList(tail)
  }

  def getMaxInArray(arr: Array[Int]): Int = arr match {
    case Array() => throw new UnsupportedOperationException("empty array")
    case array if array.length == 1 => array(0)
    case array => if (array.head > getMaxInArray(array.tail)) array.head else getMaxInArray(array.tail)
  }

}
