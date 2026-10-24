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
        loadMapFile("/maps/map01.txt");
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

    public void loadMapFile(String fileName){
        try {
            InputStream in = getClass().getResourceAsStream(fileName);
            assert in != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            for (int row=0; row < gp.maxScreenRow; row++) {
                String line = br.readLine();
                for (int col = 0; col < gp.maxScreenCol; col++) {
                    String[] mapTileData = line.split(" ");
                    int intTile = Integer.parseInt(mapTileData[col]);
                    mapTileNum[col][row] = intTile;
                }
            }
            br.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
       for (int row = 0, y = 0; row < gp.maxScreenRow; row++, y += gp.scaledTileSize) {
           for (int col = 0, x = 0; col < gp.maxScreenCol; col++, x+= gp.scaledTileSize) {
               int currentTile = mapTileNum[col][row];
               g.drawImage(tile[currentTile].image, x, y, gp.scaledTileSize, gp.scaledTileSize, null);
           }
       }
    }
}
