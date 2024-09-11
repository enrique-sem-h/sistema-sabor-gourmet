/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Bem-vindo! Você é um cliente ou funcionário?");
            System.out.println("1. Cliente");
            System.out.println("2. Funcionário");
            System.out.println("3. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (escolha == 3) {
                System.out.println("Saindo...");
                break;
            }

            if (escolha == 1) {  // Cliente
                System.out.println("Digite 1 para cadastrar ou 2 para fazer login:");
                int opcaoCliente = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer

                if (opcaoCliente == 1) {  // Cadastro
                    System.out.println("Digite seu nome:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite seu email:");
                    String email = scanner.nextLine();
                    System.out.println("Digite seu endereço:");
                    String endereco = scanner.nextLine();
                    System.out.println("Digite seu telefone:");
                    String telefone = scanner.nextLine();
                    sistema.cadastrarCliente(nome, email, endereco, telefone);
                } else if (opcaoCliente == 2) {  // Login
                    System.out.println("Digite seu nome:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite seu email:");
                    String email = scanner.nextLine();
                    if (sistema.autenticarCliente(nome, email)) {
                        System.out.println("Bem-vindo ao sistema, " + nome);
                        clienteMenu(sistema);
                    }
                }
            } else if (escolha == 2) {  // Funcionário
                System.out.println("Digite seu nome:");
                String nome = scanner.nextLine();
                System.out.println("Digite seu código de funcionário:");
                int codigo = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer

                if (sistema.autenticarFuncionario(nome, codigo)) {
                    System.out.println("Bem-vindo ao sistema, " + nome);
                    funcionarioMenu(sistema);
                }
            }
        }
        scanner.close();
    }

    private static void clienteMenu(Sistema sistema) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Cliente:");
            System.out.println("1. Ver menu");
            System.out.println("2. Realizar pedido");
            System.out.println("3. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (escolha == 1) {
                sistema.mostrarMenu();
            } else if (escolha == 2) {
                System.out.println("Pedido realizado!");
                // Simula pedido, você pode adicionar funcionalidades adicionais aqui.
            } else if (escolha == 3) {
                break;
            }
        }
    }

    private static void funcionarioMenu(Sistema sistema) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Funcionário:");
            System.out.println("1. Ver pedidos");
            System.out.println("2. Atualizar estado de pedido");
            System.out.println("3. Cadastrar item no menu");
            System.out.println("4. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (escolha == 1) {
                System.out.println("Exibindo pedidos...");
                // Aqui, você pode listar os pedidos.
            } else if (escolha == 2) {
                System.out.println("Atualizando estado do pedido...");
                // Implementação de atualização do pedido.
            } else if (escolha == 3) {
                System.out.println("Cadastro de item:");
                System.out.println("Digite o nome do item:");
                String nomeItem = scanner.nextLine();
                System.out.println("Digite o preço do item:");
                float preco = scanner.nextFloat();
                scanner.nextLine();  // Limpar o buffer
                System.out.println("Digite a descrição do item:");
                String descricao = scanner.nextLine();
                System.out.println("Escolha o tipo de prato:");
                System.out.println("1. Entrada\n2. Principal\n3. Sobremesa\n4. Bebida");
                int tipoEscolha = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer
                TipoPrato tipoPrato = TipoPrato.values()[tipoEscolha - 1];

                Item item = new Item(nomeItem, preco, descricao, tipoPrato);
                sistema.getMenu().adicionarItem(item);
                System.out.println("Item cadastrado!");
            } else if (escolha == 4) {
                break;
            }
        }
    }
}

// Enums
enum TipoUsuario {
    Cliente,
    Funcionario
}

enum TipoPrato {
    Entrada,
    Principal,
    Sobremesa,
    Bebida
}

enum EstadoPedido {
    Recebido,
    EmPreparacao,
    ProntoParaEntrega,
    EmEntrega,
    Entregue
}

// Interface
class Usuario {
    protected String nome;
    protected String email;
    protected String endereco;
    protected String telefone;
    protected TipoUsuario tipo;
    
    public Usuario(String nome, String email, String endereco, String telefone) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}

// Classes
class Cliente extends Usuario {
    private Carrinho carrinho = new Carrinho();

    public Cliente(String nome, String email, String endereco, String telefone) {
        super(nome, email, endereco, telefone);
        this.tipo = TipoUsuario.Cliente;
    }

    public void pagarCarrinho() {
        float total = carrinho.calcularTotal();
        System.out.println("Pagamento realizado. Valor total: R$ " + total);
        carrinho.esvaziarCarrinho();
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }
}

class Funcionario extends Usuario {

    public Funcionario(String nome, String email, String endereco, String telefone) {
        super(nome, email, endereco, telefone);
        this.tipo = TipoUsuario.Funcionario;
    }

    public void cadastrarFuncionario(String nome, int codigo) {
        System.out.println("Funcionário cadastrado: " + nome + " - Código: " + codigo);
    }

    public void cadastrarItem(Item item, Menu menu) {
        menu.adicionarItem(item);
        System.out.println("Item cadastrado: " + item.getNome());
    }

    public void editarItem(Item item, String novoNome, float novoPreco, String novaDescricao) {
        item.setNome(novoNome);
        item.setPreco(novoPreco);
        item.setDescricao(novaDescricao);
        System.out.println("Item editado: " + item.getNome());
    }

