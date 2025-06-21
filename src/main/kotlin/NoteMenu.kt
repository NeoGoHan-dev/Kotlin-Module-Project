class NoteMenu(private val archive: Archive) : Menu<Note>("Архив: ${archive.name}", mutableListOf()) {
    init {
        items.add(MenuItem("Создать заметку") { createNote() })
    }

    override fun show() {
        items.removeAll { it.title != "Создать заметку" }
        archive.notes.forEach { note ->
            items.add(MenuItem(note.title) { note })
        }
        super.show()
    }

    override fun onItemSelected(item: Note) {
        showNoteContent(item)
    }

    private fun createNote(): Note {
        println("\nВведите название заметки:")
        val title = readNonEmptyString("Название не может быть пустым!")

        println("Введите текст заметки:")
        val content = readNonEmptyString("Текст не может быть пустым!")

        return Note(title, content).also {
            archive.notes.add(it)
            println("Заметка '$title' создана!")
        }
    }

    private fun showNoteContent(note: Note) {
        println("\nЗаметка: ${note.title}")
        println("Текст: ${note.content}")
        println("\nНажмите Enter чтобы вернуться...")
        scanner.nextLine()
    }

    private fun readNonEmptyString(errorMessage: String): String {
        while (true) {
            val input = scanner.nextLine()
            if (input.isNotBlank()) return input
            println(errorMessage)
        }
    }
}