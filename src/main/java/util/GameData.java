package util;

import java.nio.file.*;
import java.sql.*;
import java.time.*;
import java.io.*;

public class GameData {

    int playerCropCount = 0;
    
    GameData() {
        
    }
    
    GameData(Player player) {
        
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
                                        playerCropCount INTEGER
                                        );
                                    """;
                stmt.execute(sqlCreateTable);
            } else {
                String sqlLoadData_player = "SELECT playerCropCount FROM game_data";
                stmt.execute(sqlLoadData_player);
            
                
                try (ResultSet rs = stmt.getResultSet())
                {
                    gameData.playerCropCount = rs.getInt("playerCropCount");
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
    
    //Input: String filepath representing the class path and a GameData storing the game's save info, Output: boolean if it saved properly or not
    //Purpose: to save our game data into a secure .db file
    public static boolean saveGameData(String filePath, GameData gameData) {
        
        boolean databaseFound = Files.exists(Paths.get(filePath));
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            Statement stmt = conn.createStatement()){
            
            if (!databaseFound) {
                String sqlCreateTable ="""
                                    CREATE TABLE IF NOT EXISTS game_data (
                                        playerCropCount INTEGER
                                        );
                                    """;
                stmt.execute(sqlCreateTable);
            } else {
                stmt.execute("DELETE FROM game_data;");
                String sqlSaveData = "INSERT INTO game_data VALUES ("
                                    +gameData.playerCropCount
                                    +");";
                stmt.execute(sqlSaveData);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
