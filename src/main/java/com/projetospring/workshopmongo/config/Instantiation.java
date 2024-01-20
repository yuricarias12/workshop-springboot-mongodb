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
		
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}

}
