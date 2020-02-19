class MaxFun {

  def getMax(inputArray: Array[Int]): Int = {
    var maxValue = inputArray(0)
    var i = 1
    while ( {
      i < inputArray.length
    }) {
      if (inputArray(i) > maxValue) maxValue = inputArray(i)

      {
        i += 1;
        i - 1
      }
    }
    maxValue
  }
}