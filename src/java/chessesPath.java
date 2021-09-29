public enum chessesPath {
    Pl1Normal("file:src/BN.PNG"),
    Pl1King("file:src/BK.png"),
    Pl2Normal("file:src/YN.png"),
    Pl2King("file:src/YK.png");
    private String path;
    chessesPath(String s) {
        this.path = s;
    }
    public String getPath(){
        return path;
    }
}
