package com.meli.prizedraw.api.service;

import com.meli.prizedraw.api.model.StudentModel;
import com.meli.prizedraw.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StudentDrawService {
  @Autowired
  private StudentRepository repository;

  public Flux<StudentModel> draw(Integer count) {
    return Flux.fromIterable(this.repository.findOrderByRandomLimited(count));
  }
}
