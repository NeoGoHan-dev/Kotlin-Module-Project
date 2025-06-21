data class Archive(val name: String, val notes: List<Note> = emptyList())

data class Note(val title: String, val content: String)