import java.util.Scanner

abstract class Menu<T>(private val title: String, protected val items: MutableList<MenuItem<T>>) {
    protected val scanner = Scanner(System.`in`)
    private val exitItem = MenuItem("Выход") { null }

    open fun show() {
        while (true) {
            println("\n$title:")
            items.forEachIndexed { index, item -> println("$index. ${item.title}") }
            println("${items.size}. ${exitItem.title}")

            when (val selected = readValidOption()) {
                items.size -> return
                else -> items[selected].action()?.let { result -> onItemSelected(result) }
            }
        }
    }

    protected abstract fun onItemSelected(item: T)

    private fun readValidOption(): Int {
        while (true) {
            print("Выберите пункт меню: ")
            try {
                val input = scanner.nextLine().toInt()
                if (input in 0..items.size) return input
                println("Ошибка: введите число от 0 до ${items.size}")
            } catch (e: NumberFormatException) {
                println("Ошибка: введите корректное число")
            }
        }
    }

    protected fun readNonEmptyString(errorMessage: String): String {
        while (true) {
            val input = scanner.nextLine()
            if (input.isNotBlank()) return input
            println(errorMessage)
        }
    }
}

class MenuItem<T>(val title: String, val action: () -> T?)