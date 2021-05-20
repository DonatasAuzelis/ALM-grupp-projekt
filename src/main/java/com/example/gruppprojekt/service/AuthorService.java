package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Author;
import com.example.gruppprojekt.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    /**
     * adds an author object
     *
     * @param author
     * @return author object
     */
    public Author addAuthor(Author author) {
        return authorRepo.save(author);
    }

    /**
     * to get a list of the authors
     *
     * @return a list of Author
     */
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    /**
     * deletes an author by sending its id
     *
     * @param id as String
     * @return return a string with a message
     */
    public String deleteAuthorById(String id) {
        Author author = authorRepo.findById(id).get();
        authorRepo.deleteById(id);
        return "Author deleted with " + id + " and named: " + author.getFirstName() + ", " + author.getLastName();
    }

     /** updates an author
     *
     * @param author Author object
     * @return updated Author object
     */
    public Author updateAuthor(Author author) throws Exception {
        Optional<Author> authorInDb = authorRepo.findById(author.getId());
        if (authorInDb.isPresent() && author.getId()!=null && author.getFirstName() != null && author.getLastName() != null && author.getDateOfBirth() != null) {
            author.setCreatedDate(authorInDb.get().getCreatedDate());
            return authorRepo.save(author);
        } else
            throw new Exception("Author object is not in right format, check all its properties are in:" +
                    "\nId,firstName,lastName,dateOfBirth");

    }

    /**
     * add a list of authors
     *
     * @param authors List<Authors>
     * @return returns all authors in db
     */
    public List<Author> addAuthors(List<Author> authors) {
        return authorRepo.saveAll(authors);
    }
}
