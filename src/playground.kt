import data.*

fun main(args: Array<String>) {
    val x = Maybe.of("Hello, World!")
    val y = Maybe.Nothing<String>()

    val upper = { str: String -> str.toUpperCase() }
    val f = Maybe.of(upper)

    println("## Maybe")
    println(x.toString())
    println(y.toString())

    println(map(x, upper))
    println(map(y, upper))

    println(ap(x, f))
    println(ap(y, f))

    println(chain(x, upper))
    println(chain(y, upper))

    val add: (Maybe<Int>, Number) -> Maybe<Int> = { x, y -> map(x, { a -> a + 1 }) }

    println(add(Maybe.of(1), 9000.0))

    println("## Either")

    val l: Either<String,Int> = Either.Left("Nope")

    println(map(Either.of(1)) { x -> x + 1})
    println(map(l, { x -> x + 1}))
}
