# scala-lazyjson

A very simple, very incomplete Scala wrapper for [LazyJSON](https://github.com/doubledutch/LazyJSON) - *a very fast, very lazy JSON parser for Java*.

## Usage

```scala
import lazyjson._

val json =
  """{
    |  "data": [
    |    {
    |      "id": "a-001",
    |      "title": "Example A 001"
    |    },
    |    {
    |      "id": "b-001",
    |      "title": "Example B 001"
    |    },
    |    {
    |      "id": "c-001",
    |      "title": "Example C 001"
    |    }
    |  ]
    |}"""

LazyObject(json)
  .getAs[LazyArray]("data")
  .collect {
    case o: LazyObject => o.getAs[String]("title")
  }

// res0: Seq[String] = List(Example A 001, Example B 001, Example C 001)
```
