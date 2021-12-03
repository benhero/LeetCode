/**
 *
 *
 * @author Benhero
 * @date   2021-12-02
 */
fun Number.log() {
    println(this)
}

fun IntArray.log() {
    println(this.contentToString())
}

fun Any.log() {
    println(this.toString())
}