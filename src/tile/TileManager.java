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
    Tile[] tiles;
    int[][] mapMatrix;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[100];
        mapMatrix = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMapFile("/maps/map02.txt");
    }

    public void getTileImage(){
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_corner_nw.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_edge_n.png")));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_corner_ne.png")));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_edge_w.png")));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_center.png")));

            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_edge_e.png")));

            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_corner_sw.png")));

            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_edge_s.png")));

            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_corner_se.png")));

            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_corner_nw.png")));

            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_edge_n.png")));

            tiles[12] = new Tile();
            tiles[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_corner_ne.png")));

            tiles[13] = new Tile();
            tiles[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_edge_w.png")));

            tiles[14] = new Tile();
            tiles[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_center.png")));

            tiles[15] = new Tile();
            tiles[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_edge_e.png")));

            tiles[16] = new Tile();
            tiles[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_corner_sw.png")));

            tiles[17] = new Tile();
            tiles[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_edge_s.png")));

            tiles[18] = new Tile();
            tiles[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_corner_se.png")));

            tiles[19] = new Tile();
            tiles[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_corner_nw.png")));

            tiles[20] = new Tile();
            tiles[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_edge_n.png")));

            tiles[21] = new Tile();
            tiles[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_corner_ne.png")));

            tiles[22] = new Tile();
            tiles[22].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_edge_w.png")));

            tiles[23] = new Tile();
            tiles[23].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_center.png")));

            tiles[24] = new Tile();
            tiles[24].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_edge_e.png")));

            tiles[25] = new Tile();
            tiles[25].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_corner_sw.png")));

            tiles[26] = new Tile();
            tiles[26].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_edge_s.png")));

            tiles[27] = new Tile();
            tiles[27].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_corner_se.png")));

            tiles[28] = new Tile();
            tiles[28].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/base_dry_left.png")));

            tiles[29] = new Tile();
            tiles[29].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/base_dry_middle.png")));

            tiles[30] = new Tile();
            tiles[30].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/base_dry_right.png")));

            tiles[31] = new Tile();
            tiles[31].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_path_h.png")));

            tiles[32] = new Tile();
            tiles[32].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_path_v.png")));

            tiles[33] = new Tile();
            tiles[33].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_path_h_end_e.png")));

            tiles[34] = new Tile();
            tiles[34].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_inner_ne_se.png")));

            tiles[35] = new Tile();
            tiles[35].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_inner_se_sw.png")));

            tiles[36] = new Tile();
            tiles[36].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_inner_nw.png")));
        } catch (Exception e){e.printStackTrace();}
    }

    public void loadMapFile(String fileName){
        try {
            InputStream in = getClass().getResourceAsStream(fileName);
            assert in != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            for (int row=0; row < gp.maxWorldRow; row++) {
                String line = br.readLine();
                String[] mapTileData = line.split(" ");
                for (int col=0; col < gp.maxWorldCol; col++) {
                    int tileNumType = Integer.parseInt(mapTileData[col]);
                    mapMatrix[col][row] = tileNumType;
                }
            }

            br.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics g){
       int worldCol = 0;
       int worldRow = 0;

       while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
           int tileType = mapMatrix[worldCol][worldRow];

           //world pos del tile en pixeles
           int tileX = worldCol * gp.scaledTileSize;
           int tileY = worldRow * gp.scaledTileSize;

           //screen pos del tile relativo al player
           int screenX = (tileX - (int) gp.player.worldX) + gp.player.screenX;
           int screenY = (tileY - (int) gp.player.worldY) + gp.player.screenY;
           //grid culling-ish
           //calcula si el tile esta dentro del frame.
           //usando el spawn de ej: 960px - gp.player.screenX = 360px = borde del frame.
           //dibujar si tileX > 360px
           if (tileX > gp.player.worldX - gp.player.screenX &&
                   tileX < gp.player.worldX + gp.player.screenX &&
                   tileY > gp.player.worldY - gp.player.screenY &&
                   tileY < gp.player.worldY + gp.player.screenY){
               g.drawImage(tiles[tileType].image,screenX,screenY,gp.scaledTileSize,gp.scaledTileSize,null);
           }

           worldCol++;

           if (worldCol == gp.maxWorldCol) {
               worldCol = 0;
               worldRow++;
           }
       }
    }
}
