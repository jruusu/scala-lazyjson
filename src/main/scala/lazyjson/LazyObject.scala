package lazyjson

class LazyObject(underlying: me.doubledutch.lazyjson.LazyObject) extends Map[String, Any] with LazyElement {
  override def +[V1 >: Any](kv: (String, V1)): Map[String, V1] = {
    underlying.put(kv._1, kv._2)
    this
  }

  override def get(key: String): Option[Any] = Option(underlying.opt(key)).map(wrap)

  override def iterator: Iterator[(String, Any)] = new Iterator[(String, Any)] {

    import scala.collection.JavaConverters._

    private val keys = underlying.keys().asScala

    override def hasNext: Boolean = keys.hasNext

    override def next(): (String, Any) = {
      val key = keys.next()
      (key, wrap(underlying.get(key)))
    }
  }

  override def -(key: String): Map[String, Any] = {
    underlying.remove(key)
    this
  }
}

object LazyObject {
  val NULL: LazyElement = new LazyElement {}

  def apply(raw: String): LazyObject = new LazyObject(
    new me.doubledutch.lazyjson.LazyObject(raw)
  )
}
