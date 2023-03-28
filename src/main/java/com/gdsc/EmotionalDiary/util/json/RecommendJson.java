package com.gdsc.EmotionalDiary.util.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RecommendJson {
    private List<MovieJson> movie;
    private List<SongJson> song;

    public RecommendJson() {
        movie = new ArrayList<MovieJson>();
        song = new ArrayList<SongJson>();
    }
}
