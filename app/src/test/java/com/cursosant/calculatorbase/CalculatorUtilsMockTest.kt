package com.cursosant.calculatorbase

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
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
    fun `Calculation call checkOrResolve and doesn't return`(){
        // Given
        val operation = "5*3"
        val isFromResolve = true
        //When
        calculatorUtils.checkOrResolve(operation, isFromResolve)
        //Then
        verify(operations).tryResolve(operation, isFromResolve, listener)
    }

}