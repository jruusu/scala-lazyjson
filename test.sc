import lazyjson._

val json: String =
  """{
    |  "data": [
    |    {
    |      "id": "a-001",
    |      "height": 6.403,
    |      "title": "Example A 001",
    |      "description": "Testing",
    |      "quantity": 3,
    |      "available": false
    |    },
    |    {
    |      "id": "b-001",
    |      "height": 3.23,
    |      "title": "Example B 001",
    |      "quantity": 50,
    |      "available": true
    |    },
    |    {
    |      "id": "c-001",
    |      "height": 4.00,
    |      "title": "Example C 001",
    |      "quantity": 14,
    |      "available": true
    |    }
    |  ]
    |}""".stripMargin

val items = LazyObject(json).getAs[LazyArray]("data")
  .map(_ collectFirst { case x: LazyObject => x })
