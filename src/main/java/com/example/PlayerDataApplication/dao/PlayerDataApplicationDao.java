package com.example.PlayerDataApplication.dao;

import com.example.PlayerDataApplication.connection.Connect;
import com.example.PlayerDataApplication.util.DBUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.*;
@Repository
public class PlayerDataApplicationDao {

        private static final Logger log = LoggerFactory.getLogger(PlayerDataApplicationDao.class);


        public static Map<String, Object> fetchPlayerCareerData(String playerName) throws SQLException {
            Connection conn = null;
            PreparedStatement ps = null;
            Map<String, Object> playerCareerInfo = new LinkedHashMap<>();

            try {
                if (playerName == null || playerName.isEmpty()) {
                    log.debug("Please provide a valid Player Name, invalid Player Name null or empty ::{}", playerName);
                    throw new IllegalArgumentException("Please provide a valid Player Name, invalid Player Name null or empty :" + playerName);
                }
                conn = Connect.createConnection();

                String query = "Select * from player_career_info where player_name = ? ";
                log.debug("Executing fetchPlayerCareerData Query : {} ", query);
                log.debug("Parameters : { PlayerName : {}} ", playerName);

                ps = conn.prepareStatement(query);
                ps.setString(1, playerName);
                ResultSet rs = ps.executeQuery();

                Map<String, Object> playerData = new LinkedHashMap<>();
                while (rs.next()) {

                    log.info("Id : {}" , rs.getInt("id"));
                    playerData.put("ID", rs.getInt("id"));

                    log.info("Player Name : {}" , rs.getString("player_name"));

                    log.info("PlayerSpecification : {}" , rs.getString("player_specification"));
                    playerData.put("PlayerSpecification", rs.getString("player_specification"));

                    log.info("Total Matches : {}" , rs.getInt("player_total_matches"));
                    playerData.put("TotalMatches", rs.getInt("player_total_matches"));

                    log.info("TotalCenturies : {}" , rs.getInt("player_centuries"));
                    playerData.put("TotalCenturies", rs.getInt("player_centuries"));

                    log.info("TotalHalfCenturies : {}" , rs.getInt("player_half_centuries"));
                    playerData.put("TotalHalfCenturies", rs.getInt("player_half_centuries"));

                    log.info("Total5WicketsHaul : {}" , rs.getInt("player_five_wickets"));
                    playerData.put("Total5WicketsHaul", rs.getInt("player_five_wickets"));

                    log.info("TotalHatricks :{} " ,rs.getInt("player_total_hatricks"));
                    playerData.put("TotalHatricks", rs.getInt("player_total_hatricks"));

                    log.info("TotalOdiMatches : {}" , rs.getInt("total_ODI_matches"));
                    playerData.put("TotalOdiMatches", rs.getInt("total_ODI_matches"));

                    log.info("TotalTestMatches : {}", rs.getInt("total_Test_matches"));
                    playerData.put("TotalTestMatches", rs.getInt("total_Test_matches"));

                    log.info("TotalT20iMatches : {}" , rs.getInt("total_T20i_matches"));
                    playerData.put("TotalT20iMatches", rs.getInt("total_T20i_matches"));

                    log.info("TotalODIWickets : {}" , rs.getInt("total_odi_wickets"));
                    playerData.put("TotalODIWickets", rs.getInt("total_odi_wickets"));

                    log.info("TotalT20iWickets : {}" , rs.getInt("total_t20i_wickets"));
                    playerData.put("TotalT20iWickets", rs.getInt("total_t20i_wickets"));

                    log.info("TotalTestWickets : {}" , rs.getInt("total_test_wickets"));
                    playerData.put("TotalTestWickets", rs.getInt("total_test_wickets"));

                    log.info("TotalODIRuns : {}" , rs.getInt("player_odi_runs"));
                    playerData.put("TotalODIRuns", rs.getInt("player_odi_runs"));

                    log.info("TotalT20iRuns : {}" , rs.getInt("player_t20i_runs"));
                    playerData.put("TotalT20iRuns", rs.getInt("player_t20i_runs"));

                    log.info("TotalTestRuns : {}" , rs.getInt("player_test_runs"));
                    playerData.put("TotalTestRuns ", rs.getInt("player_test_runs"));
                }
                playerCareerInfo.put(playerName, playerData);
                if (playerData.isEmpty()){
                    log.debug("player is not available with this name : {}" , playerName);
                    throw new IllegalArgumentException("Please provide a valid playerName, invalid playerName :" + playerName);
                }

            } finally {
                DBUtil.close(ps, conn);
            }
            return playerCareerInfo;
        }

