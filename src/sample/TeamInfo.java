package sample;

public enum TeamInfo {
    LAVAL_TITANS("Laval Titans", "LT.jpg"), NDG_STRIKERS("NDG Strikers", "NDG.jpg"),
    SOUTH_SHORE_LIONS("South-Shore Lions", "SS.jpg"), MONT_ROYAL_EAGLES("Mont-Royal Eagles", "MR.jpg"),
    WEST_ISLAND_MUSTANGS("West Island Mustangs", "WI.jpg"), DOWNTOWN_THUNDER("Downtown Thunder", "DT.jpg"),
    GRIFFINTOWN_WARRIORS("Griffintown Warriors", "GW.jpg"), PARC_EX_KNIGHT_RIDERS("Parc-Ex Knight Riders", "PE.jpg");

    public final String TEAM_NAME;
    public final String TEAM_LOGO_FILE_NAME;

    TeamInfo(String TEAM_NAME, String TEAM_LOGO_FILE_NAME) {
        this.TEAM_NAME = TEAM_NAME;
        this.TEAM_LOGO_FILE_NAME = Config.IMAGE_DIR + TEAM_LOGO_FILE_NAME;
    }

    public String toString() {
        return TEAM_NAME;
    }
}
