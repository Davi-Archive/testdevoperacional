package testdevoperacional.domain;

public class Cliente {
    private String cpf;
    private String nome;
    private String username;
    private Integer idade;

    public Cliente(final String cpf, final String nome,
	    final String username, final Integer idade) {
	super();
	this.cpf = cpf;
	this.nome = nome;
	this.username = username;
	this.idade = idade;
    }

    public String getCpf() {
	return cpf;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Integer getIdade() {
	return idade;
    }

    public void setIdade(Integer idade) {
	this.idade = idade;
    }

}
