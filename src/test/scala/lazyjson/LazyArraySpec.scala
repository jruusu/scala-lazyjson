package lazyjson

import org.scalatest._
import org.scalatest.OptionValues._

class LazyArraySpec extends FlatSpec with Matchers {
  private val str = """[9, true, 3.1415, "hello world", false, null, [2,2,4], {"foo": 42}, "\n"]"""
  private val arr = LazyArray(str)

  "get" should "chooch" in {
    arr.get(0) shouldEqual 9L
    arr.get(1) shouldEqual true
    arr.get(2) shouldEqual 3.1415
    arr.get(3) shouldEqual "hello world"
    arr.get(4) shouldEqual false
    arr.get(5) shouldEqual LazyObject.NULL
    arr.get(6) shouldBe a[LazyArray]
    arr.get(7) shouldBe a[LazyObject]
    arr.get(8) shouldEqual "\n"
    assertThrows[me.doubledutch.lazyjson.LazyException] { arr.get(9) }
  }

  "opt" should "chooch" in {
    arr.opt(0) shouldEqual Some(9L)
    arr.opt(1) shouldEqual Some(true)
    arr.opt(2) shouldEqual Some(3.1415)
    arr.opt(3) shouldEqual Some("hello world")
    arr.opt(4) shouldEqual Some(false)
    arr.opt(5) shouldEqual Some(LazyObject.NULL)
    arr.opt(6).value shouldBe a[LazyArray]
    arr.opt(7).value shouldBe a[LazyObject]
    arr.opt(8) shouldEqual Some("\n")
    arr.opt(9) shouldEqual None
  }
}
