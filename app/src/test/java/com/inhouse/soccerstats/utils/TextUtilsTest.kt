package com.inhouse.soccerstats.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TextUtilsTest {
    @Test
    fun `name with 2 segments`() {
        val x = getInitials("Liverpool F.C")
        assertThat(x).isEqualTo("LFC")
    }

    @Test
    fun `name with 3 segments`() {
        val x = getInitials("Manchester City F.C")
        assertThat(x).isEqualTo("MFC")
    }

    @Test
    fun `name with 1 segment`() {
        val x = getInitials("Chelsea")
        assertThat(x).isEqualTo("CHE")
    }
}