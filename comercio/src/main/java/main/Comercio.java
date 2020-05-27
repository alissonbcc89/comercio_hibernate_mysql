package main;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexao.HibernateUtil;
import entidade.Categoria;
import entidade.Cliente;
import entidade.Empregado;
import entidade.Endereco;
import entidade.Pedido;
import entidade.Produto;

public class Comercio {
	
	private Session sessao = null;
	
	public Comercio(Session s) {
		this.sessao = s;
	}
	
	private Produto criarProdutoFilmeHobbit() {
		Categoria categoriaFilmes = new Categoria("Filme", "Categoria de filmes");
		sessao.save(categoriaFilmes);
		
		Produto produto = new Produto();
		produto.setDescricao("O Hobbit");
		produto.setPreco(29.99f);
		produto.setCategoria(categoriaFilmes);
		sessao.save(produto);
		
		return produto;
	}
	
		private Produto criarProdutoLivroPeregrino(){
		
				Categoria categoriaLivro = new Categoria("Livro", "Categoria de livros");
				sessao.save(categoriaLivro);
				Produto produto = new Produto();
				produto.setDescricao("O peregrino");
				produto.setPreco(15.75f);
				produto.setCategoria(categoriaLivro);
				sessao.save(produto);
				return produto;
		
			}
		
		private Produto criarProdutoAlimentoMistoQuente() {
			
			Categoria categoriaAlimentos = new Categoria("Alimento","Categoria de alimentos");
			sessao.save(categoriaAlimentos);
			Produto produto = new Produto();
			produto.setDescricao("Misto quente");
			produto.setPreco(1.99f);
			produto.setCategoria(categoriaAlimentos);
			sessao.save(produto);
			
			return produto;
		}
	
		private Cliente criarClienteBeltrano() {
		
			Cliente cliente = new Cliente();
			cliente.setNome("Beltrano");
			Endereco endereco = new Endereco();
			endereco.setRua("Rua dos Beltranos");
			endereco.setCidade("Blumenau");
			endereco.setCliente(cliente);
			cliente.setEndereco(endereco);
			
			sessao.save(cliente);
			
			return cliente;
		}
		
		private Cliente criarClienteFulano() {
			
			Cliente cliente = new Cliente();
			cliente.setNome("Fulano");
			Endereco endereco = new Endereco();
			endereco.setRua("Rua dos Fulanos");
			endereco.setCidade("Florianopolis");
			endereco.setCliente(cliente);
			cliente.setEndereco(endereco);
			
			sessao.save(cliente);
			
			return cliente;
		}

		public Empregado criarEmpregadoMelo() {
			
			Empregado chefe = new Empregado();
			chefe.setNome("Chefe");
			sessao.save(chefe);
			
			Empregado empregado1 = new Empregado();
			empregado1.setNome("Melo");
			empregado1.setChefe(chefe);
			sessao.save(empregado1);
			
			return empregado1;
			
			
			
		}
		
		public Empregado criarEmpregadoLuckow() {
			
			Empregado empregado1 = new Empregado();
			empregado1.setNome("Luckow");
			//empregado1.setChefe(chefe);
			sessao.save(empregado1);
			
			return empregado1;
		}
		
		public void criarPedidos() {
			
			Produto filmeHobbit = criarProdutoFilmeHobbit();
			Produto livroPeregrino = criarProdutoLivroPeregrino();
			Produto alimentoMistoQuente = criarProdutoAlimentoMistoQuente();
			Empregado empregadoLuckow = criarEmpregadoLuckow();
			Empregado empregadoMelo = criarEmpregadoMelo();
			
			
			Cliente clienteFulano = criarClienteFulano();
			Pedido pedido = new Pedido();
			pedido.setCliente(clienteFulano);
			pedido.setEmpregado(empregadoLuckow);
			pedido.setDescricao("Pedido do Sr Fulano");
			pedido.setDataPedido(new Date(System.currentTimeMillis()));
			pedido.setHoraPedido(new Time (System.currentTimeMillis()));
			Set<Produto> produtos = new HashSet<Produto>();
			pedido.setPorduto(produtos);
			produtos.add(filmeHobbit);
			produtos.add(livroPeregrino);
			sessao.save(pedido);
			
			
			Cliente clienteBeltrano =criarClienteBeltrano();
			pedido = new Pedido();
			pedido.setCliente(clienteBeltrano);
			pedido.setEmpregado(empregadoMelo);
			pedido.setDescricao("Pedido Sr. Beltrano");
			pedido.setDataPedido(new Date(System.currentTimeMillis()));
			pedido.setHoraPedido(new Time(System.currentTimeMillis()));
			produtos = new HashSet<Produto>();
			produtos.add(filmeHobbit);
			produtos.add(alimentoMistoQuente);
			sessao.save(pedido);		
			
		}
		
		
		public static void main(String[] args)
		{
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Comercio comercio = new Comercio(sessao);
			Transaction transacao = sessao.beginTransaction();
			comercio.criarPedidos();
			transacao.commit();
			
			System.out.println("Cadastrou");
		}
		
		
}




