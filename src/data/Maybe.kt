package data

sealed class Maybe<A>() {
    class Nothing<A>(): Maybe<A>()
    class Just<A>(val value: A) : Maybe<A>()

    override fun toString(): String = when(this) {
        is Nothing -> "Nothing"
        is Just<*> -> "Just: ${this.value.toString()}"
    }


    companion object {
        fun <A>of(a: A): Maybe<A> {
            return Maybe.Just(a)
        }
    }
}


fun <A, B>map(a: Maybe<A>, fn: (a: A) -> B): Maybe<B> = when(a) {
    is Maybe.Nothing -> a as Maybe<B>
    is Maybe.Just -> Maybe.Just(fn(a.value))
}

fun <A, B>ap(a: Maybe<A>, b: Maybe<(a: A) -> B>) = when(b) {
    is Maybe.Nothing -> b
    is Maybe.Just -> map(a, b.value)
}

fun <A, B>chain(a: Maybe<A>, transform: (a: A) -> B): Maybe<B> = when(a){
    is Maybe.Nothing -> a as Maybe<B>
    is Maybe.Just -> map(a, transform)
}
