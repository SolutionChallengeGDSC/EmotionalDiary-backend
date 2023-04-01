package com.gdsc.EmotionalDiary.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.EmotionalDiary.util.json.RecommendJson;
import com.mysql.cj.log.Log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PredictModule {
    // 여기 부분은 테스트를 위해서 살린 곳입니다. 테스트하려면 여기를 풀어주세요
    // private final String BASIC_PATH = "/project/SolutionChallengeGDSC/EmotionalDiary-backend/predict";


    // 여기가 실제로 배포 때 사용하는 PATH 입니다. 테스트 중일 때 여기를 풀어주세요.
    private final String BASIC_PATH = "home/dongwon000103/EmotionalDiary-backend/predict/user";
    private final String RECOMMEND_AI_PATH = "home/dongwon000103/TEMP/Recommendation/Recommend_predict.py";
    private final String PYTHON = "python3";
    private final String CONTENT_FILE_NAME = "/content.txt";
    private final String DECISION_FILE_NAME = "/output.txt";
    private Long userId;

    private PredictModule(Long userId){
        this.userId = userId;
    }

    public static PredictModule newInstance(Long userId) {
        return new PredictModule(userId);
    }

    public void saveDiaryContentAndPredict(Long diaryId, String contents) {
        String diaryPath = getDiaryPath(diaryId);
        File folder = new File(diaryPath);
        if(!folder.exists()) {
            try {
                folder.mkdirs();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(diaryPath+CONTENT_FILE_NAME);
            outputStream.write(contents.getBytes());
            outputStream.close();

            // 프로세스 시작
            new ProcessBuilder(
                    PYTHON, RECOMMEND_AI_PATH, "--user_id", userId.toString(), "--diary_id",diaryId.toString()).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDiaryOutput(Long diaryId) {
        Path path = Paths.get(getDiaryPath(diaryId) + DECISION_FILE_NAME);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public RecommendJson getRecommand() throws IOException{

        File recommendFile = new File(getRecommendPath());
        if(!recommendFile.exists()) {
            throw new FileNotFoundException();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(recommendFile, RecommendJson.class);
    }

    private String getDiaryPath(Long diaryId) {
        return BASIC_PATH+"/"+String.valueOf(userId)+"/diary/"+String.valueOf(diaryId);
    }
    private String getRecommendPath() {
        return BASIC_PATH+"/"+String.valueOf(userId)+"/recommend.json";
    }
}
