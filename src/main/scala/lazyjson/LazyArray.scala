package lazyjson

import scala.util.Try

class LazyArray(underlying: me.doubledutch.lazyjson.LazyArray) extends Seq[Any] with LazyElement {
  override def apply(idx: Int): Any = wrap(underlying.get(idx))

  override def iterator: Iterator[Any] = new Iterator[Any] {
    private var current = 0

    override def hasNext: Boolean = Try(apply(current)).isSuccess

    override def next(): Any = {
      val next = apply(current)
      current += 1
      next
    }
  }

  override def length: Int = underlying.length()
}

object LazyArray {
  def apply(raw: String): LazyArray = new LazyArray(
    new me.doubledutch.lazyjson.LazyArray(raw)
  )
}
