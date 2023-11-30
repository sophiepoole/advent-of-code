import kotlin.io.path.Path
import kotlin.io.path.readLines

internal object Input {
    fun asList(name: String) = Path("resources/$name.txt").readLines()
}

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)