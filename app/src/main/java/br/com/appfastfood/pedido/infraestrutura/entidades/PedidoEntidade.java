package br.com.appfastfood.pedido.infraestrutura.entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "pedido")
public class PedidoEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected PedidoEntidade() {
    }

    @ElementCollection
    List<ProdEnt> produtos;
    private String clienteId;
    private Double valorTotal;
    private String status;
    private String tempoEspera;
    private String statusPagamento;

    public PedidoEntidade(List<ProdEnt> produtos, String clienteId, Double valorTotal,
            String status, String tempoEspera, String statusPagamento) {
        this.produtos = produtos;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.status = status;
        this.tempoEspera = tempoEspera;
        this.statusPagamento = statusPagamento;
    }

    public PedidoEntidade(Long id, List<ProdEnt> produtos, String clienteId, Double valorTotal,
            String status, String tempoEspera, String statusPagamento) {
        this.produtos = produtos;
        this.id = id;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.status = status;
        this.tempoEspera = tempoEspera;
        this.statusPagamento = statusPagamento;
    }

    public List<ProdEnt> getProdutos() {
        return produtos;
    }

    public Long getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClienteId() {
        return clienteId;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public String getTempoEspera() {
        return tempoEspera;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

}
