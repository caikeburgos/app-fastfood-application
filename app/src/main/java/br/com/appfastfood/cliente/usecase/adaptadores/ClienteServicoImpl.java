
package br.com.appfastfood.cliente.usecase.adaptadores;
 
import br.com.appfastfood.cliente.dominio.modelos.Cliente;
import br.com.appfastfood.cliente.dominio.repositorios.ClienteRepositorio;
import br.com.appfastfood.cliente.exceptions.ExceptionsMessages;
import br.com.appfastfood.cliente.infraestrutura.entidades.EntidadeCliente;
import br.com.appfastfood.cliente.usecase.portas.ClienteServico;
import br.com.appfastfood.configuracoes.execption.BadRequestException;

import java.util.UUID;


public class ClienteServicoImpl implements ClienteServico {
    private final ClienteRepositorio clienteRepositorio;

    public ClienteServicoImpl(final ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public UUID cadastrar(String nome, String cpf, String email) {
        Cliente cliente = new Cliente(nome,cpf,email);
        UUID id = clienteRepositorio.cadastrar(cliente);
        return id;
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        if (clienteRepositorio.buscarPorCpf(cpf).isPresent()) {
            EntidadeCliente entidade = clienteRepositorio.buscarPorCpf(cpf).get();
            return new Cliente(entidade.getNome(), entidade.getCpf(), entidade.getEmail());
        }
        throw new BadRequestException(ExceptionsMessages.CLIENTE_NAO_ENCONTRADO.getValue());
    }
}