        public static JSONObject get10PlayerData() throws SQLException {

            Connection conn = null;
            Statement stmt = null;

            JSONObject playerFullInfo = new JSONObject();

            try {
                conn = Connect.createConnection();

                stmt = conn.createStatement();
                String query = "Select pci.*,ppi.* from player_career_info pci inner join player_personal_info ppi on pci.id = ppi.player_id  where id <= 10  ";
                log.debug("Executing get10PlayerData Query : {} ", query);

                ResultSet rs = stmt.executeQuery(query);

                int rowCount =0 ;

                JSONArray players = new JSONArray();
                while (rs.next()) {

                    Map<String ,Object> playerData = new LinkedHashMap<>();

                    log.info("Id : {}" , rs.getInt("id"));
                    playerData.put("Id", rs.getInt("id"));

                    log.info("PlayerName : {}" , rs.getString("player_name"));
                    playerData.put("PlayerName ",rs.getString("player_name"));

                    log.info("PlayerSpecification : {}" , rs.getString("player_specification"));
                    playerData.put("PlayerSpecification", rs.getString("player_specification"));

                    log.info("TotalMatches  : {}" ,rs.getInt("player_total_matches"));
                    playerData.put("Total Matches ",rs.getInt("player_total_matches"));

                    log.info("TotalCenturies : {}" , rs.getInt("player_centuries"));
                    playerData.put("TotalCenturies ",rs.getInt("player_centuries"));

                    log.info("TotalHalfCenturies : {}" , rs.getInt("player_half_centuries"));
                    playerData.put("TotalHalfCenturies",rs.getInt("player_half_centuries"));

                    log.info("Total5WicketsHaul : {}" , rs.getInt("player_five_wickets"));
                    playerData.put("TotalFiveWicketsHaul ",rs.getInt("player_five_wickets"));

                    log.info("TotalHatricks : {}", rs.getInt("player_total_hatricks"));
                    playerData.put("TotalHatricks ",rs.getInt("player_total_hatricks"));

                    log.info("TotalOdiMatches : {}" , rs.getInt("total_ODI_matches"));
                    playerData.put("TotalODIMatches", rs.getInt("total_ODI_matches"));

                    log.info("TotalTestMatches : {}" , rs.getInt("total_Test_matches"));
                    playerData.put("TotalTestMatches", rs.getInt("total_Test_matches"));

                    log.info("TotalT20iMatches : {}" , rs.getInt("total_T20i_matches"));
                    playerData.put("TotalT20iMatches", rs.getInt("total_T20i_matches"));

                    log.info("TotalODIWickets : {}" , rs.getInt("total_odi_wickets"));
                    playerData.put("TotalODIWickets", rs.getInt("total_odi_wickets"));

                    log.info("TotalT20iWickets : {}" , rs.getInt("total_t20i_wickets"));
                    playerData.put("TotalT20iWickets", rs.getInt("total_t20i_wickets"));

                    log.info("TotalTestWickets : {}" , rs.getInt("total_test_wickets"));
                    playerData.put("TotalTestWickets", rs.getInt("total_test_wickets"));

                    log.info("TotalODIRuns : {}" , rs.getInt("player_odi_runs"));
                    playerData.put("TotalODIRuns", rs.getInt("player_odi_runs"));

                    log.info("TotalT20iRuns : {}" , rs.getInt("player_t20i_runs"));
                    playerData.put("TotalT20iRuns", rs.getInt("player_t20i_runs"));

                    log.info("TotalTestRuns : {}" , rs.getInt("player_test_runs"));
                    playerData.put("TotalTestRuns", rs.getInt("player_test_runs"));

                    log.info("Age : {}" , rs.getInt("age"));
                    playerData.put("Age", rs.getInt("age"));

                    log.info("Gender : {}" ,rs.getString("gender"));
                    playerData.put("Gender", rs.getString("gender"));

                    log.info("State : {}" , rs.getString("state"));
                    playerData.put("State", rs.getString("state"));

                    log.info("++++++++++++++++++++++++++++++++++++++");
                    rowCount ++;
                    players.add(playerData);
                }
                playerFullInfo.put("players" ,players);
                log.info(" total no of player displayed are: {}",rowCount);
            } finally {
                DBUtil.close(stmt, conn);
            }

            return playerFullInfo;

        }

