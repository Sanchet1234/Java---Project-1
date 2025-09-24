package edu.ccrm.service;

import edu.ccrm.domain.Student;

public interface TranscriptService {
    String generateTranscript(Student student);
    double computeGPA(Student student);
}
