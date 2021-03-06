package joont92;

import java.util.stream.Stream;

public enum Rank{
    First(6, false, 2000000000), Second(5, true, 30000000), Third(5, false, 1500000),
    Fourth(4, false, 50000), Fifth(3, false, 5000), Fail(0, false, 0);

    private int hit;
    private boolean isBonusHit;
    private long prizeMoney;

    Rank(int count, boolean isBonusHit, long prizeMoney){
        this.hit = count;
        this.isBonusHit = isBonusHit;
        this.prizeMoney = prizeMoney;
    }

    public int getHit() {
        return hit;
    }

    public boolean isBonusHit() {
        return isBonusHit;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

    public static Rank calculate(int hit, boolean isBonusHit){
        return Stream.of(Rank.values())
                .filter(r -> {
                    if(r == Second){
                        return r.hit == hit && r.isBonusHit == isBonusHit;
                    } else{
                        return r.hit == hit;
                    }
                })
                .findFirst()
                .orElse(Fail);
    }
}
