import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TCPClient {

	static final int RTT = 100;

	public static void main(String[] args) throws IOException {
		String dns = "ec2-54-86-43-91.compute-1.amazonaws.com";
		Socket s = null;
		HSSFWorkbook workbook = new HSSFWorkbook();

		try {

			s = new Socket(dns, 7000);
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();

			int[] size = { 1, 8, 16, 32, 64, 128, 256, 512, 1024 };
			
			for (int j = 0; j < size.length; j++) {
				HSSFSheet sheet = workbook.createSheet(size[j] + "Bytes");

				int rownum = 0;
				for (int i = 0; i < 10; i++) {
					HSSFRow row = sheet.createRow(rownum++);
					
					byte[] buffSend = new byte[size[j]];
					byte[] buffReceive = new byte[size[j]];
					System.out.println("Rodada " + i + " Buffer " + buffSend.length);
					int count = 0;
					int numCell = 0;
					while (count < 100) {
						long tempoInicial = System.currentTimeMillis();

						os.write(buffSend);
						is.read(buffReceive);
						
						HSSFCell cell = row.createCell(numCell++);
						long tempoFinal = System.currentTimeMillis();
						cell.setCellValue( tempoFinal - tempoInicial);
						System.out.println(tempoFinal - tempoInicial + ", ");

						++count;
					}
				}
			}
			workbook.write(new FileOutputStream("dados.xls"));
			workbook.close();

			System.err.println("Write successfully");

			s.close();

		} catch (Exception e) {
			System.err.println("Error in writing to file");
		}
	}
}
