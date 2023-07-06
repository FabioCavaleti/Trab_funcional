import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        // String diretorioAtual = System.getProperty("user.dir");
        // System.out.println("Diretório atual: " + diretorioAtual);
        String arquivoCSV = "dados.csv";
        String linha;
        List<String[]> linhasCSV = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            while ((linha = br.readLine()) != null) {
                // Separar os campos da linha usando a vírgula como delimitador
                String[] campos = linha.split(",");
                linhasCSV.add(campos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Converter a lista de linhas para uma matriz
        // df[0][] - lista de paises
        // df[1][] - lista de confirmed
        // df[2][] - lista de deaths
        // df[3][] - lista de recovery
        // df[4][] - lista de active
        String[][] df = new String[linhasCSV.size()][];
        linhasCSV.toArray(df);


        // Leitura da linha de entrada contendo os 5 inteiros
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite 4 valores separados por espaço:");
        String input = scanner.nextLine();
        String[] inputValues = input.split(" ");

        int n1 = Integer.parseInt(inputValues[0]);
        int n2 = Integer.parseInt(inputValues[1]);
        int n3 = Integer.parseInt(inputValues[2]);
        int n4 = Integer.parseInt(inputValues[3]);

        scanner.close();

        // 1

        int sum = 0;

        for(int i = 0; i < df[0].length; i++)
        {
            if(Integer.parseInt(df[1][i]) > n1)
            {
                sum += Integer.parseInt(df[3][i]);
            }
        }

        System.out.println(sum);

        // 2
        
        
    }
}

