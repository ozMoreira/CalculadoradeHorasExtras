
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.NumberFormat;

public class CalcHoraExtra
{
    public static void main ( String [] args) throws ParseException
    {
        long diferencaHorario;
        Scanner digitaHora = new Scanner(System.in);

        System.out.println("");
        System.out.println("Digite abaixo a hora de Entrada");
        String horaInicial = digitaHora.nextLine();
        System.out.println("");
        System.out.println("Digite abaixo a hora de Saída");
        String horaSaida = digitaHora.nextLine();

        SimpleDateFormat hInformada = new SimpleDateFormat("HH:mm");
        Date horaInicioJornada = hInformada.parse(horaInicial);
        Date horaFimJornada = hInformada.parse(horaSaida);

        //Calcula o total da Jornada
        diferencaHorario = horaFimJornada.getTime() - horaInicioJornada.getTime();
        System.out.println("");
        System.out.println(String.format("Sua jornada total foi de " + "%02d:%02d", diferencaHorario / (60 * 60 * 1000) % 24 , ( diferencaHorario / 60000 ) % 60 ));
        
        //Calcula o total de Hora Extra, convertendo de Milisegundos pra Horas e Minutos
        long horaExtraTotal = (diferencaHorario/ (60 * 60 * 1000) % 24) - 8;
        long minHoraExtraTotal = (diferencaHorario/ 60000 ) % 60;
        System.out.println("");
        String informaHoraExtra = horaExtraTotal <= -1 ? "ATENÇÃO ... Voce Está Devendo Horas! Vá ao RH para verificar sua situação." : (String.format("Seu total de Horas Extras foi ---> " + "%02d:%02d", horaExtraTotal, minHoraExtraTotal));
        System.out.println(informaHoraExtra);
        
        //Calcula o que for hora extra, superior à jornada extra de 2 horas
        long demaisHorasExtras = (horaExtraTotal - 2);
        long horasExtrasIniciais = horaExtraTotal - demaisHorasExtras;
        
        double salario = 3500.00;
        double salarioMinuto = 0.33; 
        double salarioHora = 19.88;
        double addPrimeirasHExtras = 0.55;
        double addDemaisHExtras = 0.60;

        double valorPrimeirasHExtras = horaExtraTotal > 0? (salarioHora * addPrimeirasHExtras)*horasExtrasIniciais : 0;
        double valorMinutosIniciaisExtras = minHoraExtraTotal > 0 && horaExtraTotal >= 0 ? (salarioMinuto * addPrimeirasHExtras)*minHoraExtraTotal : 0 ;

        double valorDemaisHExtras = (salarioHora * addDemaisHExtras)*demaisHorasExtras;
        double valorMinExtras = (salarioMinuto * addDemaisHExtras)*minHoraExtraTotal;
  
        double totalPrimeirasExtras = valorPrimeirasHExtras + valorMinutosIniciaisExtras;
        double valorTotalPrimeirasExtras = totalPrimeirasExtras; // + salario
        
        double totalDemaisExtras = valorDemaisHExtras + valorMinExtras;
        double valorTotalExtraLonga = totalDemaisExtras + valorPrimeirasHExtras;  // + salario

        double resultado = horaExtraTotal > 2 ? valorTotalExtraLonga : valorTotalPrimeirasExtras;
        double totalFinal=(resultado + salario);
        System.out.println("");
        System.out.println("Seu saldo de horas neste dia foi de --->" + (NumberFormat.getCurrencyInstance().format(resultado)));
        System.out.println("");
        System.out.println("Caso voce tenha saldo de horas negativo no RH,\nseu Salario poderá sofrer alterações!");
        System.out.println("");
        System.out.println("O total que voce receberá neste mês, será de --->" + (NumberFormat.getCurrencyInstance().format(totalFinal)));
    }    
}
