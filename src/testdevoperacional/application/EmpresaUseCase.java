package testdevoperacional.application;

import static testdevoperacional.utils.Printar.printarBarra;
import static testdevoperacional.utils.Printar.printarProduto;
import static testdevoperacional.utils.Printar.pularLinha;
import static testdevoperacional.utils.PrintarLista.printarVendas;

import java.util.List;

import testdevoperacional.domain.Produto;
import testdevoperacional.domain.Usuario;
import testdevoperacional.domain.Venda;

public class EmpresaUseCase {
    
    public static void verProdutosEmpresa(List<Produto> produtos,
	    Usuario usuarioLogado) {
	pularLinha();
	printarBarra();
	System.out.println("MEUS PRODUTOS");
	produtos.stream().forEach(produto -> {
	    if (produto.getEmpresa().getId()
		    .equals(usuarioLogado.getEmpresa().getId())) {
		
		printarBarra();
		
		printarProduto(produto);
		
		printarBarra();
	    }

	});
	System.out.println(
		"Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
	printarBarra();
    }

    public static void listarVendasEmpresa(List<Venda> vendas,
	    Usuario usuarioLogado) {
	
	pularLinha();
	printarBarra();
	System.out.println("VENDAS EFETUADAS");
	vendas.stream().forEach(venda -> {
	    if (venda.getEmpresa().getId()
		    .equals(usuarioLogado.getEmpresa().getId())) {
		printarBarra();
		System.out.println("Venda de código: " + venda.getCódigo()
			+ " no CPF " + venda.getCliente().getCpf() + ": ");
		
		printarVendas(venda);
			
		
		System.out.println("Total Venda: R$" + venda.getValor());
		System.out.println("Total Taxa a ser paga: R$"
			+ venda.getComissaoSistema());
		System.out.println("Total Líquido  para empresa: R$"
			+ (venda.getValor() - venda.getComissaoSistema()));
		printarBarra();
	    }

	});
	
	System.out.println(
		"Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
	printarBarra();
    }
}
