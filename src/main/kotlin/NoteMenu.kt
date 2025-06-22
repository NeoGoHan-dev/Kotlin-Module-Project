class NoteMenu(initialArchive: Archive) : Menu<Note>("Архив: ${initialArchive.name}", mutableListOf()) {
    private var currentArchive = initialArchive

    fun getCurrentArchive(): Archive = currentArchive

    init {
        items.add(MenuItem(Constants.CREATE_NOTE) { createNote() })
    }

    override fun show() {
        updateMenuItems()
        super.show()
    }

    private fun updateMenuItems() {
        items.removeAll { it.title != Constants.CREATE_NOTE }
        currentArchive.notes.forEach { note ->
            items.add(MenuItem(note.title) { note })
        }
    }

    override fun onItemSelected(item: Note) {
        showNoteContent(item)
    }

    private fun createNote(): Note {
        println("\nВведите название заметки:")
        val title = readNonEmptyString(Constants.EMPTY_NAME_ERROR)

        println("Введите текст заметки:")
        val content = readNonEmptyString(Constants.EMPTY_CONTENT_ERROR)

        val newNote = Note(title, content)
        currentArchive = currentArchive.copy(notes = currentArchive.notes + newNote)
        println("Заметка '$title' создана!")
        updateMenuItems()
        return newNote
    }

    private fun showNoteContent(note: Note) {
        println("\n=== ${note.title} ===")
        println(note.content)
        println("\nНажмите Enter чтобы вернуться...")
        scanner.nextLine()
    }
}