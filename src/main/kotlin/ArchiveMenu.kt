class ArchiveMenu : Menu<Archive>("Список архивов", mutableListOf()) {
    private val archives = mutableListOf<Archive>()

    init {
        items.add(MenuItem("Создать архив") { createArchive() })
    }

    override fun show() {
        updateArchiveItems()
        super.show()
    }

    private fun updateArchiveItems() {
        items.removeAll { it.title != "Создать архив" }
        archives.forEach { archive ->
            items.add(MenuItem(archive.name) { archive })
        }
    }

    override fun onItemSelected(item: Archive) {
        NoteMenu(item).show()
        updateArchiveItems()
    }

    private fun createArchive(): Archive {
        println("\nВведите название архива:")
        val name = readNonEmptyString("Название не может быть пустым!")
        return Archive(name).also {
            archives.add(it)
            println("Архив '$name' создан!")
        }
    }
}