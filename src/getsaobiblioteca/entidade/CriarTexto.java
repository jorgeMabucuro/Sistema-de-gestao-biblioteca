
package getsaobiblioteca.entidade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CriarTexto {
    private BufferedWriter fW;
    
    public void abreEscrita(String nomeFicheiro){
        try{
            fW = new BufferedWriter(new FileWriter(nomeFicheiro));
        }
        catch(IOException e){
                System.out.println("Erro ao criar ficheiros");
        }
    }

    public void escreverLinha(String linha){
        try{
            fW.write(linha, 0, linha.length());
            fW.newLine();
        }
        catch(IOException e){
                System.out.println("ficheiro indicado não existe");
        }
    }
    
    public void escreverNumero(int num){
        String st = "";
        st = st.valueOf(num);
        escreverLinha(st);
    }
    
    public void escreverNumero(double num){
        String st = "";
        st = st.valueOf(num);
        escreverLinha(st);
    }
    
    public void fechaEscrita(){
        try{
            fW.close();
        }
        catch(IOException e){
                System.out.println("ficheiro indicado não existe");
        }
    }

    public static void main(String[] args) throws IOException{
        CriarTexto file = new CriarTexto();
        file.abreEscrita("C:\\Users\\anton\\Documents\\MyWork\\ISLORE\\SUBNDS\\java_basico\\aula\\ficheirotexto\\ficheiroTeste.txt");
        String st = "Insituto Foco Maputo";
        file.escreverLinha(st);
        file.escreverLinha("Aula prática sobre criação de ficheiro de texto em java");
        file.escreverNumero(2024);
        file.fechaEscrita();
    } 
}
