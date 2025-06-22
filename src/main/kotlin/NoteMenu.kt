class NoteMenu(archive: Archive) : Menu<Note>("Архив: ${archive.name}", mutableListOf()) {
    private var currentArchive = archive

    init {
        items.add(MenuItem("Создать заметку") { createNote() })
    }

    override fun show() {
        updateNoteItems()
        super.show()
    }

    private fun updateNoteItems() {
        items.removeAll { it.title != "Создать заметку" }
        currentArchive.notes.forEach { note ->
            items.add(MenuItem(note.title) { note })
        }
    }

    override fun onItemSelected(item: Note) {
        showNoteContent(item)
    }

    private fun createNote(): Note {
        println("\nВведите название заметки:")
        val title = readNonEmptyString("Название не может быть пустым!")

        println("Введите текст заметки:")
        val content = readNonEmptyString("Текст не может быть пустым!")

        val newNote = Note(title, content)
        currentArchive = currentArchive.copy(notes = currentArchive.notes + newNote)
        println("Заметка '$title' создана!")
        updateNoteItems()
        return newNote
    }

    private fun showNoteContent(note: Note) {
        println("\n=== ${note.title} ===")
        println(note.content)
        println("\nНажмите Enter чтобы вернуться...")
        scanner.nextLine()
    }
}