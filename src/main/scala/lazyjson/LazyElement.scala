package lazyjson

trait LazyElement {
  protected def wrap(o: Object): Any =
    o match {
      case la: me.doubledutch.lazyjson.LazyArray => new LazyArray(la)
      case lo: me.doubledutch.lazyjson.LazyObject => new LazyObject(lo)
      case me.doubledutch.lazyjson.LazyObject.NULL => LazyObject.NULL
      case x => x
    }
}
