package solostudy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class Methood01 {
	public static void main(String[] args) {
        byte[] src = {0, 1, 2, 3};
        byte[] dest = null;

        try{
            InputStream is = new ByteArrayInputStream(src);
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            int data = -1;
            while( (data = is.read()) != -1 ){
                os.write(data);
            }

            dest = os.toByteArray();
            System.out.println(Arrays.toString(dest));      // [0, 1, 2, 3]
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
