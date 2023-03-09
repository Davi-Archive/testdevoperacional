package testdevoperacional;

import static testdevoperacional.infrastructure.BancoDeDados.pegarListaDeClientes;
import static testdevoperacional.infrastructure.BancoDeDados.pegarListaDeEmpresas;
import static testdevoperacional.infrastructure.BancoDeDados.pegarListaDeProdutos;
import static testdevoperacional.infrastructure.BancoDeDados.pegarListaDeUsuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import testdevoperacional.application.ClienteUseCase;
import testdevoperacional.application.EmpresaUseCase;
import testdevoperacional.domain.Cliente;
import testdevoperacional.domain.Empresa;
import testdevoperacional.domain.Produto;
import testdevoperacional.domain.Usuario;
import testdevoperacional.domain.Venda;

public class Main {

    public static void main(String[] args) {

	List<Produto> carrinho = new ArrayList<Produto>();
	List<Venda> vendas = new ArrayList<Venda>();

	List<Usuario> usuarios = pegarListaDeUsuarios();
	List<Cliente> clientes = pegarListaDeClientes();
	List<Empresa> empresas = pegarListaDeEmpresas();
	List<Produto> produtos = pegarListaDeProdutos();
	executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
    }

    public static void executar(final List<Usuario> usuarios,
	    final List<Cliente> clientes, final List<Empresa> empresas,
	    final List<Produto> produtos, final List<Produto> carrinho,
	    final List<Venda> vendas) {
	Scanner sc = new Scanner(System.in);
	Integer escolha = sc.nextInt();

	System.out.println("Entre com seu usuário e senha:");
	System.out.print("Usuário: ");
	String username = sc.next();
	System.out.print("Senha: ");
	String senha = sc.next();

	List<Usuario> usuariosSearch = usuarios.stream()
		.filter(x -> x.getUsername().equals(username))
		.collect(Collectors.toList());

	if (!usuariosSearch.isEmpty()
		&& (usuariosSearch.get(0).getSenha().equals(senha))) {

	    Usuario usuarioLogado = usuariosSearch.get(0);

	    System.out.println("Escolha uma opção para iniciar");
	    if (usuarioLogado.IsEmpresa()) {
		System.out.println("1 - Listar vendas");
		System.out.println("2 - Ver produtos");
		System.out.println("0 - Deslogar");

		switch (escolha) {
		case 1: {
		    EmpresaUseCase.listarVendasEmpresa(vendas,
			    usuarioLogado);
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		}
		case 2: {
		    EmpresaUseCase.verProdutosEmpresa(produtos,
			    usuarioLogado);
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		}
		case 0: {
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		}
		default: {
		    System.out.println(
			    "Opção não encontrada, Empresa deslogada.");
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		}
		}

	    } else {
		System.out.println("1 - Relizar Compras");
		System.out.println("2 - Ver Compras");
		System.out.println("0 - Deslogar");

		switch (escolha) {
		case 1: {
		    ClienteUseCase.realizarCompraCliente(clientes,
			    empresas, produtos, carrinho, vendas, sc,
			    usuarioLogado);
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		}
		case 2: {
		    ClienteUseCase.verCompraCliente(vendas, usuarioLogado);
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		}
		case 0: {
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);

		}
		default: {
		    System.out.println(
			    "Opção não encontrada, Usuário deslogada.");
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		}

		}
	    }

	} else
	    System.out.println(
		    "Usuário não encontrado e/ou Senha incorreta");

    }
}