        public static Map<String, Object> fetchPlayerT20iInfoByName(String playerName) throws SQLException {

            Connection conn = null;
            PreparedStatement ps = null;
            Map<String, Object> playerT20iInfo = new HashMap<>();

            try {

                if (playerName == null || playerName.isEmpty()) {
                    log.error("Please provide a valid playerName, invalid playerName null or empty :{}", playerName);
                    throw new IllegalArgumentException("Please provide a valid playerName, invalid playerName null or empty :{}" +playerName);
                }

                conn = Connect.createConnection();

                String query = "Select pci.player_name, pci.player_specification, pci.total_T20i_matches,pci.total_t20i_wickets,pci.player_t20i_runs, ppi.age,ppi.gender  from player_career_info pci left join player_personal_info ppi on pci.id = ppi.player_id  where player_name = ? ";
                log.debug("Executing fetchPlayerT20iInfoByName Query : {} ", query);
                log.debug("Parameters : { Player Name : {}} ", playerName);

                ps = conn.prepareStatement(query);
                ps.setString(1, playerName);
                ResultSet rs = ps.executeQuery();

                Map<String, Object> playerData = new LinkedHashMap<>();
                while (rs.next()) {

                    log.info("PlayerName : {}" , rs.getString("player_name"));

                    log.info("PlayerSpecification : {}" , rs.getString("player_specification"));
                    playerData.put("PlayerSpecification", rs.getString("player_specification"));

                    log.info("TotalT20iMatches  : {}" , rs.getInt("total_T20i_matches"));
                    playerData.put("TotalT20iMatches", rs.getInt("total_T20i_matches"));

                    log.info("TotalT20iWickets  : {}", rs.getInt("total_t20i_wickets"));
                    playerData.put("TotalT20iWickets", rs.getInt("total_t20i_wickets"));

                    log.info("TotalT20iRuns : {}" ,rs.getInt("player_t20i_runs"));
                    playerData.put("TotalT20iRuns", rs.getInt("player_t20i_runs"));

                    log.info("Age : {}" , rs.getInt("age"));
                    playerData.put("Age", rs.getInt("age"));

                    log.info("Gender : {}" , rs.getString("gender"));
                    playerData.put("Gender", rs.getString("gender"));
                }
                playerT20iInfo.put(playerName, playerData);
                if (playerData.isEmpty()){
                    log.debug("player is not available with this name: {} " , playerName);
                    throw new IllegalArgumentException("Please provide a valid playerName, invalid playerName :" + playerName);
                }

            } finally {




                DBUtil.close(ps, conn);
            }
            return playerT20iInfo;
        }

        public static Map<String, Object> fetchPlayerODIInfoByName(String playerName) throws SQLException {
            Map<String, Object> playerOdiInfo = new HashMap<>();

            Connection conn = null;
            PreparedStatement ps = null;

            try {
                if (playerName == null || playerName.isEmpty()) {
                    log.debug("Please provide a valid playerName, invalid playerName null or empty ::{}", playerName);
                    throw new IllegalArgumentException("Please provide a valid playerName, invalid playerName null or empty :" + playerName);
                }
                conn = Connect.createConnection();

                String query = "Select pci.id, pci.player_name, pci.player_specification, pci.total_ODI_matches,pci.total_odi_wickets,pci.player_odi_runs, ppi.age,ppi.gender  from player_career_info pci left join player_personal_info ppi on pci.id = ppi.player_id  where player_name = ? ";
                log.debug("Executing fetchPlayerODIInfoByName Query : {} ", query);
                log.debug("Parameters : { Player_Name : {}} ", playerName);
                ps = conn.prepareStatement(query);
                ps.setString(1, playerName);
                ResultSet rs = ps.executeQuery();

                Map<String, Object> playerData = new LinkedHashMap<>();
                while (rs.next()) {
                    log.info("Id : {}" , rs.getInt("id"));
                    playerData.put("Id", rs.getInt("id"));

                    log.info("PlayerName : {}" , rs.getString("player_name"));

                    log.info("PlayerSpecification : {}" , rs.getString("player_specification"));
                    playerData.put("PlayerSpecification", rs.getString("player_specification"));

                    log.info("TotalODIMatches  : {}" , rs.getInt("total_ODI_matches"));
                    playerData.put("TotalODIMatches", rs.getInt("total_ODI_matches"));

                    log.info("TotalODIWickets  :{}" , rs.getInt("total_odi_wickets"));
                    playerData.put("TotalODIWickets", rs.getInt("total_odi_wickets"));

                    log.info("TotalODIRuns : {}" , rs.getInt("player_odi_runs"));
                    playerData.put("TotalODIRuns", rs.getInt("player_odi_runs"));

                    log.info("Age : {}" , rs.getInt("age"));
                    playerData.put("Age", rs.getInt("age"));

                    log.info("Gender : {}" , rs.getString("gender"));
                    playerData.put("Gender", rs.getString("gender"));

                }
                playerOdiInfo.put(playerName, playerData);
                if (playerData.isEmpty()){
                    log.debug("player is not available with this name : {}" , playerName);
                    throw new IllegalArgumentException("Please provide a valid playerName, invalid playerName :" + playerName);
                }

            } finally {
                DBUtil.close(ps, conn);
            }
            return playerOdiInfo;

        }

