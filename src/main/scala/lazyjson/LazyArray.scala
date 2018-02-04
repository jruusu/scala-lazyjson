package lazyjson

class LazyArray(underlying: me.doubledutch.lazyjson.LazyArray) extends LazyElement {
  def get(index: Int): Any = wrap(underlying.get(index))

  def opt(index: Int): Option[Any] = Option(underlying.opt(index)).map(wrap)
}

object LazyArray {
  def apply(raw: String): LazyArray = new LazyArray(
    new me.doubledutch.lazyjson.LazyArray(raw)
  )
}
