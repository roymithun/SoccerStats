package com.inhouse.soccerstats.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.inhouse.soccerstats.model.Match
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class SoccerMatchDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var dao: SoccerMatchDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun insert_and_fetch_all_matches() = runBlocking {
        val matchList = FakeSoccerMatchList.matches

        dao.insertAllMatches(matchList)

        val insertedMatches: List<Match> = dao.getAllMatches().take(3).first()

        assertThat(insertedMatches).isEqualTo(matchList)
    }

    @Test
    fun insert_and_fetch_match_by_id() = runBlocking {
        val matchList = FakeSoccerMatchList.matches
        dao.insertAllMatches(matchList)

        val dbMatch = dao.getMatchById(1).take(1).first()
        assertThat(dbMatch).isEqualTo(FakeSoccerMatchList.matches[0])
    }
}