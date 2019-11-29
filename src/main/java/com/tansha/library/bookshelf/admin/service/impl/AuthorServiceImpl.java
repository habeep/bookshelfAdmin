package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.Author;
import com.tansha.library.bookshelf.admin.repository.AuthorRepository;
import com.tansha.library.bookshelf.admin.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	@Override
	public Author getAuthorById(int authorId) {
		Author obj = authorRepository.findByAuthorID(authorId);
		return obj;
	}	
	@Override
	public List<Author> getAllAuthors(){
		List<Author> list = new ArrayList<>();
		authorRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addAuthor(Author author){
	        Author list = authorRepository.findByAuthorName(author.getAuthorName()); 	
                if (list != null ) {
    	           return false;
                } else {
                	author.setIsActive(1);
    	        authorRepository.save(author);
    	        return true;
       }
	}
	@Override
	public void updateAuthor(Author author) {
		authorRepository.save(author);
	}
	@Override
	public void deleteAuthor(int authorId) {
		authorRepository.delete(getAuthorById(authorId));
	}
	@Override
	public Integer getAuthorByNameQuery(String authorName) {
		Integer authorId=0;
		if(authorRepository.findByAuthorNameQuery(authorName) == null ) {
			Author author = new Author();
			author.setAuthorName(authorName);
			author.setIsActive(1);
			authorRepository.save(author);
			authorId = authorRepository.findByAuthorNameQuery(authorName);
		} else {
			authorId = authorRepository.findByAuthorNameQuery(authorName);
		}
		// TODO Auto-generated method stub
		return authorId;
	}
}
