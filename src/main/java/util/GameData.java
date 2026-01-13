package util;

import java.sql.*;

public class GameData {

    public int playerCropCount = 0;
    public int playerCropsNeeded = 10;
    public boolean wateredTheNPC = false;

    public GameData() {
        
    }
    
    //Input: String representing the filepath of the db file, Output: 
    //Purpose: to load the game data
    //Example: loadGameData("resources/saves/MySave.db")
    public static GameData loadGameData(String filePath) {
        GameData gameData = new GameData();
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            Statement stmt = conn.createStatement()){

            String sqlCreateTableIfNotExists ="""
                                CREATE TABLE IF NOT EXISTS game_data (
                                    playerCropCount INTEGER,
                                    playerCropsNeeded INTEGER,
                                    wateredTheNPC BOOLEAN
                                    );
                                """;
            stmt.execute(sqlCreateTableIfNotExists);

            String sqlLoadData_player = "SELECT playerCropCount, playerCropsNeeded, wateredTheNPC FROM game_data";
            stmt.execute(sqlLoadData_player);

            try (ResultSet rs = stmt.getResultSet()) //result set is an object that stores every row of a database
            {
                if (rs.next()) { //moves to the next row in the result set
                    gameData.playerCropCount = rs.getInt("playerCropCount");
                    gameData.playerCropsNeeded = rs.getInt("playerCropsNeeded");
                    gameData.wateredTheNPC = rs.getBoolean("wateredTheNPC");
                }
            }
            catch (Exception Ex)
            {
                Ex.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameData;
    }
    
    //Input: String representing the filepath and a GameData storing the game's save info, Output: boolean if it saved properly or not
    //Purpose: to save our game data into a secure .db file
    //Example: loadGameData("resources/saves/MySave.db", new GameData())
    public static boolean saveGameData(String filePath, GameData gameData) {
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            Statement stmt = conn.createStatement()){

            String sqlCreateTableIfNotExists ="""
                                CREATE TABLE IF NOT EXISTS game_data (
                                    playerCropCount INTEGER,
                                    playerCropsNeeded INTEGER,
                                    wateredTheNPC BOOLEAN
                                    );
                                """;
            stmt.execute(sqlCreateTableIfNotExists);

            String sqlDeleteFromGameData = "DELETE FROM game_data;";
            stmt.execute(sqlDeleteFromGameData);

            String sqlSaveData = "INSERT INTO game_data VALUES ("
                                +gameData.playerCropCount+","
                                +gameData.playerCropsNeeded+","
                                +gameData.wateredTheNPC
                                +");";
            stmt.execute(sqlSaveData);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
