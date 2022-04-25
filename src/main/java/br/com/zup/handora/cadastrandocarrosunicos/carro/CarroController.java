package br.com.zup.handora.cadastrandocarrosunicos.carro;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(CarroController.BASE_URI)
public class CarroController {

    public final static String BASE_URI = "/carros";

    private final CarroRepository carroRepository;

    public CarroController(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CarroRequest carroRequest,
                                    UriComponentsBuilder ucb) {
        if (carroRepository.existsByPlaca(carroRequest.getPlaca())) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "O carro com essa placa já está cadastrado."
            );
        }

        if (carroRepository.existsByChassi(carroRequest.getChassi())) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "O carro com esse chassi já está cadastrado."
            );
        }

        Carro carro = carroRepository.save(carroRequest.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(carro.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
