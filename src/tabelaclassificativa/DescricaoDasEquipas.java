/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelaclassificativa;

import java.util.Comparator;

/**
 *
 * @author Gabarito
 */
public class DescricaoDasEquipas {
    
    String nome;
    Integer pontos;
    Integer d_golos;
    int n_vitorias;
    int n_empates;
    int n_derotas;
    int g_marcados;
    int g_sofridos;
   
    
    public DescricaoDasEquipas(String nome,Integer pontos, Integer d_golos, int n_vitorias, int n_empates, int n_derotas, int g_marcados, int g_sofridos){
    this.nome = nome;
    this.pontos = pontos;
    this.d_golos = d_golos;
    this.n_vitorias = n_vitorias;
    this.n_empates = n_empates;
    this.n_derotas = n_derotas;
    this.g_marcados = g_marcados;
    this.g_sofridos = g_sofridos;
    
    }
    
   
    
    public static Comparator<DescricaoDasEquipas> getComparatorIdadeDescNomeCresc() { // função de comparação
		return new Comparator<DescricaoDasEquipas>() {
			@Override
			public int compare(DescricaoDasEquipas o1, DescricaoDasEquipas o2) {// Compara argumentos 2 a 2 para ordenar.
                           
                       /*Se o número do o1 atual é menor do que o2 retorna -1
                        *(ou qualquer int negativo, indicando que deve vir “antes” de o1),
                        *se for maior retornamos 1 (ou qualquer int positivo)
                        *e se for igual então devolvemos 0.
    
                        *Obs: multiplicamos por numere negativo para envertermos ordem da ordecao(Decrescente)*/
				int ordemDescPontos = o1.pontos.compareTo(o2.pontos) * -1; //ordem decrescente

				//se nº de pontos for igual, comparar deferença de golos
				if (ordemDescPontos == 0){
					int ordemDescDefGolos = o1.d_golos.compareTo(o2.d_golos)* -1;//ordem decrescente
                                   //se ainda a deferença de golo for igual, comparar por nome     
                                 if (ordemDescDefGolos == 0){
                                    return o1.nome.compareTo(o2.nome);//Observe, queremos na ordem crescente(não multiplicar po -1)  
                                 }       
                                     return ordemDescDefGolos;   
				}
				return ordemDescPontos;
			}
		};
	}

}
