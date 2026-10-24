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
    Tile[] tilesArray;
    Tile [][] mapTiles;
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tilesArray = new Tile[10];
        mapTiles = new Tile[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMapFile("/maps/map01.txt");
    }
    public Tile getTile (int row, int col) {
        return mapTiles[col][row];
    }

    public void getTileImage(){
        try {
            tilesArray[0] = new Tile(Tile.Type.GRASS);
            tilesArray[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass.png")));

            tilesArray[1] = new Tile(Tile.Type.WATER);
            tilesArray[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/water.png")));

            tilesArray[2] = new Tile(Tile.Type.DIRT);
            tilesArray[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/wall.png")));
        } catch (Exception e){e.printStackTrace();}
    }

    public void loadMapFile(String fileName) {
        try {
            InputStream in = getClass().getResourceAsStream(fileName);
            assert in != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            for (int row = 0; row < gp.maxScreenRow; row++) {
                String line = br.readLine();
                for (int col = 0; col < gp.maxScreenCol; col++) {
                    String[] mapTileData = line.split(" ");
                    int intTile = Integer.parseInt(mapTileData[col]);
                    mapTiles[col][row] = tilesArray[intTile];
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g) {
       for (int row = 0, y = 0; row < gp.maxScreenRow; row++, y += gp.scaledTileSize) {
           for (int col = 0, x = 0; col < gp.maxScreenCol; col++, x+= gp.scaledTileSize) {
               Tile currentTile = mapTiles[col][row];
               g.drawImage(currentTile.image, x, y, gp.scaledTileSize, gp.scaledTileSize, null);
           }
       }
    }
}
