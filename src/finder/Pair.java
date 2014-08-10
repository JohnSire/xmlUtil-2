package finder;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 10.08.2014 21:51
 */
public class Pair {
    private final int count;
    private final String ans;

    public Pair(final int count, final String ans) {
        this.count = count;
        this.ans = ans;
    }

    public int getCount() {
        return count;
    }

    public String getAns() {
        return ans;
    }
}
