package com.sample.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    List<String> mockList;

    @Captor
    ArgumentCaptor<String> captor;

    @Test
    void list() {
        mockList.add("apple");

        verify(mockList).add("apple");
        verify(mockList, times(1)).add("apple");
        verify(mockList, atLeast(1)).add("apple");
        verify(mockList, atLeastOnce()).add("apple");
        verify(mockList, atMost(1)).add("apple");
        verify(mockList, never()).get(0);        // get 을 호출하지 않았다
        verify(mockList, timeout(100)).add("apple");
    }

    @Test
    void MockitoWhen() {
        when(mockList.get(0)).thenReturn("banana");
        when(mockList.get(2)).thenThrow(new IllegalArgumentException());

        assertEquals("banana", mockList.get(0));

        //원래 null이 일어나므로 thenThrow가 작동하는지 검증하기 위해 다른 예외를 던짐
        assertThrows(IllegalArgumentException.class, () -> mockList.get(2));
    }

    @Test
    void argumentCaptorMethod() {
        mockList.add("apple");

        verify(mockList).add(captor.capture());
    }
}