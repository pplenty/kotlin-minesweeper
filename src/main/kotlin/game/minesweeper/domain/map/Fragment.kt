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

    fun open() {
        _isOpen = true
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
