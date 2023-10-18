package com.cursosant.calculatorbase

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUtilsMockTest{

    @Mock
    private lateinit var operations: Operations

    @Mock
    private lateinit var listener: OnResolveListener

    private lateinit var calculatorUtils: CalculatorUtils

    @Before
    fun setup(){
        calculatorUtils = CalculatorUtils(operations, listener)
    }

    @Test
    fun `Calculation calls checkOrResolve and doesn't return`(){
        // Given
        val operation = "5*3"
        val isFromResolve = true
        //When
        calculatorUtils.checkOrResolve(operation, isFromResolve)
        //Then
        verify(operations).tryResolve(operation, isFromResolve, listener)
    }

    @Test
    fun `Calculation calls addOperator with valid subtraction and doesn't return`(){
        //Given
        val operator = "-"
        val operation = "4+"
        var isCorrect = false
        //When
        calculatorUtils.addOperator(operator, operation){
            isCorrect = true
        }
        //Then
        assertTrue(isCorrect)
    }

    @Test
    fun `Calculation calls addOperator with an invalid subtraction and doesn't return`(){
        //Given
        val operator ="-"
        val operation = "4."
        var isCorrect = false
        //When
        calculatorUtils.addOperator(operator, operation){
            isCorrect = true
        }
        //Then
        assertFalse(isCorrect)
    }

    @Test
    fun `Calculation calls addPoint with an operation without a point and doesn't return`(){
        //Given
        val operation = "4x4"
        var isCorrect = false
        //When
        calculatorUtils.addPoint(operation){
            isCorrect = true
        }
        //Then
        assertTrue(isCorrect)
        verifyNoInteractions(operations)
    }

    @Test
    fun `Calculation calls addPoint with an operation with point and doesn't return`(){
        //Given
        val operation = "7.5x2"
        val operator = "x"
        var isCorrect = false
        //When
        `when`(operations.getOperator(operation)).thenReturn(operator)
        `when`(operations.divideOperation(operator, operation)).thenReturn(arrayOf("7.5", "2"))
        calculatorUtils.addPoint(operation){
            isCorrect = true
        }
        //Then
        assertTrue(isCorrect)
        verify(operations).getOperator(operation)
        verify(operations).divideOperation(operator, operation)
    }

}