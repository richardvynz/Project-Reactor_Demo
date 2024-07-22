package com.richardvinz.Reactive_Demo;

import com.richardvinz.Reactive_Demo.controller.ProductController;
import com.richardvinz.Reactive_Demo.dto.ProductDTO;
import com.richardvinz.Reactive_Demo.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
public class ReactiveDemoApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	@MockBean
	private ProductService productService;

	@Test
	public void testToSaveProduct(){
		Mono<ProductDTO> productDTOMono =
				Mono.just(new ProductDTO("108", "TV", 15,480.19));
		when(productService.saveProduct(productDTOMono)).thenReturn(productDTOMono);

		webTestClient.post().uri("/products")
				.body(Mono.just(productDTOMono),ProductDTO.class)
				.exchange()
				.expectStatus().isCreated();
	}

	@Test
	public void testToGetAllProducts(){
		Flux<ProductDTO> productDTOFlux =
				Flux.just(new ProductDTO("108", "TV", 15,480.19),new ProductDTO("158", "RADIO", 5,600.00));
				when(productService.getProducts()).thenReturn(productDTOFlux);

		Flux<ProductDTO> responseBody = webTestClient.get().uri("/products")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDTO.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(new ProductDTO("108", "TV", 15,480.19))
				.expectNext(new ProductDTO("158", "RADIO", 5,600.00))
				.verifyComplete();
	}

	@Test
	public void testToGetProduct(){
		Mono<ProductDTO> productDTOMono =
				Mono.just(new ProductDTO("108", "TV", 15,480.19));
		when(productService.getProductsById(any())).thenReturn(productDTOMono);

		Flux<ProductDTO> responseBody = webTestClient.get().uri("/products/102")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDTO.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNextMatches(p->p.getName().equals("TV"))
				.verifyComplete();
	}

	@Test
	public void testUpdateProduct(){
		Mono<ProductDTO> productDTOMono = Mono.just(new ProductDTO("108", "TV", 15,480.19));
		Mono<ProductDTO> updatedProduct = Mono.just(new ProductDTO("108", "TV", 60,900.19));

		when(productService.updateProduct(productDTOMono,"108")).thenReturn(updatedProduct);

		webTestClient.put().uri("/products/update/108")
				.body(Mono.just(updatedProduct), ProductDTO.class)
				.exchange()
				.expectStatus().isOk();

	}
	@Test
	public void testDeleteProduct(){
		Mono<ProductDTO> productDTOMono = Mono.just(new ProductDTO("108", "TV", 15,480.19));
		given(productService.deleteProduct(any())).willReturn(Mono.empty());

		webTestClient.delete().uri("/products/delete/108")
				.exchange()
				.expectStatus().isOk();
	}

}
