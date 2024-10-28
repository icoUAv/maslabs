import ementas.*;

import java.time.LocalDateTime;
import java.util.Random;


/**
 * ponto de arranque da aplicação para demonstração
 * o código principal está na subpasta ementas
 */
public class DemoMain {


    public static void main(String[] args) {
        print("\n\nA preparar os dados aleatórios...");
        Ementa ementaHoje = novaEmentaAleatoria("Menu Primavera", "Loja 1", 2);

        print("\n\nEmenta para hoje:" + ementaHoje.toString() );

        // criar um pedido
        Cliente cliente = new Cliente("Joao Pinto", "PT120200200");
        Pedido pedido = new Pedido(cliente, LocalDateTime.now());

        // adiciona um prato à sorte, da ementa do dia
        Prato opcao = ementaHoje.escolherPratoAleatorio();
        pedido.adicionarPrato(opcao);

        // adiciona outro prato à sorte, da ementa do dia
        opcao = ementaHoje.escolherPratoAleatorio();
        pedido.adicionarPrato(opcao);

        print(); print("Pedido gerado");
        print("Pedido: " + pedido);
        double precoTotal = pedido.calcularTotal();
        print("Preço do Pedido: " + precoTotal);
        double calorias = pedido.calcularCalorias();
        print("Calorias do Pedido: " + calorias);
    }


    /*
     * Cria um novo objeto do tipo Ementa e preecnhe com  a designação
     * fornecida em parâmetro, e acrescenta aleatoriamente a quantidade de pratos pretendida
     */
    private static Ementa novaEmentaAleatoria(String designacao, String local, int quantidadePratos) {
        Ementa ementa = new Ementa(designacao, local, LocalDateTime.now());

        for (int nrOpcaoPrato = 0; nrOpcaoPrato < Ementa.NR_PRATOS_NA_EMENTA; nrOpcaoPrato++) {

            Prato prato = gerarPratoAleatorio(nrOpcaoPrato + 1);
            print("A gerar .. " + prato);

            // Adiciona 2 ingredientes a cada prato
            int nrIngrediente = 1;
            do {
                Alimento aux = escolherUmAlimentoAleatorio();
                if( prato.getListaAlimentos().contains(aux)){
                    print("Ingrediente " + nrIngrediente + " repetido: " + aux);

                }else if (prato.adicionarIngrediente(aux)) {
                    print("\tIngrediente " + nrIngrediente + " adicionado: " + aux);
                    nrIngrediente++;
                } else
                    print("\tERRO: ingrediente sorteado nao é adequado " + nrIngrediente + ": " + aux);

            } while (nrIngrediente <= quantidadePratos);

            ementa.addPrato(prato);
        }
        return ementa;
    }

    /*
     * Gera uma ocorrencia de Alimento, com dados aleatorios
     */
    public static Alimento escolherUmAlimentoAleatorio() {

        Alimento resultado;
        switch (geradorIntAleatorios.nextInt(4)) {
            case 0:
                resultado = new Carne(VariedadeCarne.FRANGO, 22.3, 345.3, 300);
                break;
            case 1:
                resultado = new Peixe(TipoPeixe.CONGELADO, 31.3, 25.3, 200);
                break;
            case 2:
                resultado = new Legume("Couve Flor", 21.3, 22.4, 150);
                break;
            default:
                resultado = new Cereal("Milho", 19.3, 32.4, 110);
                break;
        }
        return resultado;
    }

    /*
     * Gera uma ocorrencia de Prato, com dados aleatorios
     */
    public static Prato gerarPratoAleatorio(int i) {
        Prato resultado;
        switch (geradorIntAleatorios.nextInt(3)) {
            case 0:
                resultado = new Prato("Combinado n." + i, 100.0);
                break;
            case 1:
                resultado = new PratoVegetariano("Vegetariano n." + i, 120.0);
                break;
            default:
                resultado = new PratoDieta("Dieta n." + i, 200, 90.8);
                break;
        }
        return resultado;
    }


    public static Alimento randAlimento() {
        throw new UnsupportedOperationException();
    }


    public static void print() {
        System.out.println();
    }
    public static void print(String message){
        System.out.println( message);
    }

    static final Random geradorIntAleatorios = new Random();

}
