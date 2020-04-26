/*
 * Antonio Fernanandes
 * Desciplina: Analise sentese de algoritomos(ASA)
 */
package tabelaclassificativa;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Gabarito
 */
public class TabelaClassificativa {

    /**
     * @throws java.io.IOException
     */
    
    //Cores para pensolanilar output e interacoes
    public static final String BLUE   = "\033[0;34m";  // BLUE
    public static final String GREEN  = "\033[0;32m";  // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String RED    = "\033[0;31m";  // RED
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
     public static final String PURPLE = "\033[0;35m";  // PURPLE
    
    public static Scanner entrada = new Scanner(System.in);
    public static ArrayList<DescricaoDasEquipas> dados = new ArrayList<>();
    public static File DataBase = new File("DataBase.txt");
    
    // DECLARACAO DE DADOS TODOS DE FORMA GLOBAL PARA FACILITAR A CODIFICACOA(mais nao e o foco aqui...) 
    public static String nome;
    public static int n_vitorias,n_empates,n_derotas,g_marcados,g_sofridos,pontos,d_golos;
    public static int i = 0;
    
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
           String opcao;
            CaregarDadosPreRegistados();
       
            do{
                 limparTela();
                 menu();
                 System.out.print("Escolha o menu: "); opcao = entrada.next();
            switch(opcao){
                case "1": 
                    limparTela();
                    if(i<=19){
                     enserirDadosEquipa();
                      while(i<19){
                           System.out.println("\n"+YELLOW+"OBS: So é possivel insirir dados de mais "+(20-i)+" equipas");
                           System.out.print("Deseja ensirir mais equipa(S para sim ou qualquer outro caratere para negar... ): ");
                           String sim = entrada.next();
                           if(sim.equalsIgnoreCase("s")){
                           enserirDadosEquipa();
                            }else{
                               break;
                           }
                        }
                    }else{
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t"+RED+"NAO É POSSIVEL REALIZAR OPERAÇÃO! Limite maximo de equipa foi atingida... ");
                         TimeUnit.SECONDS.sleep(3);
                    }
                    break;
                 case "2":
                       limparTela();
                     Collections.sort(dados,DescricaoDasEquipas.getComparatorIdadeDescNomeCresc());
                     apresentarDadosEquipas();
                     System.out.println("\n"+YELLOW+"Click enter para continuar..."); entrada.nextLine();entrada.nextLine();
                    break;
                 case "3":
                    removerEquipa();
                    break;
                 case "4":
                      limparTela();
                     System.out.println("\n\n\n\n\n\n\ntEsta operacao apagara todos os dados do sistemas. \"Deseja continuar?");
                     System.out.print(YELLOW+"(S para sim ou qualquer outro caratere para necancelargar... ): ");
                     String sim = entrada.next();
                     limparTela();
                           if(sim.equalsIgnoreCase("s")){
                               System.out.print("\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t"+BLUE+"Restaurando..."); TimeUnit.SECONDS.sleep(1); limparTela();
                               System.out.print("\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t"+YELLOW+"Restaurando..."); TimeUnit.SECONDS.sleep(1); limparTela();
                               System.out.print("\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t"+RED+"Restaurando..."); TimeUnit.SECONDS.sleep(1); limparTela();
                               dados.clear();
                               System.out.print("\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t"+GREEN+"Restaurando..."); TimeUnit.SECONDS.sleep(1); limparTela();
                               System.out.print("\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t"+PURPLE+"Restaurando..."); TimeUnit.SECONDS.sleep(1); limparTela();
                               System.out.print("\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t"+BLUE_BACKGROUND+"Restaurando..."); TimeUnit.SECONDS.sleep(2); limparTela();
                               System.out.println("\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t"+GREEN+"RESTAURACAO CONCLUIDO COM SUCESSO.");
                               TimeUnit.SECONDS.sleep(3);
                           }
                    break;
                  case "0":
                   
                    break;
                
                default:System.out.println(YELLOW+"OPCAO INVALIDO! Please escola um dos menus abaixo...");
            }
         }while(!"0".equals(opcao));
            
