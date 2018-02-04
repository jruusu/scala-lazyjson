package lazyjson

import org.scalatest._

class LazyArraySpec extends FlatSpec with Matchers {
  private val str = """[9, true, 3.1415, "hello world", false, null, [2,2,4], {"foo": 42}, "\n"]"""
  private val arr = LazyArray(str)

  "apply" should "chooch" in {
    arr.head shouldEqual 9L
    arr(1) shouldEqual true
    arr(2) shouldEqual 3.1415
    arr(3) shouldEqual "hello world"
    arr(4) shouldEqual false
    arr(5) shouldEqual LazyObject.NULL
    arr(6) shouldBe a[LazyArray]
    arr(7) shouldBe a[LazyObject]
    arr(8) shouldEqual "\n"
    assertThrows[me.doubledutch.lazyjson.LazyException] { arr(9) }
  }

  "iterable" should "chooch" in {
    arr.size shouldEqual 9
    arr.indexOf(LazyObject.NULL) shouldEqual 5
    arr.collect { case b: Boolean => b } shouldEqual Seq(true, false)
  }
}
