package lazyjson

class LazyObject(underlying: me.doubledutch.lazyjson.LazyObject) extends LazyElement {
  def get(key: String): Any = wrap(underlying.get(key))

  def opt(key: String): Option[Any] = Option(underlying.opt(key)).map(wrap)
}

object LazyObject {
  val NULL: LazyElement = new LazyElement {}

  def apply(raw: String): LazyObject = new LazyObject(
    new me.doubledutch.lazyjson.LazyObject(raw)
  )
}
