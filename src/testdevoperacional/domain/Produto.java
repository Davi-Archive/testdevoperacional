package testdevoperacional.domain;

public class Produto {
    private Integer id;
    private String nome;
    private Integer quantidade;
    private Double preco;
    private Empresa empresa;

    public Produto(final Integer id, final String nome,
	    final Integer quantidade, final Double preco,
	    final Empresa empresa) {
	super();
	this.id = id;
	this.nome = nome;
	this.quantidade = quantidade;
	this.preco = preco;
	this.empresa = empresa;
    }

    public Produto() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Empresa getEmpresa() {
	return empresa;
    }

    public void setEmpresa(Empresa empresa) {
	this.empresa = empresa;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
    }

    public Double getPreco() {
	return preco;
    }

    public void setPreco(Double preco) {
	this.preco = preco;
    }

}
