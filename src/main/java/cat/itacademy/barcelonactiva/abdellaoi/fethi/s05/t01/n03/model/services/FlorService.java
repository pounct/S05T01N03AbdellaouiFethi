package cat.itacademy.barcelonactiva.abdellaoi.fethi.s05.t01.n03.model.services;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cat.itacademy.barcelonactiva.abdellaoi.fethi.s05.t01.n03.model.dto.FlorDTO;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class FlorService {
	
	private final WebClient webClient;
	
//	WebClient webClient = WebClient.builder() 
//		      .baseUrl( "http://localhost:9001/flor" )
//		      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
//		      .build();

	public Mono<FlorDTO> addFlor(FlorDTO entity) {
		Mono<FlorDTO> mono = webClient.post().uri("/add")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(entity), FlorDTO.class)
				.retrieve()
				.bodyToMono(FlorDTO.class)
				.timeout(Duration.ofMillis(10_000));
		return mono;

	}
	public Mono<FlorDTO> update(FlorDTO fe) {
		Mono<FlorDTO> mono = webClient.put()
				.uri("/update")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(fe), FlorDTO.class)
				.retrieve()
				.bodyToMono(FlorDTO.class)
				.timeout(Duration.ofMillis(10_000));
		return mono;
	}

	public Flux<FlorDTO> findAll() {
		return webClient.get()
				.uri("/getAll")
				.retrieve()
				.bodyToFlux(FlorDTO.class)
				.timeout(Duration.ofMillis(10_000));
	}

	public Mono<FlorDTO> findById(Integer id) {
		Mono<FlorDTO> mono = webClient.get()
				.uri("/getOne/{id}", id)
				.retrieve().bodyToMono(FlorDTO.class);
		return mono;
	}

	public Mono<Void> delete(Integer id) {
		return webClient.delete()
				.uri("/delete/" +id)
				.retrieve()
				.bodyToMono(Void.class);
	}

}
