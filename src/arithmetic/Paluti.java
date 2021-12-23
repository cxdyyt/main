package arithmetic;

public class Paluti {
    public static int totalStep(int steps) {
        if (steps == 1 || steps == 2) {
            return steps;
        } else {
            int pre = 1;
            int current = 2;
            for (int i = 3; i <= steps; i++) {
                int temp = current;
                current = current + pre;
                pre = temp;
            }
            return current;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int step = 10;
        System.out.println(totalStep(5));
    }

}