        public static Map<String, Object> fetchPlayerTestInfoByName(String playerName) throws SQLException {
            Map<String, Object> playerTestInfo = new HashMap<>();
            Connection conn = null;
            PreparedStatement ps = null;

            try {
                if (playerName == null || playerName.isEmpty()) {
                    log.debug("Please provide a valid playerName, invalid playerName null or empty ::{}", playerName);
                    throw new IllegalArgumentException("Please provide a valid playerName, invalid playerName null or empty :" + playerName);
                }
                conn = Connect.createConnection();

                String query = "Select pci.id, pci.player_name, pci.player_specification, pci.total_Test_matches,pci.total_test_wickets,pci.player_test_runs, ppi.age,ppi.gender,ppi.state  from player_career_info pci left join player_personal_info ppi on pci.id = ppi.player_id  where player_name = ? ";
                log.debug("Executing fetchPlayerTestInfoByName2 Query : {} ", query);
                log.debug("Parameter : { PlayerName : {}} ", playerName);
                ps = conn.prepareStatement(query);
                ps.setString(1, playerName);
                ResultSet rs = ps.executeQuery();

                Map<String, Object> playerData = new LinkedHashMap<>();
                while (rs.next()) {

                    log.info("Id : {}" , rs.getInt("id"));
                    playerData.put("Id", rs.getInt("id"));

                    log.info("PlayerName : {}" , rs.getString("player_name"));

                    log.info("PlayerSpecification : {}" , rs.getString("player_specification"));
                    playerData.put("PlayerSpecification", rs.getString("player_specification"));

                    log.info("TotalTestMatches  : {}" , rs.getInt("total_Test_matches"));
                    playerData.put("TotalTestMatches", rs.getInt("total_Test_matches"));

                    log.info("TotalTestWickets  :{}" ,rs.getInt("total_test_wickets"));
                    playerData.put("TotalTestWickets", rs.getInt("total_test_wickets"));

                    log.info("TotalTestRuns : {}" , rs.getInt("player_test_runs"));
                    playerData.put("TotalTestRuns", rs.getInt("player_test_runs"));

                    log.info("Age : {}" , rs.getInt("age"));
                    playerData.put("Age", rs.getInt("age"));

                    log.info("Gender : {}" , rs.getString("gender"));
                    playerData.put("Gender", rs.getString("gender"));

                    log.info("State : {}" , rs.getString("state"));
                    playerData.put("State", rs.getString("state"));

                }
                playerTestInfo.put(playerName, playerData);
                if (playerData.isEmpty()){
                    log.debug("player is not available with this name : {}" , playerName);
                    throw new IllegalArgumentException("Please provide a valid playerName, invalid playerName :" + playerName);
                }
            } finally {
                DBUtil.close(ps, conn);
            }
            return playerTestInfo;
        }

