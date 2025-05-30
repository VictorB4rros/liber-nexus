package model.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Livro {

	private Integer idLivro;
	private String tituloLivro;
	private String autorLivro;
	private Date dataPublicacao;
	private String genero;
	private String idioma;
	
	private List<Estoque> bibliotecasComEsseLivro = new ArrayList<>();
	private List<Emprestimo> listaDeEmprestimos = new ArrayList<>();
	
	public Livro() {
	}

	public Livro(Integer idLivro, String tituloLivro, String autorLivro, Date dataPublicacao, String genero,
			String idioma) {
		this.idLivro = idLivro;
		this.tituloLivro = tituloLivro;
		this.autorLivro = autorLivro;
		this.dataPublicacao = dataPublicacao;
		this.genero = genero;
		this.idioma = idioma;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public String getTituloLivro() {
		return tituloLivro;
	}

	public void setTituloLivro(String tituloLivro) {
		this.tituloLivro = tituloLivro;
	}

	public String getAutorLivro() {
		return autorLivro;
	}

	public void setAutorLivro(String autorLivro) {
		this.autorLivro = autorLivro;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public List<Estoque> getEstoques() {
		return bibliotecasComEsseLivro;
	}

	public List<Emprestimo> getListaDeEmprestimos() {
		return listaDeEmprestimos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idLivro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(idLivro, other.idLivro);
	}

	@Override
	public String toString() {
		return "Livro [idLivro=" + idLivro + ", tituloLivro=" + tituloLivro + ", autorLivro=" + autorLivro
				+ ", dataPublicacao=" + dataPublicacao + ", genero=" + genero + ", idioma=" + idioma + "]";
	}
	
	public void addEstoque(Estoque estoque) {
		bibliotecasComEsseLivro.add(estoque);
	}
	
	public void addEmprestimo(Emprestimo emprestimo) {
		listaDeEmprestimos.add(emprestimo);
	}
}
