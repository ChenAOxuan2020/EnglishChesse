import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class chesses {
    private int x;
    private int y;
    private double cellLen = 60;
    private chessesPath chessesPath;
    private boolean selected;
    private ImageView imageView;
    public chesses(int x, int y, chessesPath chessesPath) {
        this.x = x;
        this.y = y;
        this.chessesPath = chessesPath;
        Image image = new Image(chessesPath.getPath());
        imageView = new ImageView(image);
    }

    public ImageView getImageView() {
        return imageView;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public void move(int x,int y){
        this.x = x;
        this.y = y;
        this.imageView.setX(x*cellLen);
        this.imageView.setY(y*cellLen);
    }
    public void replacePl1(){
        this.chessesPath = chessesPath.Pl1King;
    }
    public void replacePl2(){
        this.chessesPath= chessesPath.Pl2King;
    }

    public String getType(){
        return chessesPath.toString() ;
    }
}
