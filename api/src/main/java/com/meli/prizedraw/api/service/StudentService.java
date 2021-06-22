package com.meli.prizedraw.api.service;

import com.meli.prizedraw.api.model.StudentModel;
import com.meli.prizedraw.api.model.StudentPartialRequestModel;
import com.meli.prizedraw.api.model.StudentRequestModel;
import com.meli.prizedraw.api.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
public class StudentService {
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private StudentRepository repository;

  public Mono<StudentModel> create(Mono<StudentRequestModel> studentRequest) {
    return studentRequest
      .map(student -> this.modelMapper.map(student, StudentModel.class))
      .map(this.repository::save);
  }

  public Mono<StudentModel> findById(Integer id) {
    return Mono
      .justOrEmpty(this.repository.findById(id))
      .switchIfEmpty(Mono.error(new NoSuchElementException("Student resource not found.")));
  }

  public Flux<StudentModel> findByName(String name) {
    return Flux.fromIterable(this.repository.findByNameContaining(name));
  }

  public Flux<StudentModel> list(Pageable pageable) {
    return Mono
      .fromCallable(() -> this.repository.findAll(pageable))
      .onErrorMap(
        PropertyReferenceException.class,
        (exception) -> new IllegalArgumentException("Invalid sort parameter."))
      .flatMapMany(Flux::fromIterable);
  }

  public Mono<StudentModel> update(Integer id, Mono<StudentRequestModel> studentRequest) {
    return Mono
      .justOrEmpty(this.repository.findById(id))
      .switchIfEmpty(Mono.error(new NoSuchElementException("Student resource not found.")))
      .flatMap(student -> studentRequest
        .map(studentUpdate -> this.modelMapper.map(studentUpdate, StudentModel.class))
        .map(studentUpdate -> studentUpdate
          .toBuilder()
          .id(student.getId())
          .build()))
      .map(this.repository::save);
  }

  public Mono<StudentModel> partialUpdate(Integer id, Mono<StudentPartialRequestModel> studentRequest) {
    return Mono
      .justOrEmpty(this.repository.findById(id))
      .switchIfEmpty(Mono.error(new NoSuchElementException("Student resource not found.")))
      .flatMap(student -> studentRequest
        .doOnNext(studentUpdate -> this.modelMapper.map(studentUpdate, student))
        .thenReturn(student))
      .map(this.repository::save);
  }

  public Mono<StudentModel> delete(Integer id) {
    return Mono
      .justOrEmpty(this.repository.findById(id))
      .switchIfEmpty(Mono.error(new NoSuchElementException("Student resource not found.")))
      .map(student -> {
        this.repository.deleteById(student.getId());
        return student;
      });
  }
}
