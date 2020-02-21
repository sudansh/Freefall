package com.sudansh.falldetection

import com.nhaarman.mockito_kotlin.spy
import com.sudansh.falldetection.model.FallRecord
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FallDetectorTest {

    lateinit var fallDetector: FallDetector

    @Before
    fun setup() {
        fallDetector = spy(FallDetector())
    }

    @Test
    fun `When angular speed is less then 1ms following by more then 10 ms fall is detected`() {
        val firstResult = fallDetector.calculate(
            x = (-0.15322891).toFloat(),
            y = 0.02873042F,
            z = 0.7469909F
        )
        val secondResult = fallDetector.calculate(
            x = 3.1794999F,
            y = (-0.89064306).toFloat(),
            z = 10.534488F
        )

        assert(firstResult == null)
        assert(secondResult is FallRecord)
    }

    @Test
    fun `When angular speed is more then 1ms no fall is detected`() {
        val firstResult = fallDetector.calculate(
            x = 1.7238252F,
            y = (-0.08619126).toFloat(),
            z = 9.643845F
        )
        val secondResult = fallDetector.calculate(
            x = 3.1794999F,
            y = (-0.89064306).toFloat(),
            z = 10.534488F
        )

        assert(firstResult == null)
        assert(secondResult == null)
    }

}
