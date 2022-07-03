package game.minesweeper.domain.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("지뢰 지도 생성")
internal class MineMapTest {

    @Test
    fun `높이와 너비 만큼 맵 그리기`() {
        val config = MapConfig(5, 10)
        val map = MineMap.create(config)
        assertThat(map.rows()).hasSize(5)
        assertThat(map.rows().first().fragments()).hasSize(10)
    }

    @Test
    fun `지뢰 매설`() {
        val config = MapConfig(5, 10)
        val map = MineMap.create(config)
        map.setMines(listOf(Coordinate(1, 2), Coordinate(1, 3), Coordinate(5, 10)))
        assertThat(map.rows().flatMap { it.fragments() }.filter { it.hasMine() }).hasSize(3)
    }

    @Test
    fun `지뢰 오픈하면 false`() {
        val config = MapConfig(3, 3)
        val map = MineMap.create(config)
        map.setMines(listOf(Coordinate(1, 1), Coordinate(1, 2), Coordinate(1, 3)))
        assertThat(map.open(Coordinate(1, 1))).isFalse
    }

    @Test
    fun `숫자 오픈하면 하나만 오픈`() {
        val config = MapConfig(3, 3)
        val map = MineMap.create(config)
        map.setMines(listOf(Coordinate(1, 1), Coordinate(1, 2), Coordinate(1, 3)))
        val result = map.open(Coordinate(2, 1))
        assertThat(result).isTrue
        assertThat(map.rows().flatMap { it.fragments() }.filter { it.isClosed() }).hasSize(8)
    }

    @Test
    fun `빈 곳 오픈하면 인접 연쇄오픈`() {
        val config = MapConfig(3, 3)
        val map = MineMap.create(config)
        map.setMines(listOf(Coordinate(1, 1), Coordinate(1, 2), Coordinate(1, 3)))
        val result = map.open(Coordinate(3, 1))
        assertThat(result).isTrue
        assertThat(map.rows().flatMap { it.fragments() }.filter { it.isClosed() }).hasSize(3)
    }
}
