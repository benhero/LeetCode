/**
 *
 *
 * @author Benhero
 * @date   2021-12-02
 */
fun IntArray.log() {
    this.contentToString().log()
}

fun CharArray.log() {
    this.contentToString().log()
}

fun Any.log() {
    println(this.toString())
}