        public static Map<String, Object> fetchPlayerPersonalDataByName(String name) throws SQLException {
            Map<String, Object> playerPersonalInfo = new HashMap<>();
            Connection conn = null;
            PreparedStatement ps = null;

            try {

                if (name == null || name.isEmpty()) {
                    log.debug("Please provide a valid playerName, invalid playerName null or empty ::{}", name);
                    throw new IllegalArgumentException("Please provide a valid playerName, invalid playerName null or empty :" + name);
                }

                conn = Connect.createConnection();

                String query = "Select * from player_personal_info  where name = ?";
                log.debug("Executing fetchPlayerPersonalData Query : {} ", query);
                log.debug("Parameter : { Name : {}  } ", name);

                ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();

                Map<String, Object> playerData = new LinkedHashMap<>();
                while (rs.next()) {

                    log.info("Id : {}", rs.getInt("player_id"));
                    playerData.put("Id", rs.getInt("player_id"));

                    log.info("Player Name :{}" , rs.getString("name"));

                    log.info("Age : {}" , rs.getInt("age"));
                    playerData.put("Age ", rs.getInt("age"));

                    log.info("Gender : {}" , rs.getString("gender"));
                    playerData.put("Gender", rs.getString("gender"));

                    log.info("State : {}", rs.getString("state"));
                    playerData.put("State", rs.getString("state"));

                }
                playerPersonalInfo.put(name, playerData);
                if (playerData.isEmpty()){
                    log.debug("player is not available with this name : {}" , name);
                    throw new IllegalArgumentException("Please provide a valid playerName, invalid playerName :" + name);
                }
            }finally{
                DBUtil.close(ps, conn);
            }
            return playerPersonalInfo;

        }

