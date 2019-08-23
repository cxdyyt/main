package reflect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

public class ReflectTest {
    EntityField ef = new EntityField();

    public void test() throws Exception {
        Method[] methods = EntityField.class.getDeclaredMethods();

        FileOutputStream fio = new FileOutputStream(new File("C:\\Users\\Beck\\Desktop\\clic\\messageClic.xml"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fio));
        for (Method med : methods) {

            String name = med.getName();
            if (!name.startsWith("set")) {
                System.out.println("------------------------------------------------------------------------" + name);
                String res = (String) med.invoke(ef, null);
                bw.write("<ns6:Message>");
                bw.newLine();
                bw.write("   <identifier>" + "204" + "</identifier>");
                bw.newLine();
                bw.write("   <messageText>" + "mess" + "</identifier>");
                bw.newLine();
                bw.write("   <messageType>" + "E" + "</messageType>");
                bw.newLine();
                bw.write("</ns6:Message>");
                System.out.println(res);
            }
        }
        bw.flush();
        bw.close();
    }

    public static void main(String ats[]) throws Exception {
        ReflectTest ret = new ReflectTest();
        ret.test();
    }
}