    public void excluirItem(Item item, Menu menu) {
        menu.removerItem(item);
        System.out.println("Item excluído: " + item.getNome());
    }

    public void verPedido(Pedido pedido) {
        System.out.println("Pedido #" + pedido.getNumero() + " - Estado: " + pedido.getEstado());
    }

    public void atualizarPedido(Pedido pedido, EstadoPedido estado) {
        pedido.setEstado(estado);
        System.out.println("Pedido #" + pedido.getNumero() + " atualizado para: " + estado);
    }

    public void gerarRelatorio() {
        System.out.println("Relatório gerado.");
        // Implementar a lógica para gerar relatórios
    }

    public void estatisticasMaisVendidos() {
        System.out.println("Exibindo estatísticas dos itens mais vendidos.");
        // Implementar a lógica para exibir estatísticas
    }
}

class Sistema {
    private Usuario usuario;
    private Menu menu = new Menu();
    private Pedido[] pedidos = new Pedido[100];
    private int contadorPedidos = 0;

    // Adiciona o método getMenu()
    public Menu getMenu() {
        return menu;
    }

    public boolean autenticarCliente(String nome, String email) {
        if (usuario instanceof Cliente && usuario.nome.equals(nome) && usuario.email.equals(email)) {
            System.out.println("Cliente autenticado: " + nome);
            return true;
        } else {
            System.out.println("Falha na autenticação do cliente.");
            return false;
        }
    }

    public boolean cadastrarCliente(String nome, String email, String endereco, String telefone) {
        this.usuario = new Cliente(nome, email, endereco, telefone);
        System.out.println("Cliente cadastrado: " + nome);
        return true;
    }

    public boolean autenticarFuncionario(String nome, int codigo) {
        if (usuario instanceof Funcionario && usuario.nome.equals(nome)) {
            System.out.println("Funcionário autenticado: " + nome);
            return true;
        } else {
            System.out.println("Falha na autenticação do funcionário.");
            return false;
        }
    }

    public void realizarPedido(Cliente cliente) {
        Pedido novoPedido = new Pedido(++contadorPedidos, cliente, cliente.getCarrinho().getItens());
        novoPedido.setEstado(EstadoPedido.Recebido);
        pedidos[contadorPedidos - 1] = novoPedido;
        System.out.println("Pedido #" + novoPedido.getNumero() + " realizado.");
    }

    public void mostrarMenu() {
        menu.mostrarMenu();
    }
}


class Menu {
    private Item[] itens = new Item[50];
    private int contadorItens = 0;

    public void adicionarItem(Item item) {
        itens[contadorItens++] = item;
    }

    public void removerItem(Item item) {
        for (int i = 0; i < contadorItens; i++) {
            if (itens[i].equals(item)) {
                itens[i] = itens[--contadorItens];
                itens[contadorItens] = null;
                System.out.println("Item removido do menu: " + item.getNome());
                break;
            }
        }
    }

    public void mostrarMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < contadorItens; i++) {
            System.out.println(itens[i].getNome() + " - R$ " + itens[i].getPreco());
        }
    }

    public Item[] getItens() {
        return itens;
    }
}

class Item {
    private String nome;
    private float preco;
    private String descricao;
    private String personalizacao;
    private TipoPrato tipoPrato;

    public Item(String nome, float preco, String descricao, TipoPrato tipoPrato) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.tipoPrato = tipoPrato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPersonalizacao() {
        return personalizacao;
    }

    public void setPersonalizacao(String personalizacao) {
        this.personalizacao = personalizacao;
    }

    public TipoPrato getTipoPrato() {
        return tipoPrato;
    }
}

class Carrinho {
    private Item[] itens = new Item[20];
    private int contadorItens = 0;

    public void adicionarItem(Item item) {
        itens[contadorItens++] = item;
        System.out.println("Item adicionado ao carrinho: " + item.getNome());
    }

    public void removerItem(Item item) {
        for (int i = 0; i < contadorItens; i++) {
            if (itens[i].equals(item)) {
                itens[i] = itens[--contadorItens];
                itens[contadorItens] = null;
                System.out.println("Item removido do carrinho: " + item.getNome());
                break;
            }
        }
    }

    public void listarCarrinho() {
        System.out.println("Itens no carrinho:");
        for (int i = 0; i < contadorItens; i++) {
            System.out.println(itens[i].getNome() + " - R$ " + itens[i].getPreco());
        }
    }

    public Item[] getItens() {
        return itens;
    }

    public float calcularTotal() {
        float total = 0;
        for (int i = 0; i < contadorItens; i++) {
            total += itens[i].getPreco();
        }
        return total;
    }

    public void esvaziarCarrinho() {
        itens = new Item[20];
        contadorItens = 0;
        System.out.println("Carrinho esvaziado.");
    }
}

class Pedido {
    private int numero;
    private Cliente cliente;
    private Item[] itens;
    private EstadoPedido estado;

    public Pedido(int numero, Cliente cliente, Item[] itens) {
        this.numero = numero;
        this.cliente = cliente;
        this.itens = itens;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Item[] getItens() {
        return itens;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
}
