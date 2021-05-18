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

    public Author addAuthor(Author author) {
        return authorRepo.save(author);
    }

    public List<Author> getAllAuthors() { return authorRepo.findAll();
    }

    public String deleteAuthorById(String id) {
        Author author=authorRepo.findById(id).get();
        authorRepo.deleteById(id);

        return "Author deleted with " + id + " and named: "+ author.getFirstName() + ", " + author.getLastName();
    }

    public Author updateAuthor(String id, Author author) throws ClassNotFoundException {
        Optional<Author> newAuthor =authorRepo.findById(id);
        if(newAuthor.isPresent()){
            newAuthor.get().setFirstName(author.getFirstName());
            newAuthor.get().setLastName(author.getLastName());
            newAuthor.get().setDateOfbirth(author.getDateOfbirth());
           return  authorRepo.save(newAuthor.get());
        }
        else{
            throw new ClassNotFoundException();
        }

    }
}
