package com.cursosant.calculatorbase

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUtilsSpyTest {

    @Spy
    private lateinit var operations: Operations

    @Mock
    private lateinit var listener: OnResolveListener

    private lateinit var calculatorUtils: CalculatorUtils

    @Before
    fun setup(){
        calculatorUtils = CalculatorUtils(operations, listener)
    }

    @Test
    fun `Calculation calls addPoint with an operation with a point and doesn't return`(){
        //Given
        val operation = "7.5x2"
        var isCorrect = false
        //When
        calculatorUtils.addPoint(operation){
            isCorrect = true
        }
        //Then
        assertTrue(isCorrect)
        verify(operations).getOperator(operation)
        verify(operations).divideOperation("x",operation)
    }

    @Test
    fun `Calculation calls addPoint with an operation with two points and doesn't return`(){
        //Given
        val operation = "7.5x9."
        var isCorrect = false
        //When
        calculatorUtils.addPoint(operation){
            isCorrect = true
        }
        //Then
        assertFalse(isCorrect)
        verify(operations).getOperator(operation)
        verify(operations).divideOperation("x",operation)
    }

}