package game.minesweeper

import game.minesweeper.domain.map.Coordinate
import game.minesweeper.domain.map.MapConfig
import game.minesweeper.domain.map.MineMap
import game.minesweeper.domain.strategy.RandomMineGenerator
import game.minesweeper.view.InputView
import game.minesweeper.view.ResultView

class Game(config: MapConfig, private val mineCount: Int) {

    private val mineMap: MineMap

    init {
        require(config.configurableMine(mineCount)) { "지뢰의 개수가 너무 많습니다.(${mineCount}개)" }
        mineMap = MineMap.create(config)
        mineMap.setMines(RandomMineGenerator(config).generate(mineCount))
    }

    fun start() {
        ResultView.start()
        while (true) {
            val open = InputView.readCoordinate()
            mineMap.open(Coordinate(open[0], open[1]))
            ResultView.drawMap(mineMap)
        }
    }
}

fun main() {
    val config = MapConfig(InputView.readHeight(), InputView.readWeight())
    val game = Game(config, InputView.readMineCount())
    game.start()
}
