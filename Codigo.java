import java.util.ArrayList;
import java.util.List;

class Cliente {
    
    private String nome;
    private String contato;
    
    public Cliente(String nome, String contato){
        this.nome = nome;
        this.contato = contato; 
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getContato(){
        return contato;
    }
}

class Garcom {
    
    private String nome;
    
    public Garcom(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
}

class ItemMenu {
    
    private String nome;
    private String descricao;
    private double preco;
    
    public ItemMenu(String nome, String descricao, double preco){
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public double getPreco(){ 
        return preco;
    }
}

class ItemPedido {
    
    private ItemMenu itemMenu;
    private int quantidade;
    
    public ItemPedido(ItemMenu itemMenu, int quantidade){
        this.itemMenu = itemMenu;
        this.quantidade = quantidade;
    }
    
    public ItemMenu getItemMenu(){
        return itemMenu;
    }
    
    public int getQuantidade(){
        return quantidade;
    }
    
    public double calcularSubtotal(){ 
        return itemMenu.getPreco() * quantidade;
    }
}

class Pedido {
    private Cliente cliente;
    private Garcom garcom;
    private List<ItemPedido> itens;
    
    public Pedido(Cliente cliente, Garcom garcom){
        this.cliente = cliente;
        this.garcom = garcom;
        this.itens = new ArrayList<>();
    }
    
    public void adicionarItem(ItemPedido item){ 
        itens.add(item);
    }
    
    public double calcularTotal(){
        double total = 0;
        
        for(ItemPedido item : itens){
            total += item.calcularSubtotal();
        }
        return total;
    }
    
    public void imprimirRelatorio(){
        System.out.println("Pedido para: " + cliente.getNome());
        System.out.println("Atendido por: " + garcom.getNome());
        System.out.println("Itens do Pedido:");
        
        for (ItemPedido item : itens){ 
            System.out.println("- " + item.getItemMenu().getNome() + " (x" + item.getQuantidade() + "): R$ " + item.calcularSubtotal());
        }
        
        System.out.println("Total: R$ " + calcularTotal());
    }
}

public class Main {
    public static void main(String[] args) {
        
        Cliente c1 = new Cliente("Mariana", "0000-0000");
        Cliente c2 = new Cliente("Enrique", "1111-1111");
        
        Garcom g1 = new Garcom("Bruno");
        Garcom g2 = new Garcom("Gabriel");
        
        ItemMenu item1 = new ItemMenu("Sushi", "Hot philadelphia", 30.00); 
        ItemMenu item2 = new ItemMenu("Pizza", "Pizza de Calabresa", 50.00); 
        ItemMenu item3 = new ItemMenu("Salada", "Salada Caesar", 20.00); 
        
        Pedido pedido1 = new Pedido(c1, g1);
        pedido1.adicionarItem(new ItemPedido(item1, 2));
        pedido1.adicionarItem(new ItemPedido(item2, 3)); 
        
        Pedido pedido2 = new Pedido(c2, g2);
        pedido2.adicionarItem(new ItemPedido(item3, 4));
        
        pedido1.imprimirRelatorio();
        System.out.println();
        pedido2.imprimirRelatorio();
    }
}
