

public class Produtos {

	private String descricao;
	private double preco;
	
	public Produtos() {
		
	}
	
	public Produtos(String descricao, double preco) {
		super();
		this.descricao = descricao;
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	
}
