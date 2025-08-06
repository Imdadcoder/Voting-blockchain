//
//
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.Reader;
//import java.util.Random;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import sun.misc.BASE64Decoder;
//
//public class ImageServlet extends HttpServlet
//{
//	private static final long serialVersionUID = 1L;
//
//	@Override
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//	{
//		try
//		{
//			StringBuffer buffer = new StringBuffer();
//			Reader reader = request.getReader();
//			int current;
//
//			while((current = reader.read()) >= 0)
//				buffer.append((char) current);
//			
//			String data = new String(buffer);
//			data = data.substring(data.indexOf(",") + 1);
//
//			System.out.println("Dados em Base64 da imagem PNG: " + data);
//
//			FileOutputStream output = new FileOutputStream(new File("/E:/OWNERFILES/" + 
//			new Random().nextInt(100000) + ".png"));
//
//			output.write(new BASE64Decoder().decodeBuffer(data));
//			output.flush();
//			output.close();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//}
//
//
//
//
//
