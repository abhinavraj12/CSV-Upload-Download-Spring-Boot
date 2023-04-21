package com.csv.upload.download.service;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.csv.upload.download.helper.CSVHelper;
import com.csv.upload.download.model.User;
import com.csv.upload.download.repository.UserRepo;


@Service
public class CSVService {

  @Autowired
  private UserRepo userRepo;

  public void saves(MultipartFile file) {
    try {
      List<User> user = CSVHelper.csvToTutorials(file.getInputStream());
      userRepo.saveAll(user);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<User> users = userRepo.findAll();

    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(users);
    return in;
  }

  public List<User> getAllTutorials() {
    return userRepo.findAll();
  }
}