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

//	@Autowired
//	public FlorService(WebClient webClient) {
//		this.webClient = webClient;
//	}

	public Mono<FlorDTO> addFlor(FlorDTO entity) {
		Mono<FlorDTO> mono = webClient.post().uri("http://localhost:9001/flor/add")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(entity), FlorDTO.class).retrieve().bodyToMono(FlorDTO.class)
				.timeout(Duration.ofMillis(10_000));
		return mono;

	}
	public Mono<FlorDTO> update(FlorDTO fe) {
		Mono<FlorDTO> mono = webClient.put()
				.uri("http://localhost:9001/flor/update")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(fe), FlorDTO.class)
				.retrieve()
				.bodyToMono(FlorDTO.class);
		return mono;
	}

	public Mono<FlorDTO> getFlor() {
		Mono<FlorDTO> mono = webClient.get().uri("/getOne/{id}", 1).retrieve().bodyToMono(FlorDTO.class);
		return mono;

	}

	public Flux<FlorDTO> findAll() {
		return webClient.get()
				.uri("http://localhost:9001/flor/getAll")
				.retrieve()
				.bodyToFlux(FlorDTO.class)
				.timeout(Duration.ofMillis(10_000));
	}

	public Mono<FlorDTO> findById(Integer id) {
		Mono<FlorDTO> mono = webClient.get()
				.uri("http://localhost:9001/flor/getOne/{id}", id)
				.retrieve().bodyToMono(FlorDTO.class);
		return mono;
	}

	public Mono<Void> delete(Integer id) {
		return webClient.delete()
				.uri("http://localhost:9001/flor/delete/" +id)
				.retrieve()
				.bodyToMono(Void.class);
	}

}
