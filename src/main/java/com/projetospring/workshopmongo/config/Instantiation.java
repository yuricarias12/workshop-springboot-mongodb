package com.projetospring.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.projetospring.workshopmongo.domain.Post;
import com.projetospring.workshopmongo.domain.User;
import com.projetospring.workshopmongo.dto.AuthorDTO;
import com.projetospring.workshopmongo.dto.CommentDTO;
import com.projetospring.workshopmongo.repository.PostRepository;
import com.projetospring.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User yuri = new User(null, "Yuri Carias", "yuristricken7@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob, yuri));
		
		Post post1 = new Post(null, sdf.parse("01/03/2024"), "Consegui meu estágio", "Vou Estagiar no Pagbank!", new AuthorDTO(yuri));
		Post post2 = new Post(null, sdf.parse("06/03/2024"), "Começo hoje meu estágio", "Partiu programar!", new AuthorDTO(yuri));
		
		CommentDTO c1 = new CommentDTO("Parabéns!", sdf.parse("01/03/2024"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Parabéns, muito sucesso!", sdf.parse("02/03/2024"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Parabéns, você merece!", sdf.parse("06/03/2024"), new AuthorDTO(maria));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		yuri.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(yuri);
		
	}

}
