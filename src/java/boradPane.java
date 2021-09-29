import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class boradPane extends Pane {
    private Canvas canvas;
    private GraphicsContext gc;
    private double cellLen = 60;
    private ArrayList Pl1ChessList = new ArrayList<chesses>();
    private ArrayList PL2ChessList = new ArrayList<chesses>();
    private String currentSide = "player1";


    public boradPane(double cellLen) {
        this.canvas = new Canvas(8 * cellLen, 8 * cellLen);
        this.gc = canvas.getGraphicsContext2D();
        this.cellLen = cellLen;
        getChildren().add(canvas);
        fillChesses();
    }

    public void fillChesses() {
        drawBackground();
        drawChesses();
    }

    public void drawBackground() {
        //color for background
        Color a = new Color(100 / 255.0, 125/ 255.0, 0 / 255.0, 1);
        Color b = new Color(0 / 255.0, 125 / 255.0, 125 / 255.0, 1);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0)
                    gc.setFill(b);
                else
                    gc.setFill(a);
                gc.fillRect(i * cellLen, j * cellLen, cellLen, cellLen);
            }
    }
    public void reshapeImageView(int x, int y, chesses piece){
        piece.getImageView().setX(x*cellLen);
        piece.getImageView().setY(y*cellLen);
        piece.getImageView().setFitHeight(cellLen);
        piece.getImageView().setFitWidth(cellLen);
    }
    public void DrPl1King(){
        for (int i = 0 ; i < GetPl1CL().size();i++){
            chesses chesses =  (chesses)Pl1ChessList.get(i);
            if (chesses.getType() == "Pl1King"){
                getChildren().remove(chesses.getImageView());
                chesses now = new chesses(chesses.getX(),chesses.getY(), chessesPath.Pl1King);
                Pl1ChessList.remove(chesses);
                Pl1ChessList.add(now);
                reshapeImageView(now.getX(),now.getY(),now);
                getChildren().add(now.getImageView());
            }
        }
    }
    public void DrPl1(){
        for (int i = 0;i < 8 ;i++){
            for (int j = 0 ; j <= 2 ; j ++){
                if ((j == 1&& i % 2 != 1)||(j != 1 && i % 2 == 1)){
                    chesses chesses = new chesses(i , j , chessesPath.Pl1Normal);
                    Pl1ChessList.add(chesses);
                    reshapeImageView(i,j,chesses);
                    getChildren().add(chesses.getImageView());
                }
            }
        }
    }
    public void DrPl2King(){
        for (int i = 0 ; i < GetPl2CL().size();i++){
            chesses chesses =  (chesses) PL2ChessList.get(i);
            if (chesses.getType() == "Pl2King"){
                getChildren().remove(chesses.getImageView());
                chesses now = new chesses(chesses.getX(),chesses.getY(), chessesPath.Pl2King);
                PL2ChessList.remove(chesses);
                PL2ChessList.add(now);
                reshapeImageView(now.getX(),now.getY(),now);
                getChildren().add(now.getImageView());
            }
        }
    }

    public void DrPl2(){
        for (int i = 0;i < 8 ;i++){
            for (int j = 5 ; j < 8 ; j ++){
                if ((j == 6 && i % 2 == 1)||(j != 6 && i % 2 != 1)){
                    chesses chesses = new chesses(i , j , chessesPath.Pl2Normal);
                    PL2ChessList.add(chesses);
                    reshapeImageView(i,j,chesses);
                    getChildren().add(chesses.getImageView());
                }
            }
        }
    }

    public void drawChesses() {
        DrPl1();
        DrPl2();
        DrPl1King();
        DrPl2King();
    }
    public void messeage(String title,String out){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        String content = out;
        alert.setContentText(content);
        alert.show();
    }

    public String getCurrentSide() {
        return currentSide;
    }
    public ArrayList GetPl1CL(){
        return Pl1ChessList;
    }
    public ArrayList GetPl2CL(){
        return PL2ChessList;
    }
    public void setCurrentSide(String currentSide) {
        this.currentSide = currentSide;
    }
}
