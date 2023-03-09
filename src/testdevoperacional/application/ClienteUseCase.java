package testdevoperacional.application;

import static testdevoperacional.application.VendaUseCase.criarVenda;
import static testdevoperacional.utils.MetodosDeStream.filtrarERetornarPrimeiro;
import static testdevoperacional.utils.Printar.printarBarra;
import static testdevoperacional.utils.Printar.printarStatusDoCarrinho;
import static testdevoperacional.utils.Printar.pularLinha;
import static testdevoperacional.utils.PrintarLista.printarEmpresasEmOrdemDeId;
import static testdevoperacional.utils.PrintarLista.printarProdutosEmOrdemDeId;
import static testdevoperacional.utils.PrintarLista.printarResumoDaCompra;
import static testdevoperacional.utils.PrintarLista.printarVendas;

import java.util.List;
import java.util.Scanner;

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

		printarVendas(venda);
		
		pularLinha();

		System.out.println("Total: R$" + venda.getValor());

		printarBarra();
		pularLinha();
	    }

	});
    }

    public static void realizarCompraCliente(final List<Cliente> clientes,
	    final List<Empresa> empresas, final List<Produto> produtos,
	    final List<Produto> carrinho, final List<Venda> vendas,
	    final Scanner sc, final Usuario usuarioLogado) {
	System.out.println("Para realizar uma compra, escolha"
		+ "a empresa onde deseja comprar: ");

	printarEmpresasEmOrdemDeId(empresas);

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
		String adicionadoCarrinho = printarStatusDoCarrinho(
			carrinho);

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

	printarProdutosEmOrdemDeId(produtos, escolhaEmpresa);

	System.out.println("0 - Finalizar compra");
    }

    private static void criarResumoDaCompra(List<Cliente> clientes,
	    List<Empresa> empresas, List<Produto> carrinho,
	    List<Venda> vendas, Usuario usuarioLogado,
	    Integer escolhaEmpresa) {
	pularLinha();
	printarBarra();
	System.out.println("Resumo da compra: ");

	printarResumoDaCompra(carrinho, escolhaEmpresa);

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
