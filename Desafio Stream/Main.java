import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        //pessoas com resultado final Covid
        long covid = Files.lines(Paths.get("INFLUD21-01-05-2023.csv"), Charset.defaultCharset())
            .filter(line -> line.split(";")[106].contains("5"))
            .count();
        System.out.println("\nPessoas com covid: " + covid);

        //pessoas que receberam a vacina de covid
        float vacinados = Files.lines(Paths.get("INFLUD21-01-05-2023.csv"), Charset.defaultCharset())
            .filter(line -> line.split(";")[154].contains("1"))
            .count();
        System.out.println("Vacinados: " + vacinados);

        //pessoas que nao receberam a vacina de covid
        float naoVacinados = Files.lines(Paths.get("INFLUD21-01-05-2023.csv"), Charset.defaultCharset())
            .filter(line -> line.split(";")[154].contains("2"))
            .count();
        System.out.println("Nao vacinados: " + naoVacinados);


        
        //vacinados que morreram de covid
        float vacinadosObito = Files.lines(Paths.get("INFLUD21-01-05-2023.csv"), Charset.defaultCharset())
            .filter(line -> line.split(";")[106].contains("5") && line.split(";")[154].contains("1") && line.split(";")[109].contains("2"))
            .count();
        System.out.println("\nQuantidade de vacinados que morreram de covid: " + vacinadosObito);

        float porcentagemVacinados = ((vacinadosObito * 100) / vacinados);
        System.out.println("Percentual: " + String.format("%.2f", porcentagemVacinados) + "%");



        //não vacinados que morreram de covid
        float naoVacinadosObito = Files.lines(Paths.get("INFLUD21-01-05-2023.csv"), Charset.defaultCharset())
            .filter(line -> line.split(";")[106].contains("5") && line.split(";")[154].contains("2") && line.split(";")[109].contains("2"))
            .count();
        System.out.println("\nNao vacinados que morreram de covid: " + naoVacinadosObito);
        
        float porcentagemNaoVacinados = ((naoVacinadosObito * 100) / naoVacinados);
        System.out.println("Percentual: " + String.format("%.2f", porcentagemNaoVacinados) + "%");



        // //percentual de mortes por tipo de vacina
        // float naoVacinadosObito = Files.lines(Paths.get("INFLUD21-01-05-2023.csv"), Charset.defaultCharset())
        //     .filter(line -> line.split(";")[106].contains("5") && line.split(";")[154].contains("2") && line.split(";")[109].contains("2"))
        //     .count();
        // System.out.println("\nNao vacinados que morreram de covid: " + naoVacinadosObito);
    }
}
