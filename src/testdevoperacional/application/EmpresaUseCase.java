package testdevoperacional.application;

import java.util.List;

import testdevoperacional.domain.Produto;
import testdevoperacional.domain.Usuario;
import testdevoperacional.domain.Venda;

public class EmpresaUseCase {
    public static void verProdutosEmpresa(List<Produto> produtos,
	    Usuario usuarioLogado) {
	System.out.println();
	System.out.println(
		"************************************************************");
	System.out.println("MEUS PRODUTOS");
	produtos.stream().forEach(produto -> {
	    if (produto.getEmpresa().getId()
		    .equals(usuarioLogado.getEmpresa().getId())) {
		System.out.println(
			"************************************************************");
		System.out.println("Código: " + produto.getId());
		System.out.println("Produto: " + produto.getNome());
		System.out.println("Quantidade em estoque: "
			+ produto.getQuantidade());
		System.out.println("Valor: R$" + produto.getPreco());
		System.out.println(
			"************************************************************");
	    }

	});
	System.out.println(
		"Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
	System.out.println(
		"************************************************************");
    }

    public static void listarVendasEmpresa(List<Venda> vendas,
	    Usuario usuarioLogado) {
	System.out.println();
	System.out.println(
		"************************************************************");
	System.out.println("VENDAS EFETUADAS");
	vendas.stream().forEach(venda -> {
	    if (venda.getEmpresa().getId()
		    .equals(usuarioLogado.getEmpresa().getId())) {
		System.out.println(
			"************************************************************");
		System.out.println("Venda de código: " + venda.getCódigo()
			+ " no CPF " + venda.getCliente().getCpf() + ": ");
		venda.getItens().stream().forEach(x -> {
		    System.out.println(x.getId() + " - " + x.getNome()
			    + "    R$" + x.getPreco());
		});
		System.out.println("Total Venda: R$" + venda.getValor());
		System.out.println("Total Taxa a ser paga: R$"
			+ venda.getComissaoSistema());
		System.out.println("Total Líquido  para empresa"
			+ (venda.getValor() - venda.getComissaoSistema()));
		System.out.println(
			"************************************************************");
	    }

	});
	System.out.println(
		"Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
	System.out.println(
		"************************************************************");
    }
}
