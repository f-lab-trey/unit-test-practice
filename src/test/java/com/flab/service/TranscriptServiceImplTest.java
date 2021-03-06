package com.flab.service;

import com.flab.exception.NoSuchStudentException;
import com.flab.model.Course;
import com.flab.model.Score;
import com.flab.model.Student;
import com.flab.repository.CourseRepository;
import com.flab.repository.ScoreRepository;
import com.flab.repository.StudentRepository;
import com.flab.service.impl.TranscriptServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TranscriptServiceImplTest {

    private static final Course KOREAN = new Course().setId(1).setName("korean");
    private static final Course ENGLISH = new Course().setId(2).setName("english");
    private static final Course MATH = new Course().setId(3).setName("math");
    private static final Course SCIENCE = new Course().setId(4).setName("science");

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ScoreRepository scoreRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private TranscriptServiceImpl transcriptService;


    @Test
    void testGetAverageScore_HappyCase_VerifyReturnedValue_Success() {
        // given
        final int studentID = 1;
        final Student trey = new Student().setId(studentID).setName("Trey").setMajor("Computer Engineering")
                .setCourses(List.of(KOREAN, ENGLISH, MATH, SCIENCE));

        Mockito.when(studentRepository.getStudent(1))
                .thenReturn(Optional.of(trey));

        Mockito.when(scoreRepository.getScore(studentID, 1))
                .thenReturn(Optional.of(new Score().setCourse(KOREAN).setScore(100)));
        Mockito.when(scoreRepository.getScore(studentID, 2))
                .thenReturn(Optional.of(new Score().setCourse(ENGLISH).setScore(90)));
        Mockito.when(scoreRepository.getScore(studentID, 3))
                .thenReturn(Optional.of(new Score().setCourse(MATH).setScore(80)));
        Mockito.when(scoreRepository.getScore(studentID, 4))
                .thenReturn(Optional.of(new Score().setCourse(SCIENCE).setScore(70)));

        // when
        final double averageScore = transcriptService.getAverageScore(studentID);

        // then
        Assertions.assertEquals(85.0, averageScore);
    }

    @Test
    @DisplayName("????????? studentID??? ???????????? Student??? ?????? ???, getAverageScore()??? NoSuchStudentException??? Throw ??????.")
    void testGetAverageScore_StudentNotExist_ThrowNoSuchStudentException_Error() {
        // given
        final int studentID = 1;
        Mockito.when(studentRepository.getStudent(studentID))
                .thenReturn(Optional.empty());

        // when & then
        Assertions.assertThrows(NoSuchStudentException.class, () -> transcriptService.getAverageScore(studentID));
    }

    @Test
    @DisplayName("getAverageScore()?????? studentRepository.getStudent()??? ??? ???, scoreRepository.getScore()??? student??? course ???????????? ????????????.")
    void testGetAverageScore_HappyCase_VerifyNumberOfInteractions_Success() {
        // TODO:
        // Hint: Mockito.verify() ??????
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    @DisplayName("scoreRepository????????? ????????? Score??? ???????????? ?????? ??? ?????? ??????, getAverageScore()??? NoSuchScoreException??? Throw ??????.")
    void testGetAverageScore_ScoreNotExist_ThrowNoSuchScoreException_Error() {
        // TODO:
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    @DisplayName("getRankedStudentAsc()??? ????????????, ???????????? ????????? course??? ???????????? ?????? ???????????? ???????????? ????????? ?????????????????? ????????????.")
    void testGetRankedStudentsAsc_HappyCase_VerifyReturnedValueAndInteractions_Success() {
        // TODO:
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    @DisplayName("courseRepository?????? ???????????? ????????? courseID??? course??? ????????? ??? ????????? NoSuchCourseException??? Throw ??????.")
    void testGetRankedStudentsAsc_CourseNotExist_ThrowNoSuchCourseException_Error() {
        // TODO:
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
