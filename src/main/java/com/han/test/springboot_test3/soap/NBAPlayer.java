package com.han.test.springboot_test3.soap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NBAPlayer {
    String name;
    String team;
    List<String> teams;
    String description;
    Position position;
    @Override
    public String toString() {
        return "NBA现役球员" + name + "，司职" + position.value + "现效力于" + team + "，曾效力球队" + teams.toString();
    }
}
