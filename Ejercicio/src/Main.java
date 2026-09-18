import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        ConsecutivoFactura a = ConsecutivoFactura.getInstance();
        ConsecutivoFactura b = ConsecutivoFactura.getInstance();
        //ConsecutivoFactura c = new ConsecutivoFactura();
        System.out.println("La linea new ConsecutivaFactura esta comentada ya que no compila ");
        Runnable tarea = () -> {
            for ( int i = 0; i < 100; i++){
                int num = ConsecutivoFactura.getInstance().siguiente();
                System.out.println("Num: " + num  );
            }
        };

        Thread[] hilos = new Thread[10];
        for (int i = 0; i < 10; i++) {
            hilos[i] = new Thread(tarea);
            hilos[i].start();
        }
        for (Thread h : hilos) {
            try {
                h.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int valorFinal = ConsecutivoFactura.getInstance().siguiente();
        System.out.println("Valor Final esperado : "+valorFinal);
        System.out.println("Si el valor es 1000, no hubo números repetidos ni perdidos ");
        System.out.println("a = b " + (a == b));
    }
}