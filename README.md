# kotlin-minesweeper

## 🚀 1단계 - 지뢰 찾기(그리기)

### 요구사항 정리
- [x] 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- [x] 지뢰는 눈에 잘 띄는 것으로 표기한다.
- [x] 지뢰는 가급적 랜덤에 가깝게 배치한다.


## 🚀 2단계 - 지뢰 찾기(지뢰 개수)

### 요구사항 정리
- [x] 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.


## 🚀 3단계 - 지뢰 찾기(게임 실행)

### 요구사항 정리
- [x] 지뢰가 없는 인접한 칸이 모두 열리게 된다.

```text
높이를 입력하세요.
10

너비를 입력하세요.
10

지뢰는 몇 개인가요?
10

지뢰찾기 게임 시작
open: 1, 1
0 1 C C C C C C C C
0 1 C C C C C C C C
0 1 C C C C C C C C
1 1 C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C

open: 4, 1
Lose Game.
```
