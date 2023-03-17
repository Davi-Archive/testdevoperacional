package testdevoperacional.utils;

import java.util.Comparator;
import java.util.List;

import testdevoperacional.domain.Empresa;
import testdevoperacional.domain.Produto;
import testdevoperacional.domain.Venda;

public class PrintarLista {
    public static void printarEmpresasEmOrdemDeId(
	    List<Empresa> listToFilter) {
	listToFilter.stream().sorted(Comparator.comparing(Empresa::getId))
		.forEach(x -> {
		    System.out.println(x.getId() + " - " + x.getNome());
		});
    }

    public static void printarProdutosEmOrdemDeId(
	    final List<Produto> produtos, Integer escolhaEmpresa) {
	produtos.stream().sorted(Comparator.comparing(Produto::getId))
		.forEach(x -> {
		    if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
			System.out
				.println(x.getId() + " - " + x.getNome());
		    }
		});
    }

    public static void printarVendas(Venda venda) {
	venda.getItens().stream().forEach(x -> {
	    System.out.println(x.getId() + " - " + x.getNome() + "    R$"
		    + x.getPreco());
	});
    }

    public static void printarResumoDaCompra(List<Produto> carrinho,
	    Integer escolhaEmpresa) {
	carrinho.stream().forEach(x -> {
	    if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
		System.out.println(x.getId() + " - " + x.getNome()
			+ "    R$" + x.getPreco());
	    }
	});
    }
}
