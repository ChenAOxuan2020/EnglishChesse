import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class control implements EventHandler<MouseEvent> {
    private boradPane boradPane;
    private int[][] AllChess = new int[8][8];
    public control (boradPane boradPane){
        this.boradPane = boradPane;
    }
    public void handle(MouseEvent event){
        reDraw();
        int x = (int) (event.getX() / 60);
        int y = (int) (event.getY() / 60);
        if (x > 7 || y > 7) {
            return;
        }
        if (isSelected() == null) {
            chesses chesses = isEmpity(x, y,"player1");
            if (chesses == null){
                return;
            }else {
                chesses.setSelected(true);
            }
            if (chesses == null) {
                return;
            } else {
                chesses.setSelected(true);
            }
        } else {
            chesses current = isSelected();
            if (boradPane.getCurrentSide() == "player1"){
                if (player1CanEat()){
                    if (pl1Eat(current,x,y)){
                        reDraw();
                        if (pl1King()){
                            reDraw();
                            current.setSelected(false);
                            boradPane.setCurrentSide("player2");
                            return;
                        }
                        if (!player1CanEat()){
                            reDraw();
                            current.setSelected(false);
                            boradPane.setCurrentSide("player2");
                        }
                    }
                    reDraw();
                    current.setSelected(false);
                }else {
                    if (pl1Move(current,x,y)){
                        if (pl1King()){
                            reDraw();
                            current.setSelected(false);
                            boradPane.setCurrentSide("player2");
                            return;
                        }
                        boradPane.setCurrentSide("player2");
                        reDraw();
                        current.setSelected(false);
                    }
                    current.setSelected(false);
                }
            }else {
                if (player2CanEat()){
                    if (pl2Eat(current,x,y)){
                        reDraw();
                        if (pl2King()){
                            reDraw();
                            current.setSelected(false);
                            boradPane.setCurrentSide("player1");
                            return;
                        }
                        if (!player2CanEat()){
                            reDraw();
                            current.setSelected(false);
                            boradPane.setCurrentSide("player1");
                        }
                    }
                    reDraw();
                    current.setSelected(false);
                }else {
                    if (pl2Move(current,x,y)){
                        if (pl2King()){
                            reDraw();
                            current.setSelected(false);
                            boradPane.setCurrentSide("player1");
                            return;
                        }
                        boradPane.setCurrentSide("player1");
                        reDraw();
                        current.setSelected(false);
                    }
                    current.setSelected(false);
                }
            }
            isGameOver();
        }

    }
    public boolean pl1King (){
        for (int i = 0; i < boradPane.GetPl1CL().size(); i++){
            chesses now = (chesses)boradPane.GetPl1CL().get(i);
            if (now.getY()==7 ){
                if (now.getType() != "Pl1King"){
                    reMove(now);
                    now.replacePl1();
                    boradPane.DrPl1King();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean pl2King (){
        for (int i = 0; i < boradPane.GetPl2CL().size(); i++){
            chesses now = (chesses)boradPane.GetPl2CL().get(i);
            if (now.getY()==0 ){
                if (now.getType() != "Pl2King"){
                    reMove(now);
                    now.replacePl2();
                    boradPane.DrPl2King();
                    return true;
                }
            }
        }
        return false;
    }
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
    public boolean player1CanEat(){
        for (int i = 0; i < boradPane.GetPl1CL().size(); i++){
            chesses chesses = (chesses) boradPane.GetPl1CL().get(i);
            if (AllChess[chesses.getX()][chesses.getY()] == 1){
                if (downCanEat(AllChess,2,4,chesses.getX(),chesses.getY())){
                    return true;
                }
            }else if (AllChess[chesses.getX()][chesses.getY()] == 3){
                if (downCanEat(AllChess,2,4,chesses.getX(),chesses.getY())
                        || upCanEat(AllChess,2,4,chesses.getX(),chesses.getY())){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean player2CanEat(){
        for (int i = 0; i < boradPane.GetPl2CL().size(); i++){
            chesses chesses = (chesses) boradPane.GetPl2CL().get(i);
            if (AllChess[chesses.getX()][chesses.getY()] == 2){
                if (upCanEat(AllChess,1,3,chesses.getX(),chesses.getY())){
                    return true;
                }
            }else if (AllChess[chesses.getX()][chesses.getY()] == 4){
                if (downCanEat(AllChess,1,3,chesses.getX(),chesses.getY())
                        || upCanEat(AllChess,1,3,chesses.getX(),chesses.getY())){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean pl1Eat (chesses current , int x, int y){
        if (AllChess[current.getX()][current.getY()] == 1){
            if (Math.abs(x -current.getX()) == 2 && y - current.getY() == 2){
                if (AllChess[x][y] == 0 &&
                        boradPane.GetPl2CL().contains
                                (isEmpity((x+current.getX())/2,(y +current.getY())/2,"player2"))){
                    chesses eatChesse = isEmpity((x+current.getX())/2,(y +current.getY())/2,"player2");
                    reMove(eatChesse);
                    current.move(x,y);
                    return true;
                }
            }
        }else if (AllChess[current.getX()][current.getY()] == 3){
            if (Math.abs(x -current.getX()) == 2 && Math.abs(y -current.getY()) == 2){
                if (AllChess[x][y] == 0 &&
                        boradPane.GetPl2CL().contains
                                (isEmpity((x+current.getX())/2,(y +current.getY())/2,"player2"))){
                    chesses eatChesse = isEmpity((x+current.getX())/2,(y +current.getY())/2,"player2");
                    reMove(eatChesse);
                    current.move(x,y);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean pl2Eat (chesses current , int x, int y){
        if (AllChess[current.getX()][current.getY()] == 2){
            if (Math.abs(x -current.getX()) == 2 && y - current.getY() == -2){
                if (AllChess[x][y] == 0 &&
                        boradPane.GetPl1CL().contains
                                (isEmpity((x+current.getX())/2,(y +current.getY())/2,"player2"))){
                    chesses eatChesse = isEmpity((x+current.getX())/2,(y +current.getY())/2,"player2");
                    reMove(eatChesse);
                    current.move(x,y);
                    return true;
                }
            }
        }else if (AllChess[current.getX()][current.getY()] == 4){
            if (Math.abs(x -current.getX()) == 2 && Math.abs(y -current.getY()) == 2){
                if (AllChess[x][y] == 0 &&
                        boradPane.GetPl1CL().contains
                                (isEmpity((x+current.getX())/2,(y +current.getY())/2,"player2"))){
                    chesses eatChesse = isEmpity((x+current.getX())/2,(y +current.getY())/2,"player2");
                    reMove(eatChesse);
                    current.move(x,y);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean pl1Move (chesses current, int x, int y){
        if (AllChess[current.getX()][current.getY()] == 1 && y - current.getY() == 1
                && Math.abs(x - current.getX()) == 1){
            if (AllChess[x][y]==0){
                current.move(x,y);
                return true;
            }
        }else if (AllChess[current.getX()][current.getY()] == 3 && Math.abs(y - current.getY())== 1
                && Math.abs(x - current.getX()) == 1){
            if (AllChess[x][y] == 0){
                current.move(x,y);
                return true;
            }
        }
        return false;
    }
    public boolean pl2Move (chesses current, int x, int y){
        if (AllChess[current.getX()][current.getY()] == 2 && y - current.getY() == -1
                && Math.abs(x - current.getX()) == 1){
            if (AllChess[x][y]==0){
                current.move(x,y);
                return true;
            }
        }else if (AllChess[current.getX()][current.getY()] == 4 && Math.abs(y - current.getY())== 1
                && Math.abs(x - current.getX()) == 1){
            if (AllChess[x][y] == 0){
                current.move(x,y);
                return true;
            }
        }
        return false;
    }
    public void reMove(chesses chesses){
        chesses.getImageView().setImage(null);
        if(boradPane.getCurrentSide().equals("player1")){
            for(int i=0;i<boradPane.GetPl2CL().size();i++){
                chesses chesses1 = (chesses) boradPane.GetPl2CL().get(i);
                if(chesses.equals(chesses1)){
                    boradPane.GetPl2CL().remove(i);
                    break;
                }
            }
        }
        else{
            for(int i=0;i<boradPane.GetPl1CL().size();i++){
                chesses chesses1 = (chesses) boradPane.GetPl1CL().get(i);
                if(chesses.equals(chesses1)){
                    boradPane.GetPl1CL().remove(i);
                    break;
                }
            }
        }
    }
    public chesses isSelected(){
        if (boradPane.getCurrentSide().equals("player1")){
            for (int i = 0; i < boradPane.GetPl1CL().size();i++){
                chesses chesses = (chesses) boradPane.GetPl1CL().get(i);
                if (chesses.isSelected()){
                    return chesses;
                }
            }
        }else {
            for (int i = 0; i < boradPane.GetPl2CL().size();i++){
                chesses chesses = (chesses) boradPane.GetPl2CL().get(i);
                if (chesses.isSelected()){
                    return chesses;
                }
            }
        }
        return null;
    }
    public chesses isEmpity(int x, int y, String side){
        if (boradPane.getCurrentSide().equals(side)){
            for (int i = 0; i < boradPane.GetPl1CL().size();i++){
                chesses chesses = (chesses) boradPane.GetPl1CL().get(i);
                if (chesses.getX() == x && chesses.getY() == y){
                    return chesses;
                }
            }
        }else {
            for (int i = 0; i < boradPane.GetPl2CL().size();i++){
                chesses chesses = (chesses) boradPane.GetPl2CL().get(i);
                if (chesses.getX() == x && chesses.getY() == y){
                    return chesses;
                }
            }
        }
        return null;
    }
    public void reDraw(){
        for (int i = 0; i < 8;i++){
            for (int j = 0;j< 8; j++){
                AllChess[i][j]=0;
            }
        }
        for (int i = 0; i < boradPane.GetPl1CL().size();i++){
            chesses chesses = (chesses)boradPane.GetPl1CL().get(i);
            if (chesses.getType() == "Pl1Normal"){
                AllChess[chesses.getX()][chesses.getY()]=1;
            }else if (chesses.getType() == "Pl1King"){
                AllChess[chesses.getX()][chesses.getY()]=3;
            }
        }
        for (int i = 0; i < boradPane.GetPl2CL().size();i++){
            chesses chesses = (chesses)boradPane.GetPl2CL().get(i);
            if (chesses.getType() == "Pl2Normal"){
                AllChess[chesses.getX()][chesses.getY()]=2;
            }else if (chesses.getType() == "Pl2King"){
                AllChess[chesses.getX()][chesses.getY()]=4;
            }
        }
    }
    public void isGameOver(){
        if (boradPane.GetPl1CL().isEmpty()){
            boradPane.messeage("GameOver","Player2Win");
        }
        if (boradPane.GetPl2CL().isEmpty()){
            boradPane.messeage("GameOver","Player1Win");
        }
    }
}