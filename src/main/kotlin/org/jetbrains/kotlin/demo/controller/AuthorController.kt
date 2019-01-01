package org.jetbrains.kotlin.demo.controller

import org.jetbrains.kotlin.demo.model.Author
import org.springframework.web.bind.annotation.*
import org.jetbrains.kotlin.demo.config.ApplicationProperties
import org.jetbrains.kotlin.demo.repository.AuthorRepository
import org.springframework.http.HttpStatus
import java.io.File


@RestController
@RequestMapping("/authors")
class AuthorController(val authorRepository: AuthorRepository) {

    @GetMapping()
    fun index () : List<Author> {
        return authorRepository.getAuthorsFromStore()
    }

    @PostMapping()
    fun create (@RequestBody author: Author) {
        authorRepository.writeAuthorToStore(author)
    }

    @DeleteMapping()
    fun delete (@RequestBody author: Author) {
        var result = authorRepository.deleteAuthorFromStore(author)
        if (!result) {
            throw AuthorNotFoundException()
        }
    }

    /** Handle the error */
    @ExceptionHandler(AuthorNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleError(e: AuthorNotFoundException) = e.message


    class AuthorNotFoundException: Throwable()
}