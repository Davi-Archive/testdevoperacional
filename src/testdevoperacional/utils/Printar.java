package testdevoperacional.utils;

import java.util.List;
import java.util.stream.Collectors;

import testdevoperacional.domain.Produto;
import testdevoperacional.domain.Venda;

public class Printar {
    public static void printarBarra() {
	System.out.println("***************************"
		+ "*********************************");
    }

    public static void pularLinha() {
	System.out.println(" ");
    }

    public static String printarStatusDoCarrinho(
	    final List<Produto> carrinho) {
	return carrinho.isEmpty() ? "Nenhum"
		: carrinho.stream().map(Produto::getNome)
			.collect(Collectors.joining(", "));
    }

    public static void printarProduto(Produto produto) {
	System.out.println("Código: " + produto.getId());
	System.out.println("Produto: " + produto.getNome());
	System.out.println(
		"Quantidade em estoque: " + produto.getQuantidade());
	System.out.println("Valor: R$" + produto.getPreco());
    }

    public static void printarCodigoDaCompraEEmpresa(Venda venda) {
	System.out.println("Compra de código: " + venda.getCódigo()
		+ " na empresa " + venda.getEmpresa().getNome() + ": ");
    }
}
