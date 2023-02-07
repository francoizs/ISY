package gameFramework;

/**
 * Het singleton-pattern waar belangrijke variabelen uitgehaald kunnen worden.
 * Als er van deze variabelen dubbelen zijn, kan de client in de war raken.
 * @author Aaldert Kroes
 */
public class ClientValuesSingleton {
    /* Alle constanten waar geen meerdere instanties van mogen zijn */
    // Connection
    private final String connectionHost;
    private final int connectionPort;

    // AI
    private final int tilePointsDepth;
    private final int greedyDepth;

    // Othello
    private final int maxTilesEarly;
    private final int maxTilesMid;

    // uniqueInstance
    private static ClientValuesSingleton uniqueInstance;

    private ClientValuesSingleton(){
        /* Private constructor om Singleton te forceren*/
        this.connectionHost = "145.33.225.170";
        this.connectionPort = 7789;
        this.tilePointsDepth = 6;
        this.greedyDepth = 14;
        this.maxTilesEarly = 21;
        this.maxTilesMid = 52;
    }

    public static ClientValuesSingleton getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new ClientValuesSingleton();
        }
        return uniqueInstance;
    }

    /**
     * Alle getters voor de variabelen waarvan maar één mag bestaan.
     * @author Aaldert Kroes
     */
    public String getConnectionHost(){return connectionHost;}
    public int getConnectionPort(){return connectionPort;}
    public int getTilePointsDepth(){return tilePointsDepth;}
    public int getGreedyDepth(){return greedyDepth;}
    public int getMaxTilesEarly(){return maxTilesEarly;}
    public int getMaxTilesMid(){return maxTilesMid;}
}
