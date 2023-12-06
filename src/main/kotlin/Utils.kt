import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.readLines

internal object Input {
    fun asList(name: String) = File("src/main/resources", name).readLines()

    fun asText(name: String) = File("src/main/resources", name).readText()
}

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)