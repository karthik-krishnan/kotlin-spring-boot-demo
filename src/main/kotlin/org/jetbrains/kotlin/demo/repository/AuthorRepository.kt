package org.jetbrains.kotlin.demo.repository

import org.jetbrains.kotlin.demo.config.ApplicationProperties
import org.jetbrains.kotlin.demo.model.Author
import org.springframework.stereotype.Component
import java.io.File

@Component
class AuthorRepository(val applicationProperties: ApplicationProperties) {

    fun getAuthorsFromStore(): MutableList<Author> {
        var authors = mutableListOf<Author>()
        File(applicationProperties.datafilePath + "/authors.txt").forEachLine {
            if (it.trim().isNotEmpty()) {
                val values = it.split(",")
                authors.add(Author(values[0], values[1].trim().toInt()))
            }
        }
        return authors
    }

    fun writeAuthorsToStore(authors: List<Author>) {
        File(applicationProperties.datafilePath + "/authors.txt").delete()
        authors.forEach {author -> writeAuthorToStore(author)}
    }

    fun writeAuthorToStore(author: Author) {
        File(applicationProperties.datafilePath + "/authors.txt").appendText("${author.name}, ${author.age}\n")
    }

    fun deleteAuthorFromStore(author: Author) : Boolean {
        var authors = getAuthorsFromStore()
        var result = authors.removeIf {it.name == author.name}
        writeAuthorsToStore(authors)
        return result
    }
}