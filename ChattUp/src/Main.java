//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.Socket;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class Main {
//
//	public static void main(String[] args) {
//
//		ExecutorService pool = Executors.newFixedThreadPool(10);
//		
//		try {
//			TCPServer2 t = new TCPServer2();
//			pool.submit(t);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//}