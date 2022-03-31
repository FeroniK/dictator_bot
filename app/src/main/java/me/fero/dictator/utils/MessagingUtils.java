package me.fero.dictator.utils;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;

import java.util.HashMap;
import java.util.List;

public class MessagingUtils {

    public static void sendDm(Member target, User targetUser, String text) {
        if(target != null) {
            target.getUser().openPrivateChannel()
                    .flatMap(c -> c.sendMessage(text))
                    .queue(null, new ErrorHandler().handle(ErrorResponse.CANNOT_SEND_TO_USER, (ex) -> {
                    }));
            return;
        }

        targetUser.openPrivateChannel()
                .flatMap(c -> c.sendMessage(text))
                .queue(null, new ErrorHandler().handle(ErrorResponse.CANNOT_SEND_TO_USER, (ex) -> {
                }));
    }


    public static int parseTime(String time) {
        List<String> pos = List.of("s", "m", "h", "d");
        HashMap<String, Integer> time_map = new HashMap<>();
        time_map.put("s", 1);
        time_map.put("m", 60);
        time_map.put("h", 3600);
        time_map.put("d", 3600 * 24);

        String unit = time.substring(time.length() - 1);
        if(!pos.contains(unit)) {
            return -1;
        }

        try {
            Integer val = Integer.parseInt(time.substring(0, time.length() - 1));
            return val * time_map.get(unit);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static int parseInt(String amount) {
        try {
            int i = Integer.parseInt(amount);
            if(i > 100 || i < 2) {
                return -1;
            }

            return i;
        }
        catch (Exception e) {
            return -1;
        }
    }
}
