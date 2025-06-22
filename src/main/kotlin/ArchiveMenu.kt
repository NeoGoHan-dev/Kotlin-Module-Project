class ArchiveMenu : Menu<Archive>(Constants.CREATE_ARCHIVE, mutableListOf()) {
    private val archives = mutableListOf<Archive>()

    init {
        items.add(MenuItem(Constants.CREATE_ARCHIVE) { createArchive() })
    }

    override fun show() {
        updateMenuItems()
        super.show()
    }

    private fun updateMenuItems() {
        items.removeAll { it.title != Constants.CREATE_ARCHIVE }
        archives.forEach { archive ->
            items.add(MenuItem(archive.name) { archive })
        }
    }

    override fun onItemSelected(item: Archive) {
        val noteMenu = NoteMenu(item)
        noteMenu.show()

        val updatedArchive = noteMenu.getCurrentArchive()
        val index = archives.indexOfFirst { it.name == item.name }
        if (index != -1) {
            archives[index] = updatedArchive
        }

        updateMenuItems()
    }

    private fun createArchive(): Archive {
        println("\nВведите название архива:")
        val name = readNonEmptyString(Constants.EMPTY_NAME_ERROR)
        return Archive(name).also {
            archives.add(it)
            println("Архив '$name' создан!")
            updateMenuItems()
        }
    }
}