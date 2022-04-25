package br.com.zup.handora.cadastrandocarrosunicos.carro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    boolean existsByPlaca(String placa);

    boolean existsByChassi(String chassi);

}
