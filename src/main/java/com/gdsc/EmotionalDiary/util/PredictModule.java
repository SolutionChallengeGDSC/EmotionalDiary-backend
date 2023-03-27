package com.gdsc.EmotionalDiary.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.EmotionalDiary.util.json.RecommendJson;
import com.mysql.cj.log.Log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PredictModule {
    private final String BASIC_PATH = "/project/SolutionChallengeGDSC/EmotionalDiary-backend/predict";
    private final String PREDICT_FILE_NAME = "predict.py";
    private final String PYTHON_PATH = "/opt/homebrew/bin/python3";
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
                System.out.println(1);
            } catch (Exception e) {
                System.out.println(2);
                throw new RuntimeException(e);
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(diaryPath+CONTENT_FILE_NAME);
            outputStream.write(contents.getBytes());
            outputStream.close();

            ProcessBuilder builder = new ProcessBuilder("/opt/homebrew/bin/python3");
            System.out.println(3);
        } catch (IOException e) {
            System.out.println(4);
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
