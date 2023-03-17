package testdevoperacional;

import static testdevoperacional.application.ClienteUseCase.realizarCompraCliente;
import static testdevoperacional.application.ClienteUseCase.verCompraCliente;
import static testdevoperacional.application.EmpresaUseCase.listarVendasEmpresa;
import static testdevoperacional.application.EmpresaUseCase.verProdutosEmpresa;
import static testdevoperacional.infrastructure.BancoDeDados.pegarListaDeClientes;
import static testdevoperacional.infrastructure.BancoDeDados.pegarListaDeEmpresas;
import static testdevoperacional.infrastructure.BancoDeDados.pegarListaDeProdutos;
import static testdevoperacional.infrastructure.BancoDeDados.pegarListaDeUsuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import testdevoperacional.domain.Cliente;
import testdevoperacional.domain.Empresa;
import testdevoperacional.domain.Produto;
import testdevoperacional.domain.Usuario;
import testdevoperacional.domain.Venda;

public class Main {

    public static void main(String[] args) {

	List<Produto> carrinho = new ArrayList<>();
	List<Venda> vendas = new ArrayList<>();

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
	Integer escolha = null;

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
		escolha = sc.nextInt();
		switch (escolha) {
		case 1: {
		    listarVendasEmpresa(vendas, usuarioLogado);
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		    break;
		}
		case 2: {
		    verProdutosEmpresa(produtos, usuarioLogado);
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		    break;
		}
		case 0: {
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		    break;
		}
		default: {
		    System.out.println(
			    "Opção não encontrada, Empresa deslogada.");
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		    break;
		}
		}

	    } else {
		System.out.println("1 - Realizar Compras");
		System.out.println("2 - Ver Compras");
		System.out.println("0 - Deslogar");

		escolha = sc.nextInt();

		switch (escolha) {
		case 1: {
		    realizarCompraCliente(clientes, empresas, produtos,
			    carrinho, vendas, sc, usuarioLogado);
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		    break;
		}
		case 2: {
		    verCompraCliente(vendas, usuarioLogado);
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		    break;
		}
		case 0: {
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		    break;
		}
		default: {
		    System.out.println(
			    "Opção não encontrada, Usuário deslogada.");
		    executar(usuarios, clientes, empresas, produtos,
			    carrinho, vendas);
		    break;
		}

		}
	    }

	} else
	    System.out.println(
		    "Usuário não encontrado e/ou Senha incorreta");
    }
}
