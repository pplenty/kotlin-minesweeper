package game.minesweeper.domain.map

class MineMap(private val config: MapConfig, private val _rows: List<Row>) {

    fun rows() = _rows

    fun setMines(coordinates: List<Coordinate>) {
        coordinates.groupBy { it.x }
            .forEach { _rows[it.key - 1].setMines(it.value) }

        val borders = coordinates.flatMap {
            it.findBorder(config.height, config.width)
        }

        _rows.flatMap { it.fragments() }
            .forEach { it.increaseBorderMine(it.count(borders)) }
    }

    fun open(coordinate: Coordinate): Boolean {
        val result = _rows.firstNotNullOf { it.open(coordinate) }
        if (result == OpenResult.EMPTY) {
            val found = coordinate.findBorder(config.height, config.width)
            found.forEach { openChain(it) }
        }
        return result != OpenResult.BOOM
    }

    private fun openChain(coordinate: Coordinate) {
        _rows.mapNotNull { it.openChain(coordinate, config.height, config.width) }
            .flatten()
            .forEach { openChain(it) }
    }

    fun countOfClosed() = _rows.flatMap { it.fragments() }
        .filterNot { it.hasMine() }
        .count { it.isClosed() }

    companion object {
        private const val START_ROW_NUM = 1
        fun create(config: MapConfig) = MineMap(
            config,
            (START_ROW_NUM..config.height)
                .map { Row.from(it, config.width) }
        )
    }
}
