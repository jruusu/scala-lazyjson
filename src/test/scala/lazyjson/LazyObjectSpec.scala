package lazyjson

import org.scalatest._
import org.scalatest.OptionValues._

class LazyObjectSpec extends FlatSpec with Matchers {
  private val str = """{"foo":9,"bar":true,"baz":3.1415,"sval":"hello world","fval":false,"nval":null,"aval":[2,2,4],"oval":{"foo":42},"eval":"\n"}"""
  private val obj = LazyObject(str)

  "apply" should "chooch" in {
    obj("foo") shouldEqual 9L
    obj("bar") shouldEqual true
    obj("baz") shouldEqual 3.1415
    obj("sval") shouldEqual "hello world"
    obj("fval") shouldEqual false
    obj("nval") shouldEqual LazyObject.NULL
    obj("aval") shouldBe a[LazyArray]
    obj("oval") shouldBe a[LazyObject]
    obj("eval") shouldEqual "\n"
    assertThrows[NoSuchElementException] { obj("notthere") }
  }

  "get" should "chooch" in {
    obj.get("foo") shouldEqual Some(9L)
    obj.get("bar") shouldEqual Some(true)
    obj.get("baz") shouldEqual Some(3.1415)
    obj.get("sval") shouldEqual Some("hello world")
    obj.get("fval") shouldEqual Some(false)
    obj.get("nval") shouldEqual Some(LazyObject.NULL)
    obj.get("aval").value shouldBe a[LazyArray]
    obj.get("oval").value shouldBe a[LazyObject]
    obj.get("eval") shouldEqual Some("\n")
    obj.get("notthere") shouldEqual None
  }

  "getAsOpt" should "chooch" in {
    obj.getAsOpt[String]("foo") shouldEqual None
    obj.getAsOpt[String]("sval").value shouldBe a[String]
    obj.getAsOpt[LazyObject]("oval").value shouldBe a[LazyObject]
  }

  "getAs" should "chooch" in {
    assertThrows[NoSuchElementException] { obj.getAs[String]("foo") }
    obj.getAs[String]("sval") shouldBe a[String]
    obj.getAs[LazyObject]("oval") shouldBe a[LazyObject]
  }
}
