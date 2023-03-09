package testdevoperacional.application;

import static testdevoperacional.application.VendaUseCase.criarVenda;
import static testdevoperacional.utils.MetodosDeStream.filtrarERetornarPrimeiro;
import static testdevoperacional.utils.Printar.printarBarra;
import static testdevoperacional.utils.Printar.pularLinha;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import testdevoperacional.domain.Cliente;
import testdevoperacional.domain.Empresa;
import testdevoperacional.domain.Produto;
import testdevoperacional.domain.Usuario;
import testdevoperacional.domain.Venda;

public class ClienteUseCase {

    public static void verCompraCliente(List<Venda> vendas,
	    Usuario usuarioLogado) {
	pularLinha();
	printarBarra();
	System.out.println("COMPRAS EFETUADAS");
	vendas.stream().forEach(venda -> {
	    if (venda.getCliente().getUsername()
		    .equals(usuarioLogado.getUsername())) {
		printarBarra();
		System.out.println("Compra de código: " + venda.getCódigo()
			+ " na empresa " + venda.getEmpresa().getNome()
			+ ": ");
		venda.getItens().stream().forEach(x -> {
		    System.out.println(x.getId() + " - " + x.getNome()
			    + "    R$" + x.getPreco());
		});
		System.out.println("Total: R$" + venda.getValor());
		printarBarra();
	    }

	});
    }

    public static void realizarCompraCliente(final List<Cliente> clientes,
	    final List<Empresa> empresas, final List<Produto> produtos,
	    final List<Produto> carrinho, final List<Venda> vendas,
	    final Scanner sc, final Usuario usuarioLogado) {
	System.out.println("Para realizar uma compra, escolha"
		+ "a empresa onde deseja comprar: ");
	empresas.stream().sorted(Comparator.comparing(Empresa::getId))
		.forEach(x -> {
		    System.out.println(x.getId() + " - " + x.getNome());
		});
	Integer escolhaEmpresa = sc.nextInt();
	Integer escolhaProduto = null;

	selecionarProduto(produtos, carrinho, sc, escolhaEmpresa,
		escolhaProduto);
	criarResumoDaCompra(clientes, empresas, carrinho, vendas,
		usuarioLogado, escolhaEmpresa);
	carrinho.clear();
    }

    private static void selecionarProduto(final List<Produto> produtos,
	    final List<Produto> carrinho, final Scanner sc,
	    Integer escolhaEmpresa, Integer escolhaProduto) {
	do {
	    if (escolhaProduto != null) {
		System.out.println("Produtos adicionados até o momento");
		String adicionadoCarrinho = carrinho.isEmpty() ? "Nenhum"
			: carrinho.stream().map(Produto::getNome)
				.collect(Collectors.joining(", "));
		System.out.println(adicionadoCarrinho);
		printarBarra();
		pularLinha();
	    }
	    mostrarProdutosAVenda(produtos, escolhaEmpresa);
	    escolhaProduto = sc.nextInt();
	    for (Produto produtoSearch : produtos) {
		if (produtoSearch.getId().equals(escolhaProduto)
			&& produtoSearch.getEmpresa().getId()
				.equals(escolhaEmpresa))
		    carrinho.add(produtoSearch);
	    }
	} while (escolhaProduto != 0);
    }

    private static void mostrarProdutosAVenda(final List<Produto> produtos,
	    Integer escolhaEmpresa) {
	System.out.println("Mostrar produtos: " + escolhaEmpresa);
	System.out.println("Escolha os seus produtos: ");
	produtos.stream().sorted(Comparator.comparing(Produto::getId))
		.forEach(x -> {
		    if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
			System.out
				.println(x.getId() + " - " + x.getNome());
		    }
		});
	System.out.println("0 - Finalizar compra");
    }

    private static void criarResumoDaCompra(List<Cliente> clientes,
	    List<Empresa> empresas, List<Produto> carrinho,
	    List<Venda> vendas, Usuario usuarioLogado,
	    Integer escolhaEmpresa) {
	pularLinha();
	printarBarra();
	System.out.println("Resumo da compra: ");
	carrinho.stream().forEach(x -> {
	    if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
		System.out.println(x.getId() + " - " + x.getNome()
			+ "    R$" + x.getPreco());
	    }
	});
	Empresa empresaEscolhida = filtrarERetornarPrimeiro(empresas,
		x -> x.getId().equals(escolhaEmpresa));

	Cliente clienteLogado = filtrarERetornarPrimeiro(clientes,
		x -> x.getUsername().equals(usuarioLogado.getUsername()));
	
	Venda venda = criarVenda(carrinho, empresaEscolhida, clienteLogado,
		vendas);
	pularLinha();
	System.out.println("Total: R$" + venda.getValor());
	printarBarra();
    }

}
