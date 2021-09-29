public class rules {
    public boolean upCanEat(int[][] allChess , int type1, int type2, int x, int y){
        if (y > 1){
            if (x < 6){
                if (allChess[x + 2][y - 2] == 0 &&
                        (allChess[x + 1][y - 1] == type1 || allChess[x + 1][y - 1] == type2)){
                    return true;
                }
            }
            if (x > 1){
                return allChess[x - 2][y - 2] == 0 &&
                        (allChess[x - 1][y - 1] == type1 || allChess[x - 1][y - 1] == type2);
            }
        }
        return false;
    }
    public boolean downCanEat(int[][] allChess , int type1, int type2, int x, int y){
        if (y < 6){
            if (x < 6){
                if (allChess[x + 2][y + 2] == 0 &&
                        (allChess[x + 1][y + 1] == type1 || allChess[x + 1][y + 1] == type2)){
                    return true;
                }
            }
            if (x > 1){
                return allChess[x - 2][y + 2] == 0 &&
                        (allChess[x - 1][y + 1] == type1 || allChess[x - 1][y + 1] == type2);
            }
        }
        return false;
    }
}
