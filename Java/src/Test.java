// 18, 8, 19, 12, 6, 17
public class Test {
    public static void main(String[] args) {
        double score = 0;
        long i = System.nanoTime();
        for (int one = 12; one < 22; one++) {
            for (int two = 2; two < 11; two++) {
                for (int three = one; three < 22; three++) {
                    for (int four = 12; four < 22; four++) {
                        for (int five = 2; five < 11; five++) {
                            for (int six = four; six < 22; six++) {
                                GeorgeBobbyStrategy.one = one;
                                GeorgeBobbyStrategy.two = two;
                                GeorgeBobbyStrategy.three = three;
                                GeorgeBobbyStrategy.four = four;
                                GeorgeBobbyStrategy.five = five;
                                GeorgeBobbyStrategy.six = six;
                                double ascore = new BlackjackDealer().playBlackjack(new ComputerBlackjackPlayer(new GeorgeBobbyStrategy()), 30000);
                                if (ascore > score) {
                                    score = ascore;
                                    System.out.println(one);
                                    System.out.println(two);
                                    System.out.println(three);
                                    System.out.println(four);
                                    System.out.println(five);
                                    System.out.println(six);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println((System.nanoTime() - i) / 1000000000.0);
    }
}
