class ArchiveMenu : Menu<Archive>("Список архивов", mutableListOf()) {
    private val archives = mutableListOf<Archive>()

    init {
        items.add(MenuItem("Создать архив") { createArchive() })
    }

    override fun show() {
        items.removeAll { it.title != "Создать архив" }
        archives.forEach { archive ->
            items.add(MenuItem(archive.name) { archive })
        }
        super.show()
    }

    override fun onItemSelected(item: Archive) {
        NoteMenu(item).show()
    }

    private fun createArchive(): Archive {
        println("\nВведите название архива:")
        val name = readNonEmptyString()
        return Archive(name).also {
            archives.add(it)
            println("Архив '$name' создан!")
        }
    }

    private fun readNonEmptyString(): String {
        while (true) {
            val input = scanner.nextLine()
            if (input.isNotBlank()) return input
            println("Название не может быть пустым!")
        }
    }
}