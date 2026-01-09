package util;

import java.nio.file.*;
import java.sql.*;

public class GameData {

    public int playerCropCount = 0;
    public int playerCropsNeeded = 10;

    public GameData() {
        
    }
    
    public GameData(Player player) {
        
    }
    
    //Input: String representing the filepath of the db file, Output: 
    //Purpose: to load the game data
    public static GameData loadGameData(String filePath) {
        GameData gameData = new GameData();
        
        boolean databaseFound = Files.exists(Paths.get(filePath));
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            Statement stmt = conn.createStatement()){
            
            if (!databaseFound) {
                String sqlCreateTable ="""
                                    CREATE TABLE IF NOT EXISTS game_data (
                                        playerCropCount INTEGER,
                                        playerCropsNeeded INTEGER
                                        );
                                    """;
                stmt.execute(sqlCreateTable);
            } else {
                String sqlLoadData_player = "SELECT playerCropCount, playerCropsNeeded FROM game_data";
                stmt.execute(sqlLoadData_player);
            
                
                try (ResultSet rs = stmt.getResultSet())
                {
                    if (rs.next()) {
                        gameData.playerCropCount = rs.getInt("playerCropCount");
                        gameData.playerCropsNeeded = rs.getInt("playerCropsNeeded");
                    }
                } 
                catch (Exception Ex)
                {
                    Ex.printStackTrace();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameData;
    }
    
    //Input: String representing the filepath and a GameData storing the game's save info, Output: boolean if it saved properly or not
    //Purpose: to save our game data into a secure .db file
    public static boolean saveGameData(String filePath, GameData gameData) {
        
        boolean databaseFound = Files.exists(Paths.get(filePath));
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            Statement stmt = conn.createStatement()){
            
            if (!databaseFound) {
                String sqlCreateTable ="""
                                    CREATE TABLE IF NOT EXISTS game_data (
                                        playerCropCount INTEGER,
                                        playerCropsNeeded INTEGER
                                        );
                                    """;
                stmt.execute(sqlCreateTable);
            } else {
                stmt.execute("DELETE FROM game_data;");
            }
            String sqlSaveData = "INSERT INTO game_data VALUES ("
                                +gameData.playerCropCount+","
                                +gameData.playerCropsNeeded
                                +");";
            stmt.execute(sqlSaveData);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
