package conta.modelo.beans;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable{
	private int id;
	private String nome;
	private String senha;
	private String saldo;
	private String credito;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getCredito() {
		return credito;
	}
	public void setCredito(String credito) {
		this.credito = credito;
	}
	@Override
	public int hashCode() {
		return Objects.hash(credito, id, nome, saldo, senha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(credito, other.credito) && id == other.id && Objects.equals(nome, other.nome)
				&& Objects.equals(saldo, other.saldo) && Objects.equals(senha, other.senha);
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", senha=" + senha + ", saldo=" + saldo + ", credito=" + credito
				+ "]";
	}

}
