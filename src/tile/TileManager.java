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
        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMapFile("/maps/map02.txt");
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_corner_nw.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_edge_n.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_corner_ne.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_edge_w.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_center.png")));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_edge_e.png")));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_corner_sw.png")));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_edge_s.png")));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_corner_se.png")));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_corner_nw.png")));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_edge_n.png")));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_corner_ne.png")));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_edge_w.png")));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_center.png")));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_edge_e.png")));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_corner_sw.png")));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_edge_s.png")));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_corner_se.png")));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_corner_nw.png")));

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_edge_n.png")));

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_corner_ne.png")));

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_edge_w.png")));

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_center.png")));

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_edge_e.png")));

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_corner_sw.png")));

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_edge_s.png")));

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/plateau_corner_se.png")));

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/base_dry_left.png")));

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/base_dry_middle.png")));

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_cliff/base_dry_right.png")));

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_path_h.png")));

            tile[32] = new Tile();
            tile[32].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_path_v.png")));

            tile[33] = new Tile();
            tile[33].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_path_h_end_e.png")));

            tile[34] = new Tile();
            tile[34].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_inner_ne_se.png")));

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass/dirt_inner_se_sw.png")));

            tile[36] = new Tile();
            tile[36].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/summer/grass_water/lake_inner_nw.png")));
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
                    mapTileNum[col][row] = tileNumType;
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
           int tileNum = mapTileNum[worldCol][worldRow];
           //wolrdxy es pos del tile en el mundo
           //screenxy es donde se dibuja el tile
           //si player esta world(x250 , y250) esta a esa distancia del tile[0][0].
           //el screenxy de ese tile es 48,0.
           //enotnces calculamos el offset. hay que contemplar el screenxy del player q esta en la mitad de la pantalla no en 0,0.
           int worldX = worldCol * gp.scaledTileSize;
           int worldY = worldRow * gp.scaledTileSize;
           int screenX = worldX - (int) gp.player.worldX + gp.player.screenX;
           int screenY = worldY - (int) gp.player.worldY + gp.player.screenY;

           g.drawImage(tile[tileNum].image,screenX,screenY,gp.scaledTileSize,gp.scaledTileSize,null);
           worldCol++;

           if (worldCol == gp.maxWorldCol) {
               worldCol = 0;
               worldRow++;
           }
       }
    }
}
