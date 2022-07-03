package game.minesweeper.domain.map

data class Fragment(
    private val coordinate: Coordinate,
    private var _hasMine: Boolean = false,
    private var borderMine: Int = 0,
    private var _isOpen: Boolean = false,
) {

    fun setMine() {
        _hasMine = true
    }

    fun hasMine() = _hasMine

    private fun isEmpty() = borderMine == 0

    fun open(): OpenResult {
        _isOpen = true
        return when {
            hasMine() -> OpenResult.BOOM
            isEmpty() -> OpenResult.EMPTY
            else -> OpenResult.NOT_EMPTY
        }
    }

    fun openChain(maxHeight: Int, maxWidth: Int): List<Coordinate> {
        if (!_isOpen) {
            _isOpen = true
            if (isEmpty()) {
                return coordinate.findBorder(maxHeight, maxWidth)
            }
        }
        return emptyList()
    }

    fun isClosed() = !_isOpen

    fun included(coordinates: List<Coordinate>) = coordinates.contains(coordinate)

    fun count(coordinates: List<Coordinate>) = coordinates.count { it == coordinate }

    fun increaseBorderMine(count: Int) {
        borderMine += count
    }

    fun borderMine() = borderMine

    fun match(coordinate: Coordinate) = this.coordinate == coordinate

    companion object {
        fun of(x: Int, y: Int) = Fragment(Coordinate(x, y))
    }
}

enum class OpenResult {
    EMPTY, NOT_EMPTY, BOOM
}
