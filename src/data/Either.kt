package data

sealed class Either<A,B>() {
    class Left<A,B>(val value: A): Either<A,B>()
    class Right<A,B>(val value: B) : Either<A,B>()

    override fun toString(): String = when(this) {
        is Either.Left -> "Left: ${this.value}"
        is Either.Right -> "Right: ${this.value}"
    }

    companion object {
        fun <A>of(a: A): Either<Any,A> {
            return Either.Right<Any,A>(a)
        }
    }
}


fun <A,B,C>map(a: Either<A,B>, fn: (b: B) -> C): Either<A,C> = when(a) {
    is Either.Left -> a as Either<A,C>
    is Either.Right -> Either.of(fn(a.value)) as Either<A,C>
}