        public static Map<String, Object> fetch5PlayerDataByGender(String gender) throws SQLException {
            List<Object> players = new ArrayList<>();
            Connection conn = null;
            PreparedStatement ps = null;

            Map<String, Object> top5experiencedPlayerInfo = new LinkedHashMap<>();
            try {
                if (gender == null || gender.isEmpty()) {
                    log.debug("Please provide a valid playerName, invalid playerName null or empty ::{}", gender);
                    throw new IllegalArgumentException("Please provide a valid playerName, invalid playerName null or empty :" + gender);
                }
                conn = Connect.createConnection();

                String query = "Select pci.id,pci.player_name,pci.player_specification,pci.player_total_matches,ppi.age,ppi.gender,ppi.state from player_career_info pci inner join player_personal_info ppi on pci.id = ppi.player_id  where gender = ? order by player_total_matches desc limit 5 ";
                log.debug("Executing get10PlayerData Query : {} ", query);
                log.debug("Parameter : { Gender : {}} ", gender);

                ps = conn.prepareStatement(query);
                ps.setString(1, gender);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    Map<String,Object> playerData = new LinkedHashMap<>();

                    log.info("Id : {}" , rs.getInt("id"));
                    playerData.put("Id", rs.getInt("id"));

                    log.info("PlayerName : {}" , rs.getString("player_name"));
                    playerData.put("PlayerName ",rs.getString("player_name"));

                    log.info("PlayerSpecification : {}" , rs.getString("player_specification"));
                    playerData.put("PlayerSpecification", rs.getString("player_specification"));

                    log.info("TotalMatches  : {}" , rs.getInt("player_total_matches"));
                    playerData.put("TotalMatches " , rs.getInt("player_total_matches"));

                    log.info("Age : {}" , rs.getInt("age"));
                    playerData.put("Age ", rs.getInt("age"));

                    log.info("Gender : {}" ,rs.getString("gender"));
                    playerData.put("Gender", rs.getString("gender"));

                    log.info("State : {}" , rs.getString("state"));
                    playerData.put("State", rs.getString("state"));

                    log.info("++++++++++++++++++++++++++++++++");
                    players.add(playerData);

                }

                top5experiencedPlayerInfo.put(gender, players);
                if (players.isEmpty()){
                    log.debug("player is not available with this gender : {}",gender);
                    throw new IllegalArgumentException("Please provide a valid gender, invalid gender :" + gender);
                }
            } finally {
                DBUtil.close(ps, conn);
            }
            return top5experiencedPlayerInfo;

        }

        public static Map<String, Object> fetch5PlayerInfoByGender(String gender) throws SQLException {
            List<Object> players = new ArrayList<>();
            Connection conn = null;
            PreparedStatement ps = null;

            Map<String, Object> top5CenturyScorerInfo = new LinkedHashMap<>();
            try {
                if (gender == null || gender.isEmpty()) {
                    log.debug("Please provide a valid playerName, invalid playerName null or empty ::{}", gender);
                    throw new IllegalArgumentException("Please provide a valid playerName, invalid playerName null or empty :" + gender);
                }
                conn = Connect.createConnection();

                String query = "Select pci.id,pci.player_name,pci.player_specification,pci.player_total_matches,pci.player_centuries,ppi.age from player_career_info pci inner join player_personal_info ppi on pci.id = ppi.player_id  where gender = ? order by player_centuries desc limit 5 ";
                log.debug("Executing get10PlayerData Query : {} ", query);
                log.debug("Parameters : { Gender : {}} ", gender);
                ps = conn.prepareStatement(query);
                ps.setString(1, gender);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    Map<String,Object> playerData = new LinkedHashMap<>();

                    log.info("Id : {}" , rs.getInt("id"));
                    playerData.put("Id", rs.getInt("id"));

                    log.info("PlayerName : {}" , rs.getString("player_name"));
                    playerData.put("PlayerName ",rs.getString("player_name"));

                    log.info("PlayerSpecification : {}" , rs.getString("player_specification"));
                    playerData.put("PlayerSpecification", rs.getString("player_specification"));

                    log.info("TotalMatches  : {}" , rs.getInt("player_total_matches"));
                    playerData.put("TotalMatches " , rs.getInt("player_total_matches"));

                    log.info("TotalCenturies : {}" , rs.getInt("player_centuries"));
                    playerData.put("TotalCenturies ",rs.getInt("player_centuries"));

                    log.info("Age : {}" , rs.getInt("age"));
                    playerData.put("Age ",rs.getInt("age"));

                    log.info("+++++++++++++++++++++++++++");

                    players.add(playerData);

                }

                top5CenturyScorerInfo.put(gender, players);
                if (players.isEmpty()){
                    log.debug("player is not available with gender : {}" ,gender);
                    throw new IllegalArgumentException("Please provide a valid gender, invalid gender :" + gender);
                }
            } finally {
                DBUtil.close(ps, conn);
            }
            return top5CenturyScorerInfo;

        }

        public static Map<String, Object> fetch3PlayerInfoByGender(String gender) throws SQLException {
            Map<String, Object> top3OdiWicketTakers = new LinkedHashMap<>();
            Connection conn = null;
            PreparedStatement ps = null;

            try {
                if (gender == null || gender.isEmpty()) {
                    log.debug("Please provide a valid gender, invalid gender null or empty ::{}", gender);
                    throw new IllegalArgumentException("Please provide a valid gender, invalid gender null or empty :" + gender);

                }
                conn = Connect.createConnection();

                String query = "Select pci.id,pci.player_name,pci.player_specification,pci.total_ODI_matches,pci.total_odi_wickets,ppi.age from player_career_info pci inner join player_personal_info ppi on pci.id = ppi.player_id  where gender = ? order by total_odi_wickets desc limit 3 ";
                log.debug("Executing get10PlayerData Query : {} ", query);
                log.debug("Parameters : { Gender : {}} ", gender);
                ps = conn.prepareStatement(query);
                ps.setString(1, gender);
                ResultSet rs = ps.executeQuery();

                List<Object> players = new ArrayList<>();
                while (rs.next()) {

                    Map<String,Object> playerData = new LinkedHashMap<>();

                    log.info("Id : {}" , rs.getInt("id"));
                    playerData.put("Id", rs.getInt("id"));

                    log.info("PlayerName : {}" , rs.getString("player_name"));
                    playerData.put("PlayerName ",rs.getString("player_name"));

                    log.info("PlayerSpecification : {}" , rs.getString("player_specification"));
                    playerData.put("PlayerSpecification", rs.getString("player_specification"));

                    log.info("TotalODIMatches  : {}" , rs.getInt("total_ODI_matches"));
                    playerData.put("TotalODIMatches", rs.getInt("total_ODI_matches"));

                    log.info("TotalODIWickets  :{}" , rs.getInt("total_odi_wickets"));
                    playerData.put("TotalODIWickets", rs.getInt("total_odi_wickets"));

                    log.info("Age : {}" , rs.getInt("age"));
                    playerData.put("Age ",rs.getInt("age"));

                    log.info("++++++++++++++++++++++++++++++++");
                    players.add(playerData);

                }

                top3OdiWicketTakers.put(gender, players);

                if (players.isEmpty()){
                    log.debug("Players are not available with  gender : {} ",gender);
                    throw new IllegalArgumentException("Please provide a valid gender, invalid gender :" + gender);
                }

            } finally {
                DBUtil.close(ps, conn);
            }
            return top3OdiWicketTakers;
        }
    }



