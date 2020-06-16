/** @author {Mads Voss, Mikkel Bech, Dalia Pireh, Sali Azou, Beant Sandhu}*/
package data;

public interface TempObservable extends Runnable {
    void register(TempListener listener);
}