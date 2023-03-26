package com.gdsc.EmotionalDiary.util.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendJson {
    private List<MovieJson> movie;
    private List<SongJson> song;
}
