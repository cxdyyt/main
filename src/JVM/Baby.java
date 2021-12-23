package JVM;

public enum Baby {
    JIACHENG, JINGHANG, JIARUI;

    public static void main(String args[]) {
        System.out.println(Baby.valueOf("JINGHANG"));
    }
}
