package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/water.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/wall.png")));
        } catch (Exception e){e.printStackTrace();}
    }

    public void loadMapFile(){
        try {
            InputStream in = getClass().getResourceAsStream("/maps/map01.txt");
            assert in != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int col =0;
            int row =0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol){
                    String[] mapTileData = line.split(" ");
                    int tileNumType = Integer.parseInt(mapTileData[col]);
                    mapTileNum[col][row] = tileNumType;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }

            br.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics g){
       int col = 0;
       int row = 0;
       int x = 0;
       int y = 0;
       loadMapFile();

       while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
           int tileNum = mapTileNum[col][row];
           g.drawImage(tile[tileNum].image,x,y,gp.scaledTileSize,gp.scaledTileSize,null);
           col++;
           x += gp.scaledTileSize;

           if (col == gp.maxScreenCol) {
               col = 0;
               row++;
               x = 0;
               y += gp.scaledTileSize;
           }
       }
    }
}
