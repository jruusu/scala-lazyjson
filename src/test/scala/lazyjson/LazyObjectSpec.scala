package lazyjson

import org.scalatest._
import org.scalatest.OptionValues._

class LazyObjectSpec extends FlatSpec with Matchers {
  private val str = """{"foo":9,"bar":true,"baz":3.1415,"sval":"hello world","fval":false,"nval":null,"aval":[2,2,4],"oval":{"foo":42},"eval":"\n"}"""
  private val obj = LazyObject(str)

  "get" should "chooch" in {
    obj.get("foo") shouldEqual 9L
    obj.get("bar") shouldEqual true
    obj.get("baz") shouldEqual 3.1415
    obj.get("sval") shouldEqual "hello world"
    obj.get("fval") shouldEqual false
    obj.get("nval") shouldEqual LazyObject.NULL
    obj.get("aval") shouldBe a[LazyArray]
    obj.get("oval") shouldBe a[LazyObject]
    obj.get("eval") shouldEqual "\n"
    assertThrows[me.doubledutch.lazyjson.LazyException] { obj.get("notthere") }
  }

  "opt" should "chooch" in {
    obj.opt("foo") shouldEqual Some(9L)
    obj.opt("bar") shouldEqual Some(true)
    obj.opt("baz") shouldEqual Some(3.1415)
    obj.opt("sval") shouldEqual Some("hello world")
    obj.opt("fval") shouldEqual Some(false)
    obj.opt("nval") shouldEqual Some(LazyObject.NULL)
    obj.opt("aval").value shouldBe a[LazyArray]
    obj.opt("oval").value shouldBe a[LazyObject]
    obj.opt("eval") shouldEqual Some("\n")
    obj.opt("notthere") shouldEqual None
  }
}
