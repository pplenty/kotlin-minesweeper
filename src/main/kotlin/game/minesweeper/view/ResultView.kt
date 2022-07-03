package game.minesweeper.view

import game.minesweeper.domain.map.Fragment
import game.minesweeper.domain.map.MineMap

object ResultView {

    fun start() {
        println("지뢰찾기 게임 시작")
    }

    fun drawMap(mineMap: MineMap) {
        mineMap.rows().forEach { row ->
            println(row.fragments().joinToString(separator = " ") { formatFragment(it) })
        }
    }

    private fun formatFragment(fragment: Fragment) = when {
        fragment.isClosed() -> "C"
        fragment.hasMine() -> "*"
        else -> fragment.borderMine().toString()
    }
}
