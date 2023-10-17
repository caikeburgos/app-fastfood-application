package br.com.appfastfood.pedido.infraestrutura;

import br.com.appfastfood.pedido.infraestrutura.entidades.PedidoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SpringDataPedidoRepository extends JpaRepository<PedidoEntidade, Long> {
     PedidoEntidade findPedidoEntidadeById(Long id);

     @Query(value = "SELECT * FROM pedido p WHERE p.status not in ('FINALIZADO') ORDER BY p.id asc", nativeQuery = true)
     List<PedidoEntidade> findNotInFinalzado();
}