              guardarDadosPermanente();
    }
        //Exibe os nomes dos Equipas
    public static void apresentarDadosEquipas(){
        System.out.println("================================================================");
        System.out.println("\t\t TABELA DE CLASSIFICATIVA");
        System.out.println("================================================================");
        
       System.out.printf(YELLOW+"%-7s %-17s %-4s  %-5s %-5s %-5s %-5s %-5s %-5s\n","pos","Nome equipa","pts","DG","NV","NE","ND","GM","GS");
         for(int i = 0; i < dados.size(); i++){
            System.out.printf(BLUE_BACKGROUND_BRIGHT+"%-2dº -> ",i+1);
            System.out.printf(BLUE_BACKGROUND_BRIGHT+BLUE_BACKGROUND+"%-18s %-5d %-4d",dados.get(i).nome,dados.get(i).pontos,dados.get(i).d_golos);  
                                          
            System.out.printf(BLUE_BACKGROUND_BRIGHT+"  %-5d %-5d %-5d %-5d %-5d\n",dados.get(i).n_vitorias,
                                           dados.get(i).n_empates,
                                           dados.get(i).n_derotas,
                                           dados.get(i).g_marcados,
                                           dados.get(i).g_sofridos);
        }
    }

    private static void enserirDadosEquipa() {
       System.out.println("\n"+YELLOW+"ENTRE COM DADOS DA EQUIPA...\n");
       
       try{
         System.out.print("Nome: ");                  nome = entrada.next();
         System.out.print("Nº de vitorias: ");        n_vitorias = entrada.nextInt();
         System.out.print("Nº de empates: ");         n_empates = entrada.nextInt();
         System.out.print("Nº de derotas: ");         n_derotas = entrada.nextInt();
         System.out.print("Nº de golos marcados: ");  g_marcados = entrada.nextInt();
         System.out.print("Nº de golos sofridos: ");  g_sofridos = entrada.nextInt();
       }catch(Exception e){
           System.out.println(RED+"Erro algum campo invalido.\"Renicio o cadastro novamente\"");
           System.out.println(RED+"ATENCAO!cada campo tem o seu tipo de dado.");
         enserirDadosEquipa();
           
       } 
         pontos = n_vitorias * 3 + n_empates;
         d_golos = g_marcados - g_sofridos;  
         
        dados.add(new DescricaoDasEquipas(nome,pontos,d_golos,n_vitorias,n_empates,n_derotas,g_marcados,g_sofridos));
        i++;
   limparTela();
    }

    private static void guardarDadosPermanente() throws IOException {
        FileWriter escrever = new FileWriter(DataBase);
        
        try{
            
             for(int i = 0; i < dados.size(); i++){
             escrever.write(dados.get(i).nome+";"+ dados.get(i).pontos+";"+ dados.get(i).d_golos  +";"+ dados.get(i).n_vitorias +";"+
                            dados.get(i).n_empates+";"+ dados.get(i).n_derotas+";"+ dados.get(i).g_marcados+";"+ dados.get(i).g_sofridos+"\n" );
             }
             
             escrever.close();
            
        }catch(IOException e){
            System.out.print("Erro do tipo: " + e.getMessage());
        }
    }

    private static void CaregarDadosPreRegistados() throws FileNotFoundException, IOException {
        FileReader ler = new FileReader(DataBase);
        BufferedReader br = new BufferedReader(ler);
        
        String linha;
        
        while((linha = br.readLine()) != null){
            
              String[] token = linha.split(";");
              
                  nome       = token[0];
                  pontos     = Integer.parseInt(token[1]);
                  d_golos    = Integer.parseInt(token[2]);
                  n_vitorias = Integer.parseInt(token[3]);
                  n_empates  = Integer.parseInt(token[4]);
                  n_derotas  = Integer.parseInt(token[5]);
                  g_marcados = Integer.parseInt(token[6]);
                  g_sofridos = Integer.parseInt(token[7]);
                       
      dados.add(new DescricaoDasEquipas(nome,pontos,d_golos,n_vitorias,n_empates,n_derotas,g_marcados,g_sofridos));
        i++;
        }
    }
    
    public static void limparTela(){
        try{
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        }catch(AWTException e){
            System.out.println(e.getMessage());
     }
    }
      
    public static void menu() {
        System.out.println("===========================================================================================");
        System.out.println("\t\tMENU INICIAL (Uma vez que foi restingindo numero de equipas 1...20 )...");
        System.out.println("===========================================================================================");
        
        System.out.println("\t\t\t      1 -> Adicionar equipas");
        System.out.println("\t\t\t      2 -> Tabelade clasificao");
        System.out.println("\t\t\t      3 -> Remover equipas");
        System.out.println(RED+"\t\t\t      4 -> Restaurar");
        System.out.println("\t\t\t      0 -> Sair...\n");
        
    }
    
    public static void removerEquipa() throws InterruptedException{
        limparTela();
       apresentarDadosEquipas();
        System.out.print("\nInsira a possicao da equipa que dejesas remover: "); int  p = Integer.parseInt(entrada.next());
       
        if(p <= i){
            dados.remove(p-1);
            limparTela();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t"+GREEN+"ELIMAINADA COM SUCESSO.");
           TimeUnit.SECONDS.sleep(2);
        }else{ 
             limparTela();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\t\t\t"+RED+"OPERACAO INVALIDO! Nao existe nenhum correspondencia na possica inserida.");
           TimeUnit.SECONDS.sleep(3);
        }
    }
    
}