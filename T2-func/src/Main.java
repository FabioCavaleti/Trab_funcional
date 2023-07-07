import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Data{

    String country;
    int confirmed;
    int deaths;
    int recovery;
    int active;

    public Data( String country,int confirmed,int deaths,int recovery,int active)
    {
        this.country = country;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovery = recovery;
        this.active = active;

    }
    
    public String getCountry()
    {
        return this.country;
    }
    public int getActive()
    {
        return this.active;
    }

    public int getConfirmed()
    {
        return this.confirmed;
    }

    public int getDeaths()
    {
        return this.deaths;
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        try (Stream <String> lines = Files.lines(Paths.get("dados.csv"), Charset.defaultCharset())) {
            List<String[]> splitedLines = lines.map(e -> e.split(","))
                                               .collect(Collectors.toList());

            // splitedLines.stream().forEach(e -> System.out.println(e[0]));

            List<Data> data = splitedLines.stream().map(e -> new Data(e[0], Integer.parseInt(e[1]), Integer.parseInt(e[2]), Integer.parseInt(e[3]), Integer.parseInt(e[4])))
                                                      .collect(Collectors.toList());

            // valores.stream().forEach(e -> System.out.println(e.country));

             // Leitura da linha de entrada contendo os 5 inteiros
            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();
            String[] inputValues = input.split(" ");

            int n1 = Integer.parseInt(inputValues[0]);
            int n2 = Integer.parseInt(inputValues[1]);
            int n3 = Integer.parseInt(inputValues[2]);
            int n4 = Integer.parseInt(inputValues[3]);

            scanner.close();

            // Etapa 1
             System.out.println(data.stream().filter(e -> e.confirmed >= n1)
                         .mapToInt(Data::getActive).sum());

            // Etapa 2
             List<Data> topN2ValuesActive = data.stream().sorted(Comparator.comparingInt(Data::getActive).reversed())
                                                 .limit(n2)
                                                 .collect(Collectors.toList());
                                                 
            // System.out.println("TESTE");
            // topN2ValuesActive.stream().forEach(e -> System.out.println(e.active));
            // System.out.println("TESTE");
            topN2ValuesActive.stream().sorted(Comparator.comparingInt(Data::getConfirmed))
                                                                           .limit(n3)
                                                                           .forEach(e -> System.out.println(e.getDeaths()));

            // Etapa 3
            List<Data> topN4ValuesConfirmed = data.stream().sorted(Comparator.comparingInt(Data::getConfirmed).reversed())
                                                 .limit(n4)
                                                 .collect(Collectors.toList());
            topN4ValuesConfirmed.stream().sorted((e1,e2) -> e1.getCountry().compareTo(e2.getCountry())).forEach(e -> System.out.println(e.country));
        }
        

    }
}