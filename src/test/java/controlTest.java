import static org.junit.jupiter.api.Assertions.*;

class controlTest {
    private int[][] testInt = new int[8][8];
    public void buildTestInt (){
        for (int i = 0; i < 8;i++){
            for (int j = 0; j < 8 ; j++){
                if (i == 3 && j == 4){
                    testInt[i][j] = 1;
                }else if ( i == 0 && j == 2){
                    testInt[i][j] = 1;
                }else if (i == 4 && j == 5){
                    testInt[i][j] = 2;
                }else if (i == 6 && j == 3){
                    testInt[i][j] = 2;
                }else {
                    testInt[i][j] = 0;
                }
            }
        }
    }
    private boradPane boradPane;
    @org.junit.jupiter.api.Test
    void upCanEat() {
        buildTestInt();
        assertFalse(new control(boradPane).upCanEat(testInt,2,4,0,2));
        assertTrue(new control(boradPane).upCanEat(testInt,1,3,4,5));
    }

    @org.junit.jupiter.api.Test
    void downCanEat() {
        buildTestInt();
        assertFalse(new control(boradPane).downCanEat(testInt,2,4,0,2));
        assertTrue(new control(boradPane).downCanEat(testInt,2,4,3,4));
    }

}