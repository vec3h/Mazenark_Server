import static spark.Spark.*;
import static spark.route.HttpMethod.delete;


import Rooms.Room;
import Rooms.RoomManager;

public class main {
    public static void main(String[] args) {
        //Initialisation
        port(9000);
        threadPool(Runtime.getRuntime().availableProcessors());


        RoomManager roomManager = new RoomManager(1);


        path("/api", () -> {
            before("/*", (req, res) -> res.type("application/json"));

            get("/port", (req, res) -> {
                Room active = roomManager.GetActiveRoom();
                if(active != null) {
                    active.AddPlayer();
                    return String.format("{\"port\":\"%s\"}", Integer.toString(active.getPort()));
                } else {
                    res.status(400);
                    return "{\"error\":\"All rooms occupied\"}";
                }
            });

            post("/room/playerLeft", (req, res) -> {
                //todo parse json and get roomID from which player lefted  json: {room : instanceId};
//                Room room = roomManager.GetRoomById(res.).RemovePlayer();
                return "{\"status\":\"OK\"}";
            });

            post("/gameresult", (req, res) -> "{\"status\":\"OK\"}");
        });


    }
}