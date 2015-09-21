import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        //cria um socket com o google na porta 80 
        Socket socket = new Socket("google.com.br", 80);
        //verifica se esta conectado 
        if (socket.isConnected()) {
            //imprime o endereço de IP do servidor 
            System.out.println("Conectado a " + socket.getInetAddress());
        }
        /* veja que a requisição termina com \r\n que equivale a &lt;CR&gt;&lt;LF&gt; para encerar a requisição tem uma linha em branco */
        String requisicao = ""
                + "GET / HTTP/1.1\r\n"
                + "Host: www.google.com.br\r\n"
                + "\r\n";
        //OutputStream para enviar a requisição 
        OutputStream envioServ = socket.getOutputStream();
        //temos que mandar a requisição no formato de vetor de bytes 
        byte[] b = requisicao.getBytes();
        //escreve o vetor de bytes no "recurso" de envio 
        envioServ.write(b);
        //marca a finalização da escrita 
        envioServ.flush();
        //cria um scanner a partir do InputStream que vem do servidor 
        Scanner sc = new Scanner(socket.getInputStream());
        //enquanto houver algo para ler 
        while (sc.hasNext()) {
            //imprime uma linha da resposta 
            System.out.println(sc.nextLine());
        }
        //fechar a conexao
        sc.close();
        envioServ.close();
        socket.close();
        System.out.println("Conexao Encerrada!");
    }
}
